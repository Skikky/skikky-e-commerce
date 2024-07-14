package com.example.utenti.services;

import com.example.utenti.converters.UtenteConverter;
import com.example.utenti.entities.Utente;
import com.example.utenti.exceptions.EntityNotFoundException;
import com.example.utenti.exceptions.InputErratoException;
import com.example.utenti.repositories.UtenteRepository;
import com.example.utenti.requests.UtenteRequest;
import com.example.utenti.responses.UtenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private UtenteConverter utenteConverter;

    public Utente getUtenteById(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Utente"));
    }

    public UtenteResponse getUtenteResponseById(Long id) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Utente"));
        return utenteConverter.mapToUtenteResponse(utente);
    }

    public List<UtenteResponse> getAllUtenti() {
        return utenteRepository.findAll().stream()
                .map(utenteConverter::mapToUtenteResponse)
                .collect(Collectors.toList());
    }

    public UtenteResponse createUtente(UtenteRequest utenteRequest) {
        inputValidation(utenteRequest);

        Utente utente = utenteConverter.mapToUtente(utenteRequest);
        utenteRepository.saveAndFlush(utente);

        return utenteConverter.mapToUtenteResponse(utente);
    }

    public void deleteUtente(Long id) {
        getUtenteById(id);
        utenteRepository.deleteById(id);
    }

    public UtenteResponse updateUtente(Long id, UtenteRequest utenteRequest) {
        inputValidation(utenteRequest);

        getUtenteById(id);

        Utente utente = utenteConverter.mapToUtente(utenteRequest);
        utente.setId(id);

        utenteRepository.saveAndFlush(utente);
        return utenteConverter.mapToUtenteResponse(utente);
    }

    private void inputValidation(UtenteRequest utenteRequest) {
        if (utenteRequest.getDataNascita().isAfter(LocalDate.now())) {
            throw new InputErratoException("la data di nascita non può essere nel futuro");
        }
    }
}
