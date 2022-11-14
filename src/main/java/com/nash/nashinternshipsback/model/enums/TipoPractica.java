package com.nash.nashinternshipsback.model.enums;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public enum TipoPractica {
    PRACTICA_I,PRACTICA_II,PRACTICA_III,PRACTICA_IV,PRACTICA_PROFESIONAL;
}
