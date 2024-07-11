package com.example.prodotti.services;

import com.example.prodotti.converters.ProdottoConverter;
import com.example.prodotti.entities.Categoria;
import com.example.prodotti.entities.Prodotto;
import com.example.prodotti.exceptions.EntityNotFoundException;
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

    public Prodotto getProdottoById(Long id) throws EntityNotFoundException {
        return prodottoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
    }

    public ProdottoResponse getProdottoResponseById(Long id) throws EntityNotFoundException {
        Prodotto prodotto = prodottoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
        return ProdottoConverter.mapToProdottoResponse(prodotto);
    }

    public List<ProdottoResponse> getAllProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return prodotti.stream()
                .map(ProdottoConverter::mapToProdottoResponse)
                .collect(Collectors.toList());
    }

    public ProdottoResponse createProdotto(ProdottoRequest prodottoRequest) throws EntityNotFoundException{
        List<Categoria> categorie = prodottoRequest.getIdCategoria().stream()
                .map(categoriaId -> categoriaRepository.getReferenceById(categoriaId))
                .toList();

        Prodotto prodotto = Prodotto.builder()
                .nome(prodottoRequest.getNome())
                .descrizione(prodottoRequest.getDescrizione())
                .prezzo(prodottoRequest.getPrezzo())
                .quantita(prodottoRequest.getQuantita())
                .categoria(categorie)
                .build();
        prodottoRepository.saveAndFlush(prodotto);
        return ProdottoConverter.mapToProdottoResponse(prodotto);
    }

    public ProdottoResponse updateProdotto(Long id, ProdottoRequest prodottoRequest) throws EntityNotFoundException {
        getProdottoById(id);

        List<Categoria> categorie = prodottoRequest.getIdCategoria().stream()
                .map(categoriaId -> categoriaRepository.getReferenceById(categoriaId))
                .toList();

        Prodotto prodotto = Prodotto.builder()
                .id(id)
                .nome(prodottoRequest.getNome())
                .descrizione(prodottoRequest.getDescrizione())
                .prezzo(prodottoRequest.getPrezzo())
                .quantita(prodottoRequest.getQuantita())
                .categoria(categorie)
                .build();
        prodottoRepository.saveAndFlush(prodotto);
        return ProdottoConverter.mapToProdottoResponse(prodotto);
    }

    public void deleteProdotto(Long id) throws EntityNotFoundException {
        getProdottoById(id);
        prodottoRepository.deleteById(id);
    }
}
