package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "numero_episodio")
    private int numeroEpisodio;
    @Column(name = "temporada")
    private int temporada;
    @Column(name = "duracion")
    private int duracion;
    @Column(name = "content_id", insertable = false, updatable = false)
    private int contentId;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    public Episode() {
    }

    public Episode(String titulo, String descripcion, int numeroEpisodio, int temporada, int duracion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.duracion = duracion;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", temporada=" + temporada +
                ", duracion=" + duracion +
                ", contentId=" + contentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episode episode = (Episode) o;
        return id == episode.id && numeroEpisodio == episode.numeroEpisodio && temporada == episode.temporada && duracion == episode.duracion && contentId == episode.contentId && Objects.equals(titulo, episode.titulo) && Objects.equals(descripcion, episode.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, numeroEpisodio, temporada, duracion, contentId);
    }
}
