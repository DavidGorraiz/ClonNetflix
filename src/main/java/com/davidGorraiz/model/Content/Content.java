package com.davidGorraiz.model.Content;

import jakarta.persistence.*;

import java.time.LocalDate;

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
