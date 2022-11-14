package com.nash.nashinternshipsback.dto;


/**
 * DTO-Security Clase usuario
 */
public class LoginUsuarioDTO {

        private String rut;

        private String password;

        public String getRut() {
            return rut;
        }

        public void setRut(String rut) {
            this.rut = rut;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

