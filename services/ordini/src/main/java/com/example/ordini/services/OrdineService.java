package com.example.ordini.services;

import com.example.ordini.repositories.OrdineRepository;
import com.example.ordini.responses.OrdineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    public OrdineResponse getOrdineById(Long id) {
    }

    public List<OrdineResponse> getOrdiniCarrelloById(Long idCarrello) {
        ordineRepository.getOrdiniByCarrelloId(idCarrello);

    }
}
