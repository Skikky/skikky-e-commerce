package com.example.prodotti.services;

import com.example.prodotti.converters.ProdottoConverter;
import com.example.prodotti.entities.Prodotto;
import com.example.prodotti.exceptions.EntityNotFoundException;
import com.example.prodotti.exceptions.InputErratoException;
import com.example.prodotti.exceptions.ProdottoPurchaseException;
import com.example.prodotti.repositories.ProdottoRepository;
import com.example.prodotti.requests.ProdottoPurchaseRequest;
import com.example.prodotti.requests.ProdottoRequest;
import com.example.prodotti.responses.ProdottoPurchaseResponse;
import com.example.prodotti.responses.ProdottoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProdottoConverter prodottoConverter;

    public Prodotto getProdottoById(Long id) {
        return prodottoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
    }

    public ProdottoResponse getProdottoResponseById(Long id) {
        Prodotto prodotto = prodottoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
        return prodottoConverter.mapToProdottoResponse(prodotto);
    }

    public List<ProdottoResponse> getAllProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return prodotti.stream()
                .map(prodottoConverter::mapToProdottoResponse)
                .collect(Collectors.toList());
    }

    public ProdottoResponse createProdotto(ProdottoRequest prodottoRequest) {
        inputValidation(prodottoRequest);

        Prodotto prodotto = prodottoConverter.mapToProdotto(prodottoRequest);
        prodottoRepository.saveAndFlush(prodotto);

        return prodottoConverter.mapToProdottoResponse(prodotto);
    }

    public ProdottoResponse updateProdotto(Long id, ProdottoRequest prodottoRequest) {
        inputValidation(prodottoRequest);

        getProdottoById(id);
        Prodotto prodotto = prodottoConverter.mapToProdotto(prodottoRequest);
        prodotto.setId(id);

        prodottoRepository.saveAndFlush(prodotto);
        return prodottoConverter.mapToProdottoResponse(prodotto);
    }

    public void deleteProdotto(Long id) {
        getProdottoById(id);
        prodottoRepository.deleteById(id);
    }

    public List<ProdottoPurchaseResponse> purchaseProducts(List<ProdottoPurchaseRequest> requestList) {
        List<ProdottoPurchaseResponse> result = new ArrayList<>();

        requestList.forEach(request -> {
            if (request.getQuantita() <= 0) {
                throw new InputErratoException("la quantità riferita al prodotto con id " +request.getIdProdotto()+ " non può essere minore o uguale a 0");
            }
            Prodotto prodotto = getProdottoById(request.getIdProdotto());
            if (prodotto.getQuantita() < request.getQuantita()) {
                throw new ProdottoPurchaseException("scorte insufficenti, sono rimaste soltanto "+prodotto.getQuantita()+" scorte del prodotto: "+prodotto.getNome()+" con id: "+prodotto.getId());
            }

            prodotto.setQuantita(prodotto.getQuantita() - request.getQuantita());
            prodottoRepository.saveAndFlush(prodotto);

            result.add(ProdottoPurchaseResponse.builder()
                            .idProdotto(prodotto.getId())
                            .quantita(request.getQuantita())
                            .nome(prodotto.getNome())
                    .build());
        });
        return result;
    }

    private void inputValidation(ProdottoRequest prodottoRequest) {
        if (prodottoRequest.getPrezzo() <= 0) {
            throw new InputErratoException("il prezzo non può essere minore o uguale a 0");
        }
        if (prodottoRequest.getQuantita() <= 0) {
            throw new InputErratoException("la quantità non può essere minore o uguale a 0");
        }
    }
}
