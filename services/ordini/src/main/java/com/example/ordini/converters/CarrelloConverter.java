package com.example.ordini.converters;

import com.example.ordini.entities.Carrello;
import com.example.ordini.exceptions.InputErratoException;
import com.example.ordini.responses.CarrelloResponse;
import org.springframework.stereotype.Service;

@Service
public class CarrelloConverter {

    public CarrelloResponse mapToCarrelloResponse(Carrello carrello) {
        inputValidation(carrello);
        return CarrelloResponse.builder()
                .id(carrello.getId())
                .idUtente(carrello.getIdUtente())
                .totalAmount(carrello.getTotalAmount())
                .build();
    }

    //questo l'ho aggiunto solo per spostarlo in futuro, qui è un controllo inutile
    private void inputValidation(Carrello carrello) {
        if (carrello.getTotalAmount() < 0) {
            throw new InputErratoException("il totale non può essere minore di 0");
        }
    }
}
