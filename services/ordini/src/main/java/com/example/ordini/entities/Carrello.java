package com.example.ordini.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "carrello")
    private List<Ordine> ordineList;
    @Column(nullable = false)
    private Long idUtente;
    @Column(nullable = false)
    @Check(constraints = "total_amount >= 0")
    private Double totalAmount;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column
    private LocalDateTime lastUpdateDate;

}
