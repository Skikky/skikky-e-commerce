package com.example.prodotti.converters;

import com.example.prodotti.entities.Categoria;
import com.example.prodotti.entities.Prodotto;
import com.example.prodotti.requests.ProdottoRequest;
import com.example.prodotti.responses.ProdottoResponse;
import com.example.prodotti.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoConverter {

    @Autowired
    private CategoriaService categoriaService;

    public ProdottoResponse mapToProdottoResponse(Prodotto prodotto) {
        return ProdottoResponse.builder()
                .id(prodotto.getId())
                .nome(prodotto.getNome())
                .descrizione(prodotto.getDescrizione())
                .prezzo(prodotto.getPrezzo())
                .quantita(prodotto.getQuantita())
                .idCategoria(prodotto.getCategoria()
                        .stream()
                        .map(Categoria::getId)
                        .toList())
                .build();
    }

    public Prodotto mapToProdotto(ProdottoRequest prodottoRequest) {
        return Prodotto.builder()
                .nome(prodottoRequest.getNome())
                .descrizione(prodottoRequest.getDescrizione())
                .prezzo(prodottoRequest.getPrezzo())
                .quantita(prodottoRequest.getQuantita())
                .categoria(prodottoRequest.getIdCategoria()
                        .stream()
                        .map(categoriaService::getCategoriaById)
                        .toList())
                .build();
    }
}
