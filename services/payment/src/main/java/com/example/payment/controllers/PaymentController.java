package com.example.payment.controllers;

import com.example.payment.entities.Payment;
import com.example.payment.requests.PaymentRequest;
import com.example.payment.responses.PaymentResponse;
import com.example.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Payment> getPagamentoById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getPagamentoById(id), HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.createPagamento(paymentRequest), HttpStatus.CREATED);
    }

}
