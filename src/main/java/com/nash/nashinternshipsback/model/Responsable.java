package com.nash.nashinternshipsback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Responsable{

    @Id
    private String id;

    private String nombre;

    private String correo;

    private String rut;

    @DBRef(lazy = true)
    @JsonIgnore
    private CentroPractica centroPractica;

    public Responsable(String rut, String nombre, String correo) {
    }
}
