package com.example.prodotti.converters;

import com.example.prodotti.entities.Categoria;
import com.example.prodotti.entities.Prodotto;
import com.example.prodotti.requests.ProdottoRequest;
import com.example.prodotti.responses.ProdottoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProdottoConverter {

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
                .categoria(new ArrayList<>())
                .build();
    }
}
