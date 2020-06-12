package com.example.exercise.repository;

import com.example.exercise.domain.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Integer> {

    Optional<Tecnologia> findByTecnologia(String tecnologia);

}
