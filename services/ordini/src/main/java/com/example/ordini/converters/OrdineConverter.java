package com.example.ordini.converters;

import com.example.ordini.entities.Ordine;
import com.example.ordini.exceptions.InputErratoException;
import com.example.ordini.responses.OrdineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrdineConverter {

    public OrdineResponse mapToOrdineResponse(Ordine ordine) {
        inputValidation(ordine);
        return OrdineResponse.builder()
                .idProdotto(ordine.getIdProdotto())
                .quantita(ordine.getQuantita())
                .build();
    }

    //questo l'ho aggiunto solo per spostarlo in futuro, qui è un controllo inutile
    private void inputValidation(Ordine ordine) {
        if (ordine.getQuantita() < 0) {
            throw new InputErratoException("la quantità non può essere minore di 0");
        }
    }
}
