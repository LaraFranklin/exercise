package com.example.exercise.service;

import com.example.exercise.domain.Desarrollador;
import com.example.exercise.service.payload.DesarrolladorPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DesarrolladorService {

   ResponseEntity<Desarrollador> save(DesarrolladorPayload desarrollador);
   ResponseEntity<List<Desarrollador>> findAll();
   ResponseEntity<Desarrollador> put(int id, DesarrolladorPayload desarrollador);
   ResponseEntity<?> del(int id);
}
