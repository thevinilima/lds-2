package com.example.lab2.repository;

import com.example.lab2.model.Automovel;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}
