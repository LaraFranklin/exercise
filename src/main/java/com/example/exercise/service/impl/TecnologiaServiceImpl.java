package com.example.exercise.service.impl;

import com.example.exercise.domain.Tecnologia;
import com.example.exercise.service.TecnologiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnologiaServiceImpl implements TecnologiaService {


    @Override
    public ResponseEntity<Tecnologia> save(Tecnologia tecnologia) {
        return null;
    }

    @Override
    public ResponseEntity<List<Tecnologia>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Tecnologia> put(int id, Tecnologia tecnologia) {
        return null;
    }

    @Override
    public ResponseEntity<?> del(int id) {
        return null;
    }
}
