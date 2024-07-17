package com.example.ordini.repositories;

import com.example.ordini.entities.Carrello;
import com.example.ordini.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {
}
