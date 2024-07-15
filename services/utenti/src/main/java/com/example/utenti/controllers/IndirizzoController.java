package com.example.utenti.controllers;

import com.example.utenti.entities.Indirizzo;
import com.example.utenti.handler.GenericResponse;
import com.example.utenti.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/indirizzo")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("/all")
    public ResponseEntity<List<Indirizzo>> getAllIndirizzi() {
        return new ResponseEntity<>(indirizzoService.getAllIndirizzi(), HttpStatus.FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Indirizzo> getIndirizzoById(@PathVariable Long id) {
        return new ResponseEntity<>(indirizzoService.getIndirizzoById(id), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Indirizzo> createIndirizzo(@RequestBody Indirizzo indirizzo) {
        return new ResponseEntity<>(indirizzoService.createIndirizzo(indirizzo), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteIndirizzo(@PathVariable Long id) {
        indirizzoService.deleteIndirizzo(id);
        return new ResponseEntity<>(new GenericResponse("Indirizzo con id: "+id+" cancellato correttamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Indirizzo> updateIndirizzo(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
        return new ResponseEntity<>(indirizzoService.updateIndirizzo(id, indirizzo), HttpStatus.CREATED);
    }
}
