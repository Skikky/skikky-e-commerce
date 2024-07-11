package com.example.prodotti.converters;

import com.example.prodotti.entities.Categoria;
import com.example.prodotti.responses.CategoriaResponse;

public class CategoriaConverter {

    public static CategoriaResponse mapToCategoriaResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descrizione(categoria.getDescrizione())
                .build();
    }

    public static Categoria mapToCategoria(CategoriaResponse categoriaResponse) {
        return Categoria.builder()
                .id(categoriaResponse.getId())
                .nome(categoriaResponse.getNome())
                .descrizione(categoriaResponse.getDescrizione())
                .build();
    }
}
