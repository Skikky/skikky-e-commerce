package com.example.ordini.repositories;

import com.example.ordini.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    @Query("SELECT o FROM Ordine o WHERE o.carrello.id = :carrelloId")
    List<Ordine> findOrdiniByCarrelloId(Long carrelloId);
}
