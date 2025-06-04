package com.davidGorraiz.model.Suscription;

import com.davidGorraiz.model.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscription")
public class Suscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoSuscription tipo;
    @Column(name = "precio")
    private double precio;
    @Column(name = "duracion_meses")
    private int duracionMeses;
    @Column(name = "fecha_inicio")
    private LocalDate fehcaInicio;
    @Column(name = "fecha_fin")
    private LocalDate fehcaFin;
    @Column(name = "activo")
    private short activo;
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Suscription() {
    }

    public Suscription(TipoSuscription tipo, double precio, int duracionMeses, LocalDate fehcaInicio, LocalDate fehcaFin, short activo) {
        this.tipo = tipo;
        this.precio = precio;
        this.duracionMeses = duracionMeses;
        this.fehcaInicio = fehcaInicio;
        this.fehcaFin = fehcaFin;
        this.activo = activo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoSuscription getTipo() {
        return tipo;
    }

    public void setTipo(TipoSuscription tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public LocalDate getFehcaInicio() {
        return fehcaInicio;
    }

    public void setFehcaInicio(LocalDate fehcaInicio) {
        this.fehcaInicio = fehcaInicio;
    }

    public LocalDate getFehcaFin() {
        return fehcaFin;
    }

    public void setFehcaFin(LocalDate fehcaFin) {
        this.fehcaFin = fehcaFin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Suscription{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", precio=" + precio +
                ", duracionMeses=" + duracionMeses +
                ", fehcaInicio=" + fehcaInicio +
                ", fehcaFin=" + fehcaFin +
                ", activo=" + activo +
                ", userId=" + userId +
                '}';
    }
}
