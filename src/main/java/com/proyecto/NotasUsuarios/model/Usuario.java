package com.proyecto.NotasUsuarios.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nombre debe ser obligatorio")
    @Size(min = 2, max = 50, message="El nombre debe tener un tamaño entre 2 a 50 caracteres")
    private String nombre;

    @Email(message = "El correo electronico debe ser valido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "la contraseña es obligatoria")
    @Size(min = 8, message = "la contraseña debe tener un minimo de 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos una mayuscula, una minuscula, un numero y un caracter especial")
    @Column(nullable = false)
    private String contrasenna;

    @OneToMany(mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<Notas> notas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre, String email, String contrasenna) {
        this.nombre = nombre;
        this.email = email;
        this.contrasenna = contrasenna;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Notas> getNotas() {
        return notas;
    }

    public void setNotas(List<Notas> notas) {
        this.notas = notas;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
}
