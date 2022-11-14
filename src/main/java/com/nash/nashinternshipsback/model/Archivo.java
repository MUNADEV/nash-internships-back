package com.nash.nashinternshipsback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nash.nashinternshipsback.model.enums.Etapas;
import com.nash.nashinternshipsback.model.enums.TipoDocumento;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Archivo {

    @Id
    private String id;

    private String nombre;

    private String url;

    private Carrera carrera;

    private Etapas etapa;

    private TipoDocumento tipoDocumento;

    @DBRef(lazy = true)
    @JsonIgnore
    private Usuario usuarioCreador;

}
