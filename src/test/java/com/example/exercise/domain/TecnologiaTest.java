package com.example.exercise.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TecnologiaTest {

    private static final String TEC = "python";

    @Test
    public void EquialVerifier(){
        Tecnologia tec1 = new Tecnologia(1, TEC);
        Tecnologia tec2 = new Tecnologia(1, TEC);
        assertThat(tec1).isEqualTo(tec2);
        tec1.setId(2);
        assertThat(tec1).isNotEqualTo(tec2);

    }
}
