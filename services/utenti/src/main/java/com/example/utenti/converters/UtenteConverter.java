package com.example.utenti.converters;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.entities.Utente;
import com.example.utenti.requests.UtenteRequest;
import com.example.utenti.responses.UtenteResponse;
import com.example.utenti.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UtenteConverter {

    @Autowired
    private IndirizzoService indirizzoService;

    public UtenteResponse mapToUtenteResponse(Utente utente) {
        return UtenteResponse.builder()
                .id(utente.getId())
                .nome(utente.getNome())
                .cognome(utente.getCognome())
                .dataNascita(utente.getDataNascita())
                .email(utente.getEmail())
                .password(utente.getPassword())
                .codiceFiscale(utente.getCodiceFiscale())
                .indirizziId(utente.getIndirizzi().stream()
                        .map(Indirizzo::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public Utente mapToUtente(UtenteRequest utenteRequest) {
        return Utente.builder()
                .nome(utenteRequest.getNome())
                .cognome(utenteRequest.getCognome())
                .dataNascita(utenteRequest.getDataNascita())
                .email(utenteRequest.getEmail())
                .password(utenteRequest.getPassword())
                .codiceFiscale(utenteRequest.getCodiceFiscale())
                .build();
    }
}
