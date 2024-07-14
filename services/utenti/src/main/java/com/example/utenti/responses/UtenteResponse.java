package com.example.utenti.responses;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UtenteResponse {

    private Long id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String password;
    private String codiceFiscale;
    private List<Long> indirizziId;

}
