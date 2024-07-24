package com.example.payment.converters;

import com.example.payment.entities.Payment;
import com.example.payment.enums.MetodoDiPagamento;
import com.example.payment.exceptions.InputErratoException;
import com.example.payment.requests.PaymentRequest;
import com.example.payment.responses.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PayamentConverter {

    public PaymentResponse mapToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .totalAmount(payment.getTotalAmount())
                .idCarrello(payment.getIdCarrello())
                .createdDate(payment.getCreatedDate())
                .metodoDiPagamento(payment.getMetodoDiPagamento().name()) // Convertiamo l'enum in stringa
                .build();
    }

    public Payment mapToPayment(PaymentRequest paymentRequest) {
        inputValidation(paymentRequest);

        return Payment.builder()
                .totalAmount(paymentRequest.getTotalAmount())
                .idCarrello(paymentRequest.getIdCarrello())
                .metodoDiPagamento(MetodoDiPagamento.valueOf(paymentRequest.getMetodoDiPagamento())) // Convertiamo la stringa in enum
                .build();
    }

    //TODO sposta questo metodo dove servirà in futuro
    private void inputValidation(PaymentRequest paymentRequest) {
        try {
            MetodoDiPagamento.valueOf(paymentRequest.getMetodoDiPagamento());
        } catch (IllegalArgumentException e) {
            throw new InputErratoException("Invalid metodoDiPagamento value: " + paymentRequest.getMetodoDiPagamento());
        }
    }

}
