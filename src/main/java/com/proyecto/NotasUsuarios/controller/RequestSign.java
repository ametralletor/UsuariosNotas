package com.proyecto.NotasUsuarios.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RequestSign {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String contrasenna;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
    
}
