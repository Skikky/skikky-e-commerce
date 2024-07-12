package com.example.prodotti.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdottoPurchaseResponse {

    private Long idProdotto;
    private Integer quantita;
    private String nome;

}
