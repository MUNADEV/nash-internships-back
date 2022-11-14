package com.nash.nashinternshipsback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nash.nashinternshipsback.model.enums.Etapas;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document
public class Usuario {
    @Id
    private String id;
    @NotNull
    private String nombre;
    @NotNull
    private String rut;
    @NotNull
    private String correo;
    @NotNull
    private String password;

    private String tokenPassword;

    private Etapas etapaActual;

    //Coordinador
    @DBRef(lazy = true)
    @JsonIgnore
    private Evaluacion evaluacion;

    private String facultad;

    //Practicante
    @DBRef(lazy = true)
    private List<Archivo> archivos = new ArrayList<>();

    @DBRef(lazy = true)
    @JsonIgnore
    private Practica practica;

    @DBRef(lazy = true)
    @JsonIgnore
    private Carrera carrera;

    private int nivel;

    @NotNull
    @DBRef(lazy = true)
    @JsonIgnore
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {}

    public Usuario(@NotNull String nombre, @NotNull String rut, @NotNull String correo, @NotNull String password) {
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.password = password;
    }

}
