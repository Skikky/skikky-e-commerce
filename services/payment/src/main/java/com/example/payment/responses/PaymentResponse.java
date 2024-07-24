package com.example.payment.responses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;
    private Double totalAmount;
    private Long idCarrello;
    private LocalDateTime createdDate;
    private String metodoDiPagamento;

}
