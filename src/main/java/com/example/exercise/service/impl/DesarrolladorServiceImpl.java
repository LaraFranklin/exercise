package com.example.exercise.service.impl;

import com.example.exercise.domain.Desarrollador;
import com.example.exercise.domain.Tecnologia;
import com.example.exercise.repository.DesarrolladorRepository;
import com.example.exercise.repository.TecnologiaRepository;
import com.example.exercise.service.DesarrolladorService;
import com.example.exercise.service.payload.DesarrolladorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DesarrolladorServiceImpl implements DesarrolladorService {

    @Autowired
    DesarrolladorRepository desarrolladorRepository;

    @Autowired
    TecnologiaRepository tecnologiaRepository;

    @Override
    public ResponseEntity<Desarrollador> save(DesarrolladorPayload desarrollador) {
        Set<Tecnologia> lista = new HashSet<>();
        for (String tec : desarrollador.getTecnologias_conocidas()) {
            lista.add(tecnologiaRepository.saveAndFlush(new Tecnologia(null, tec)));

        }
        Desarrollador desa = new Desarrollador(null, desarrollador.getNombres_completos(), desarrollador.getLink_github(), lista);
        return new ResponseEntity<>(desarrolladorRepository.save(desa), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Desarrollador>> findAll() {
        return new ResponseEntity<>(desarrolladorRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Desarrollador> put(int id, DesarrolladorPayload desarrollador) {
        if(id == 0){
            throw  new IllegalArgumentException("El id no puede estar vacio");
        }
        Desarrollador desa = desarrolladorRepository.findById(id).get();
        for(String tec: desarrollador.getTecnologias_conocidas()){
            if(!tecnologiaRepository.findByTecnologia(tec).isPresent()){
                desa.getTecnologias_conocidas().add(tecnologiaRepository.save(new Tecnologia(null, tec)));
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> del(int id) {
        desarrolladorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
