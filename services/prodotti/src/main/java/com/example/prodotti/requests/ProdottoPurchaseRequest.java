package com.example.prodotti.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdottoPurchaseRequest {

    private Long idProdotto;
    private Integer quantita;

}
