package org.example.alugueldecarros.repository;

import org.example.alugueldecarros.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}