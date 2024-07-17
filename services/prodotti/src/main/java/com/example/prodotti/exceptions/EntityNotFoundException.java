package com.example.prodotti.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private Long entityId;
    private String entityName;

    @Override
    public String getMessage() {
        return String.format("%s non trovato con id: %d",entityName,entityId);
    }
}
