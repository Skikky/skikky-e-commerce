package com.example.ordini.services;

import com.example.ordini.converters.OrdineConverter;
import com.example.ordini.repositories.OrdineRepository;
import com.example.ordini.responses.OrdineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private OrdineConverter ordineConverter;

    public List<OrdineResponse> getOrdiniByCarrelloId(Long idCarrello) {
        return ordineRepository.findOrdiniByCarrelloId(idCarrello)
                .stream()
                .map(ordineConverter::mapToOrdineResponse)
                .toList();
    }
}
