package com.example.prodotti.responses;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaResponse {

    private Long id;
    private String nome;
    private String descrizione;
    private List<Long> idProdotti;

}
