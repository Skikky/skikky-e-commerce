package com.example.ordini.prodotto;

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
