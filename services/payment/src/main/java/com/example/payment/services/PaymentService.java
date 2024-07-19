package com.example.payment.services;

import com.example.payment.entities.Payment;
import com.example.payment.exceptions.EntityNotFoundException;
import com.example.payment.repositories.PaymentRepository;
import com.example.payment.requests.PaymentRequest;
import com.example.payment.responses.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getPagamentoById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id,"Pagamento"));
    }

    public PaymentResponse createPagamento(PaymentRequest paymentRequest) {
        //TODO  finisci il metodo e crea PaymentResponse e PaymentRequest
        return null;
    }
}
