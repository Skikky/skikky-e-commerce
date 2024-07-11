package com.example.prodotti.services;

import com.example.prodotti.converters.CategoriaConverter;
import com.example.prodotti.entities.Categoria;
import com.example.prodotti.exceptions.EntityNotFoundException;
import com.example.prodotti.repositories.CategoriaRepository;
import com.example.prodotti.requests.CategoriaRequest;
import com.example.prodotti.responses.CategoriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria getCategoriaById(Long id) throws EntityNotFoundException {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
    }

    public CategoriaResponse getCategoriaResponseById(Long id) throws EntityNotFoundException {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Prodotto"));
        return CategoriaConverter.mapToCategoriaResponse(categoria);
    }

    public List<CategoriaResponse> getAllCategorie() {
        List<Categoria> categorie = categoriaRepository.findAll();
        return categorie.stream()
                .map(CategoriaConverter::mapToCategoriaResponse)
                .collect(Collectors.toList());
    }

    public CategoriaResponse createCategoria(CategoriaRequest categoriaRequest) {
        Categoria categoria = Categoria.builder()
                .nome(categoriaRequest.getNome())
                .descrizione(categoriaRequest.getDescrizione())
                .build();
        categoriaRepository.saveAndFlush(categoria);
        return CategoriaConverter.mapToCategoriaResponse(categoria);
    }

    public CategoriaResponse updateCategoria(Long id, CategoriaRequest categoriaRequest) throws EntityNotFoundException {
        getCategoriaById(id);

        Categoria categoria = Categoria.builder()
                .id(id)
                .nome(categoriaRequest.getNome())
                .descrizione(categoriaRequest.getDescrizione())
                .build();
        categoriaRepository.saveAndFlush(categoria);
        return CategoriaConverter.mapToCategoriaResponse(categoria);
    }

    public void deleteCategoria(Long id) throws EntityNotFoundException {
        getCategoriaById(id);
        categoriaRepository.deleteById(id);
    }
}
