package com.example.utenti.services;

import com.example.utenti.converters.UtenteConverter;
import com.example.utenti.entities.Indirizzo;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private UtenteConverter utenteConverter;
    @Autowired
    private IndirizzoService indirizzoService;

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

    public UtenteResponse addIndirizzoToUtente(Long utenteId, Indirizzo indirizzo) {
        Utente utente = getUtenteById(utenteId);

        indirizzoService.createIndirizzo(indirizzo);
        utente.getIndirizzi().add(indirizzo);

        utenteRepository.saveAndFlush(utente);
        return utenteConverter.mapToUtenteResponse(utente);
    }

    public UtenteResponse updateIndirizzoUtente(Long utenteId, Indirizzo indirizzo) {
        Utente utente = getUtenteById(utenteId);

        Optional<Indirizzo> optionalIndirizzo = utente.getIndirizzi().stream()
                .filter(ind -> ind.getId().equals(indirizzo.getId()))
                .findFirst();

        if (optionalIndirizzo.isEmpty()) {
            throw new EntityNotFoundException(indirizzo.getId(), "Indirizzo");
        }

        Indirizzo indirizzoToUpdate = optionalIndirizzo.get();
        indirizzoService.updateIndirizzo(indirizzoToUpdate.getId(), indirizzoToUpdate);

        utenteRepository.saveAndFlush(utente);
        return utenteConverter.mapToUtenteResponse(utente);
    }

    public UtenteResponse deleteIndirizzoUtente(Long utenteId, Long indirizzoId) {
        Utente utente = getUtenteById(utenteId);

        Optional<Indirizzo> optionalIndirizzo = utente.getIndirizzi().stream()
                .filter(ind -> ind.getId().equals(indirizzoId))
                .findFirst();

        if (optionalIndirizzo.isEmpty()) {
            throw new EntityNotFoundException(indirizzoId, "Indirizzo");
        }

        indirizzoService.deleteIndirizzo(indirizzoId);

        utente.getIndirizzi().remove(optionalIndirizzo.get());

        utenteRepository.saveAndFlush(utente);
        return utenteConverter.mapToUtenteResponse(utente);
    }

    public List<Indirizzo> getIndirizziByUtenteId(Long utenteId) {
        Utente utente = getUtenteById(utenteId);

        return utente.getIndirizzi();
    }

    private void inputValidation(UtenteRequest utenteRequest) {
        if (utenteRequest.getDataNascita().isAfter(LocalDate.now())) {
            throw new InputErratoException("la data di nascita non può essere nel futuro");
        }
    }
}
