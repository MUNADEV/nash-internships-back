package com.nash.nashinternshipsback.dto;


import com.nash.nashinternshipsback.model.Carrera;
import com.nash.nashinternshipsback.model.enums.Etapas;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO Clase usuario
 */
public class NuevoUsuarioDTO {

        @NotBlank
        private String nombre;
        @NotBlank
        private String rut;
        @Email
        private String correo;
        @NotBlank
        private String password;

        private Carrera carrera;

        private Etapas etapaActual;

        private int nivel;
        private Set<String> roles = new HashSet<>();

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRut() {
            return rut;
        }

        public void setRut(String rut) {
            this.rut = rut;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Set<String> getRoles() {
            return roles;
        }

        public void setRoles(Set<String> roles) {
            this.roles = roles;
        }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Etapas getEtapaActual() {
        return etapaActual;
    }

    public void setEtapaActual(Etapas etapaActual) {
        this.etapaActual = etapaActual;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}

