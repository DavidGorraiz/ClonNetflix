package com.davidGorraiz.model.Content;

import com.davidGorraiz.model.ContentGenre.ContentGenre;
import com.davidGorraiz.model.Episode;
import com.davidGorraiz.model.Favorite;
import com.davidGorraiz.model.Rating;
import com.davidGorraiz.model.WatchHistory;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoContent tipoContent;
    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "clasificacion")
    private String clasificacion;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Episode> episodes;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<ContentGenre> contentGenres;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<WatchHistory> watchHistories;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE)
    private List<Rating> ratings;

    public Content() {
    }

    public Content(String titulo, String descripcion, TipoContent tipoContent, LocalDate fechaLanzamiento, int duracion, String clasificacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoContent = tipoContent;
        this.fechaLanzamiento = fechaLanzamiento;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<WatchHistory> getWatchHistories() {
        return watchHistories;
    }

    public void setWatchHistories(List<WatchHistory> watchHistories) {
        this.watchHistories = watchHistories;
    }

    public List<ContentGenre> getContentGenres() {
        return contentGenres;
    }

    public void setContentGenres(List<ContentGenre> contentGenres) {
        this.contentGenres = contentGenres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoContent getTipoContent() {
        return tipoContent;
    }

    public void setTipoContent(TipoContent tipoContent) {
        this.tipoContent = tipoContent;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public int getId() {
        return id;
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

    public TipoContent getTipo() {
        return tipoContent;
    }

    public void setTipo(TipoContent tipoContent) {
        this.tipoContent = tipoContent;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipoContent +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", duracion=" + duracion +
                ", clasificacion='" + clasificacion + '\'' +
                '}';
    }
}
