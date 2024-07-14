package com.example.utenti.requests;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UtenteRequest {

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String password;
    private String codiceFiscale;
    private List<Long> indirizziId;

}
