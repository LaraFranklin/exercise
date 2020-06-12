package com.example.exercise.controller;

import com.example.exercise.ExerciseApplication;
import com.example.exercise.TestUtil;
import com.example.exercise.domain.Desarrollador;
import com.example.exercise.domain.Tecnologia;
import com.example.exercise.repository.DesarrolladorRepository;
import com.example.exercise.service.DesarrolladorService;
import com.example.exercise.service.payload.DesarrolladorPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = ExerciseApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class DesarrolladorControllerTest {


    private static  final int ID = 2000;
    private static final String DEFAULT_NOMBRES_COMPLETOS = "nombre";
    private static final String UPDATED_NOMBRES_COMPLETOS = "nombre Actualizado";

    private static final String DEFAULT_LINK_GITHUB = "http://www.github.com/test";
    private static final String UPDATED_LINK_GITHUB = "http://www.github.com/test2";

    private static final Set<String> LISTA = new HashSet<>();

    @Autowired
    DesarrolladorRepository desarrolladorRepository;

    @Mock
    private DesarrolladorService desarrolladorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDesarrolladorMockMvc;

    private DesarrolladorPayload desarrollador;

    public static DesarrolladorPayload createEntity(EntityManager em) {
        return new DesarrolladorPayload(DEFAULT_NOMBRES_COMPLETOS, DEFAULT_LINK_GITHUB, LISTA);
    }

    public static DesarrolladorPayload createUpdatedEntity(EntityManager em) {
        return new DesarrolladorPayload(UPDATED_NOMBRES_COMPLETOS, UPDATED_LINK_GITHUB, LISTA);
    }

    @BeforeEach
    public void initTest(){
        desarrollador = createEntity(em);
    }

    @Test
    @Transactional
    public void createDesarrollador() throws Exception {
        int databaseSizeBeforeCreate = desarrolladorRepository.findAll().size();
        // Create the Desarrollador
        restDesarrolladorMockMvc.perform(post("/developers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(desarrollador)))
                .andExpect(status().isCreated());

        // Validate the Desarrollador in the database
        List<Desarrollador> desarrolladorList = desarrolladorRepository.findAll();
        assertThat(desarrolladorList).hasSize(databaseSizeBeforeCreate + 1);
        Desarrollador testDesarrollador = desarrolladorList.get(desarrolladorList.size() - 1);
        assertThat(testDesarrollador.getNombres_completos()).isEqualTo(DEFAULT_NOMBRES_COMPLETOS);
        assertThat(testDesarrollador.getLink_github()).isEqualTo(DEFAULT_LINK_GITHUB);
    }

    @Test
    @Transactional
    public void getAllDesarrolladors() throws Exception {
        // Initialize the database
        desarrolladorRepository.saveAndFlush(new Desarrollador(
                null, desarrollador.getNombres_completos(), desarrollador.getLink_github(), null));

        // Get all the desarrolladorList
        restDesarrolladorMockMvc.perform(get("/developers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[*].nombres_completos").value(hasItem(DEFAULT_NOMBRES_COMPLETOS)))
                .andExpect(jsonPath("$.[*].link_github").value(hasItem(DEFAULT_LINK_GITHUB)));
    }


    private Set<String> converToString(Set<Tecnologia> lista){
        if(lista ==null){
            return null;
        }
        Set<String> all = new HashSet<>();
        for(Tecnologia tec: lista){
            all.add(tec.getTecnologia());
        }
        return all;
    }
    @Test
    @Transactional
    public void updateDesarrollador() throws Exception {
        // Initialize the database
        Desarrollador desa = desarrolladorRepository.saveAndFlush(new Desarrollador(
                null, desarrollador.getNombres_completos(), desarrollador.getLink_github(), null));


        int databaseSizeBeforeUpdate = desarrolladorRepository.findAll().size();

        // Update the desarrollador
        Desarrollador updatedDesarrollador = desarrolladorRepository.findById(desa.getId()).get();
        // Disconnect from session so that the updates on updatedDesarrollador are not directly saved in db
        em.detach(updatedDesarrollador);
        DesarrolladorPayload updatedDesaPayload = new DesarrolladorPayload(
                UPDATED_NOMBRES_COMPLETOS, UPDATED_LINK_GITHUB, converToString(updatedDesarrollador.getTecnologias_conocidas())
        );
//        updatedDesaPayload.setLink_github(UPDATED_LINK_GITHUB);
//        updatedDesaPayload.setNombres_completos(UPDATED_NOMBRES_COMPLETOS);

        restDesarrolladorMockMvc.perform(put("/developers/{id}", desa.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(updatedDesaPayload)))
                .andExpect(status().isOk());

        // Validate the Desarrollador in the database
        List<Desarrollador> desarrolladorList = desarrolladorRepository.findAll();
        assertThat(desarrolladorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDesarrollador() throws Exception {
        // Initialize the database
        Desarrollador desa = desarrolladorRepository.saveAndFlush(new Desarrollador(
                null, desarrollador.getNombres_completos(), desarrollador.getLink_github(), null));

        int databaseSizeBeforeDelete = desarrolladorRepository.findAll().size();

        // Delete the desarrollador
        restDesarrolladorMockMvc.perform(delete("/developers/{id}", desa.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Validate the database contains one less item
        List<Desarrollador> desarrolladorList = desarrolladorRepository.findAll();
        assertThat(desarrolladorList).hasSize(databaseSizeBeforeDelete - 1);
    }


}
