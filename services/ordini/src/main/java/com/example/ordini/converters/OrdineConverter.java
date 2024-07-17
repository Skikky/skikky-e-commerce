package com.example.ordini.converters;

import com.example.ordini.entities.Ordine;
import com.example.ordini.responses.OrdineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrdineConverter {

    public OrdineResponse mapToOrdineResponse(Ordine ordine) {
        return OrdineResponse.builder()
                .idProdotto(ordine.getIdProdotto())
                .quantita(ordine.getQuantita())
                .build();
    }
}
