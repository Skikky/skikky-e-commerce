package com.example.ordini.controllers;

import com.example.ordini.requests.CarrelloRequest;
import com.example.ordini.responses.CarrelloResponse;
import com.example.ordini.services.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carrello")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CarrelloResponse> getCarrelloById(@PathVariable Long id) {
        return new ResponseEntity<>(carrelloService.getCarrelloResponseById(id), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<CarrelloResponse> createCarrello(@RequestBody CarrelloRequest carrelloRequest) {
        return new ResponseEntity<>(carrelloService.createCarrello(carrelloRequest), HttpStatus.CREATED);
    }
}
