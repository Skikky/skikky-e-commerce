package com.example.ordini.services;

import com.example.ordini.converters.CarrelloConverter;
import com.example.ordini.entities.Carrello;
import com.example.ordini.exceptions.EntityNotFoundException;
import com.example.ordini.repositories.CarrelloRepository;
import com.example.ordini.requests.CarrelloRequest;
import com.example.ordini.responses.CarrelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private CarrelloConverter carrelloConverter;

    public Carrello getCarrelloById(Long id) {
        return carrelloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Carrello"));
    }

    public CarrelloResponse getCarrelloResponseById(Long id) {
        Carrello carrello = carrelloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Carrello"));
        return carrelloConverter.mapToCarrelloResponse(carrello);
    }

    public CarrelloResponse createCarrello(CarrelloRequest carrelloRequest) {
        Carrello carrello = Carrello.builder()
                .idUtente(carrelloRequest.getIdUtente())
                .totalAmount(0.0)
                .ordineList(new ArrayList<>())
                .build();
        carrelloRepository.saveAndFlush(carrello);
        return carrelloConverter.mapToCarrelloResponse(carrello);
    }
}
