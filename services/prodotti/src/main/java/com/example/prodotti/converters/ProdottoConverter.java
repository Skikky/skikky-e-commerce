package com.example.prodotti.converters;

import com.example.prodotti.entities.Prodotto;
import com.example.prodotti.responses.ProdottoResponse;

public class ProdottoConverter {

    public static ProdottoResponse mapToProdottoResponse(Prodotto prodotto) {
        return ProdottoResponse.builder()
                .id(prodotto.getId())
                .nome(prodotto.getNome())
                .descrizione(prodotto.getDescrizione())
                .prezzo(prodotto.getPrezzo())
                .quantita(prodotto.getQuantita())
                .build();
    }

    public static Prodotto mapToProdotto(ProdottoResponse prodottoResponse) {
        return Prodotto.builder()
                .id(prodottoResponse.getId())
                .nome(prodottoResponse.getNome())
                .descrizione(prodottoResponse.getDescrizione())
                .prezzo(prodottoResponse.getPrezzo())
                .quantita(prodottoResponse.getQuantita())
                .build();
    }
}
