package com.davidGorraiz.model.User;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.Suscription.Suscription;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Suscription> suscriptions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Profile> profiles;

    public User() {
    }

    public User(String email, String password, String nombreCompleto, LocalDate fechaRegistro, Rol rol) {
        this.email = email;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Suscription> getSuscriptions() {
        return suscriptions;
    }

    public void setSuscriptions(List<Suscription> suscriptions) {
        this.suscriptions = suscriptions;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
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
