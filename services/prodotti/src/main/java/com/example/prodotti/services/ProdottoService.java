package com.example.prodotti.services;

import com.example.prodotti.converters.ProdottoConverter;
import com.example.prodotti.entities.Prodotto;
import com.example.prodotti.exceptions.EntityNotFoundException;
import com.example.prodotti.exceptions.InputErratoException;
import com.example.prodotti.repositories.CategoriaRepository;
import com.example.prodotti.repositories.ProdottoRepository;
import com.example.prodotti.requests.ProdottoRequest;
import com.example.prodotti.responses.ProdottoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
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
        //TODO crea un metodo inputValidation()
        if (prodottoRequest.getPrezzo() <= 0) {
            throw new InputErratoException("il prezzo non può essere minore o uguale a 0");
        }
        if (prodottoRequest.getQuantita() <= 0) {
            throw new InputErratoException("la quantità non può essere minore o uguale a 0");
        }

        Prodotto prodotto = prodottoConverter.mapToProdotto(prodottoRequest);
        prodottoRepository.saveAndFlush(prodotto);

        return prodottoConverter.mapToProdottoResponse(prodotto);
    }

    public ProdottoResponse updateProdotto(Long id, ProdottoRequest prodottoRequest) {
        //TODO crea un metodo inputValidation()
        if (prodottoRequest.getPrezzo() <= 0) {
            throw new InputErratoException("il prezzo non può essere minore o uguale a 0");
        }
        if (prodottoRequest.getQuantita() <= 0) {
            throw new InputErratoException("la quantità non può essere minore o uguale a 0");
        }
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
}
