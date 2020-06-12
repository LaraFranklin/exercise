package com.example.exercise.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Desarrollador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "nombres_completos", length = 100, nullable = false)
   private String nombres_completos;

    @Basic
    @Column(name = "link_github", length = 150, nullable = false)
    private String link_github;

    @ManyToMany
    @JoinTable(name = "tecnologias_conocidas",
                joinColumns =  @JoinColumn(name = "desarrollador_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "tecnologia_id", referencedColumnName = "id")
    )

    private Set<Tecnologia> tecnologias_conocidas = new HashSet<>();

    public Desarrollador(String nombres_completos, String link_github) {
        this.id = null;
        this.nombres_completos = nombres_completos;
        this.link_github = link_github;

    }


}
