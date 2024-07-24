package com.example.payment.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    private Double totalAmount;
    private Long idCarrello;
    private String metodoDiPagamento;

}
