package com.example.exercise.controller;

import com.example.exercise.domain.Desarrollador;
import com.example.exercise.service.DesarrolladorService;
import com.example.exercise.service.payload.DesarrolladorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/developers")
public class DesarrolladorController {

    @Autowired
    DesarrolladorService desarrolladorService;


    /**
     *
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<Desarrollador>> findAll(){
        return desarrolladorService.findAll();
    }

    /***
     *
     * @param desarrollador
     * @return
     */

    @PostMapping("")
    public ResponseEntity<Desarrollador> add(
            @Valid @RequestBody DesarrolladorPayload desarrollador
    ){
        return desarrolladorService.save(desarrollador);
    }

    /**
     *
     * @param id
     * @param desarrollador
     * @return
     */

    @PutMapping("{id}")
    public ResponseEntity<Desarrollador> put(
            @PathVariable Integer id,
            @RequestBody DesarrolladorPayload desarrollador
    ){
        return desarrolladorService.put(id, desarrollador);
    }

    /**
     *
     * @param id
     * @return
     */

    @DeleteMapping("{id}")
    public ResponseEntity<?> del(
            @PathVariable Integer id
    ){
        return desarrolladorService.del(id);
    }
}
