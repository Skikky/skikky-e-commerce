package com.example.utenti.services;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.exceptions.EntityNotFoundException;
import com.example.utenti.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoRepository.findAll();
    }

    public Indirizzo getIndirizzoById(Long id) {
        return indirizzoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Indirizzo"));
    }

    public Indirizzo createIndirizzo(Indirizzo indirizzo) {
        return indirizzoRepository.saveAndFlush(indirizzo);
    }

    public void deleteIndirizzo(Long id) {
        getIndirizzoById(id);
        indirizzoRepository.deleteById(id);
    }

    public Indirizzo updateIndirizzo(Long id, Indirizzo newIndirizzo) {
        getIndirizzoById(id);
        Indirizzo oldIndirizzo = Indirizzo.builder()
                .id(id)
                .via(newIndirizzo.getVia())
                .numeroCivico(newIndirizzo.getNumeroCivico())
                .comune(newIndirizzo.getComune())
                .cap(newIndirizzo.getCap())
                .build();

        return indirizzoRepository.saveAndFlush(oldIndirizzo);
    }
}
