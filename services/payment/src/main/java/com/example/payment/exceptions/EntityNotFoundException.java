package com.example.payment.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private Long entityId;
    private String entityName;

    @Override
    public String getMessage() {
        return String.format("%s non trovato con id: %d",entityName,entityId);
    }
}
