package com.example.ordini.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Carrello carrello;
    @Column(nullable = false)
    private Long idProdotto;
    @Column(nullable = false)
    @Check(constraints = "quantita >= 0")
    private Integer quantita;

}
