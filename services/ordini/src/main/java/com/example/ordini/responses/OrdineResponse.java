package com.example.ordini.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdineResponse {

    private Long idProdotto;
    private Integer quantita;

}
