package com.example.prodotti.controllers;

import com.example.prodotti.exceptions.EntityNotFoundException;
import com.example.prodotti.handler.GenericResponse;
import com.example.prodotti.requests.ProdottoPurchaseRequest;
import com.example.prodotti.requests.ProdottoRequest;
import com.example.prodotti.responses.ProdottoPurchaseResponse;
import com.example.prodotti.responses.ProdottoResponse;
import com.example.prodotti.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProdottoResponse> getProdottoById(@PathVariable Long id) {
        return new ResponseEntity<>(prodottoService.getProdottoResponseById(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProdottoResponse>> getAllProdotti() {
        return new ResponseEntity<>(prodottoService.getAllProdotti(), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<ProdottoResponse> createProdotto(@RequestBody ProdottoRequest prodottoRequest) {
        return new ResponseEntity<>(prodottoService.createProdotto(prodottoRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProdottoResponse> updateProdotto(@PathVariable Long id, @RequestBody ProdottoRequest prodottoRequest) {
        return new ResponseEntity<>(prodottoService.updateProdotto(id, prodottoRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteProdotto(@PathVariable Long id) {
        prodottoService.deleteProdotto(id);
        return new ResponseEntity<>(new GenericResponse("Prodotto con id:" + id +" eliminato correttamente") , HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProdottoPurchaseResponse>> purchaseProdotti(@RequestBody List<ProdottoPurchaseRequest> requestList) {
        return new ResponseEntity<>(prodottoService.purchaseProducts(requestList), HttpStatus.OK);
    }
}
