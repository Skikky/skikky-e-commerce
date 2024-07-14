package com.example.utenti.controllers;

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
