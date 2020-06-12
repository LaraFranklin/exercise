package com.example.exercise.domain;

import com.example.exercise.service.payload.DesarrolladorPayload;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DesarrolladorPayloadTest {

    private static final String NAME = "nombre";
    private static final String LINK = "http://www.github.com/test";
    private final Set<String> LISTA = new HashSet<>();

    @Test
    public void equalssVerifier() throws Exception {
//        TestUtil.equalsVerifier(Desarrollador.class);
//        DesarrolladorPayload desa1 = new DesarrolladorPayload(NAME, LINK, LISTA);
//        DesarrolladorPayload desa2 = new DesarrolladorPayload(NAME, LINK, LISTA);
////        assertThat(desa1).isEqualTo(desa2);
//        desa1.getTecnologias_conocidas().add("Python");
//        assertThat(desa1).isNotEqualTo(desa2);
//        desa1.setTecnologias_conocidas(LISTA);
//        assertThat(desa1).isNotEqualTo(desa2);
    }

}
