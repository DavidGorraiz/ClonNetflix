package com.davidGorraiz.model;

import com.davidGorraiz.model.ContentGenre.ContentGenre;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE)
    private List<ContentGenre> contentGenres;

    public Genre() {
    }

    public Genre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ContentGenre> getContentGenres() {
        return contentGenres;
    }

    public void setContentGenres(List<ContentGenre> contentGenres) {
        this.contentGenres = contentGenres;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
