package com.nash.nashinternshipsback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Carrera {

    @Id
    private String id;

    private String nombre;

    @DBRef(lazy = true)
    @JsonIgnore
    private Archivo informativos;

    @DBRef(lazy = true)
    @JsonIgnore
    private Archivo plantillas;

    @DBRef(lazy = true)
    @JsonIgnore
    private Usuario coordinador;

    @DBRef(lazy = true)
    @JsonIgnore
    private List<Usuario> practicantes;

}
