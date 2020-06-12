package com.example.exercise.service;

import com.example.exercise.domain.Tecnologia;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TecnologiaService {

    ResponseEntity<Tecnologia> save(Tecnologia tecnologia);
    ResponseEntity<List<Tecnologia>> findAll();
    ResponseEntity<Tecnologia> put(int id, Tecnologia tecnologia);
    ResponseEntity<?> del(int id);
}
