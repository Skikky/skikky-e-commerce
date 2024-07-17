package com.example.utenti.controllers;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.handler.GenericResponse;
import com.example.utenti.requests.UtenteRequest;
import com.example.utenti.responses.UtenteResponse;
import com.example.utenti.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/all")
    public ResponseEntity<List<UtenteResponse>> getAllUtenti() {
        return new ResponseEntity<>(utenteService.getAllUtenti(), HttpStatus.FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UtenteResponse> getUtenteResponseById(@PathVariable Long id) {
        return new ResponseEntity<>(utenteService.getUtenteResponseById(id), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<UtenteResponse> createUtente(@RequestBody UtenteRequest utenteRequest) {
        return new ResponseEntity<>(utenteService.createUtente(utenteRequest), HttpStatus.CREATED);
    }

    @PostMapping("/add-indirizzo-utente/{id}")
    public ResponseEntity<UtenteResponse> addIndirizzoToUtente(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
        return new ResponseEntity<>(utenteService.addIndirizzoToUtente(id, indirizzo), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-indirizzo-utente")
    public ResponseEntity<UtenteResponse> deleteIndirizzoUtente(@RequestParam Long utenteId, @RequestParam Long indirizzoId) {
        return new ResponseEntity<>(utenteService.deleteIndirizzoUtente(utenteId, indirizzoId), HttpStatus.CREATED);
    }

    @GetMapping("/get-indirizzi-utente{id}")
    public ResponseEntity<List<Indirizzo>> getIndirizziUtente(@PathVariable Long id) {
        return new ResponseEntity<>(utenteService.getIndirizziByUtenteId(id), HttpStatus.FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UtenteResponse> updateUtente(@PathVariable Long id, @RequestBody UtenteRequest utenteRequest) {
        return new ResponseEntity<>(utenteService.updateUtente(id, utenteRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteUtente(@PathVariable Long id) {
        utenteService.deleteUtente(id);
        return new ResponseEntity<>(new GenericResponse("Utente con id: "+id+" eliminato con successo"), HttpStatus.OK);
    }
}
