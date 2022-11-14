package com.nash.nashinternshipsback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Evaluacion {

    @Id
    private String id;

    private boolean estado;

    @DBRef(lazy = true)
    @JsonIgnore
    private Usuario practicante;

    @DBRef(lazy = true)
    @JsonIgnore
    private Usuario coordinador;
}
