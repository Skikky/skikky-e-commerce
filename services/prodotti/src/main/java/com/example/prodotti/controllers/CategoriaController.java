package com.example.prodotti.controllers;

import com.example.prodotti.entities.Categoria;
import com.example.prodotti.handler.GenericResponse;
import com.example.prodotti.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaService.getCategoriaById(id), HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> getAllCategorie() {
        return new ResponseEntity<>(categoriaService.getAllCategorie(), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.createCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Categoria> createCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.updateCategoria(id, categoria), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>(new GenericResponse("Categoria con id: "+id+" cancellata con successo"), HttpStatus.OK);
    }
}
