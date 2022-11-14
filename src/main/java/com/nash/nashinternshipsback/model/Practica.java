package com.nash.nashinternshipsback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nash.nashinternshipsback.model.enums.Etapas;
import com.nash.nashinternshipsback.model.enums.TipoPractica;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Practica {

    @Id
    private String id;

    private String fechaInicio;

    private String fechaTermino;

    private List<Etapas> etapas = new ArrayList<>();

    private TipoPractica tipoPractica;

    private CentroPractica centroPractica;

    @DBRef(lazy = true)
    @JsonIgnore
    private Usuario practicante;

    @DBRef(lazy = true)
    @JsonIgnore
    private Responsable responsable;


}
