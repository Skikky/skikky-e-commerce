package com.example.prodotti.controllers;

import com.example.prodotti.exceptions.EntityNotFoundException;
import com.example.prodotti.requests.ProdottoRequest;
import com.example.prodotti.responses.ProdottoResponse;
import com.example.prodotti.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProdottoResponse> getProdottoById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(prodottoService.getProdottoResponseById(id));
    }

    @GetMapping("/all")
    public List<ProdottoResponse> getAllProdotti() {
        return prodottoService.getAllProdotti();
    }

    @PostMapping("/create")
    public ResponseEntity<ProdottoResponse> createProdotto(@RequestBody ProdottoRequest prodottoRequest) throws EntityNotFoundException {
        return ResponseEntity.ok(prodottoService.createProdotto(prodottoRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProdottoResponse> updateProdotto(@PathVariable Long id, @RequestBody ProdottoRequest prodottoRequest) throws EntityNotFoundException {
        return ResponseEntity.ok(prodottoService.updateProdotto(id, prodottoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProdotto(@PathVariable Long id) throws EntityNotFoundException {
        prodottoService.deleteProdotto(id);
    }
}
