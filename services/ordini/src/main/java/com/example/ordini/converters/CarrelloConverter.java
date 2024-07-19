package com.example.ordini.converters;

import com.example.ordini.entities.Carrello;
import com.example.ordini.exceptions.InputErratoException;
import com.example.ordini.requests.CarrelloRequest;
import com.example.ordini.responses.CarrelloResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public Carrello mapToCarrello(CarrelloRequest carrelloRequest) {
        return Carrello.builder()
                .idUtente(carrelloRequest.getIdUtente())
                .totalAmount(carrelloRequest.getTotalAmount())
                .createdDate(LocalDateTime.now())

                .build();
    }

    //questo l'ho aggiunto solo per spostarlo in futuro, qui è un controllo inutile
    private void inputValidation(Carrello carrello) {
        if (carrello.getTotalAmount() < 0) {
            throw new InputErratoException("il totale non può essere minore di 0");
        }
    }
}
