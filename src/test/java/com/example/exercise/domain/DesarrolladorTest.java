package com.example.exercise.domain;

import com.example.exercise.TestUtil;
import com.mysql.cj.util.TestUtils;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DesarrolladorTest {

    private static final String NAME = "nombre";
    private static final String LINK = "http://www.github.com/test";
    private final Set<Tecnologia> LISTA = new HashSet<>();

    @Test
    public void equalssVerifier() throws Exception {
        Desarrollador desa1 = new Desarrollador(1,NAME, LINK, LISTA);
        Desarrollador desa2 = new Desarrollador(2, NAME, LINK, LISTA);
        assertThat(desa1).isNotEqualTo(desa2);
        desa1.getTecnologias_conocidas().add(new Tecnologia(1, "Python"));

        assertThat(desa1).isNotEqualTo(desa2);
        desa1.setTecnologias_conocidas(LISTA);
        assertThat(desa1).isNotEqualTo(desa2);
        desa2.setId(1);
        assertThat(desa1).isEqualTo(desa2);
    }
}
