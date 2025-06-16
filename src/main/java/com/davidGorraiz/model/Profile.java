package com.davidGorraiz.model;

import com.davidGorraiz.model.User.User;
import jakarta.persistence.*;


@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile() {
    }

    public Profile(String nombre, String idioma, User user) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idioma='" + idioma + '\'' +
                ", userId=" + userId +
                '}';
    }
}
