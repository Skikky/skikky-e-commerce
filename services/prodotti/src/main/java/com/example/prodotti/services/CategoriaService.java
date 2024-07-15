package com.example.prodotti.services;

import com.example.prodotti.entities.Categoria;
import com.example.prodotti.exceptions.EntityNotFoundException;
import com.example.prodotti.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
    }

    public List<Categoria> getAllCategorie() {
        return categoriaRepository.findAll();
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.saveAndFlush(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria newCategoria) {
        getCategoriaById(id);
        Categoria oldCategoria = Categoria.builder()
                .id(id)
                .nome(newCategoria.getNome())
                .descrizione(newCategoria.getDescrizione())
                .build();

        return categoriaRepository.saveAndFlush(oldCategoria);
    }

    public void deleteCategoria(Long id) {
        getCategoriaById(id);
        categoriaRepository.deleteById(id);
    }
}
