package com.example.prodotti.requests;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdottoRequest {

    private String nome;
    private String descrizione;
    private Double prezzo;
    private Integer quantita;
    private List<Long> idCategoria;

}
