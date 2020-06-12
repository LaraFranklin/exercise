package com.example.exercise.service.payload;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DesarrolladorPayload {

    @NotEmpty
    @NotNull
    private String nombres_completos;

    @NotNull
    @NotEmpty
    private String link_github;

    @NotNull
    @NotEmpty
    private Set<String> tecnologias_conocidas = new HashSet<>();
}
