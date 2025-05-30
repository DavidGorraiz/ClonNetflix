package com.davidGorraiz.model.User;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

    public User() {
    }

    public User(String email, String password, String nombreCompleto, Date fechaRegistro, Rol rol) {
        this.email = email;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", rol=" + rol +
                '}';
    }
}
