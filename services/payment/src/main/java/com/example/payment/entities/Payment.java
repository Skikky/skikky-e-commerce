package com.example.payment.entities;

import com.example.payment.enums.MetodoDiPagamento;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double totalAmount;
    @Column
    private Long idCarrello;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Column
    @Enumerated(EnumType.STRING)
    private MetodoDiPagamento metodoDiPagamento;
}
