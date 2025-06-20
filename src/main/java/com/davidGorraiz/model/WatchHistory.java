package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "watch_history")
public class WatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "profile_id", insertable = false, updatable = false)
    private int profileId;
    @Column(name = "content_id", insertable = false, updatable = false)
    private int contentId;
    @Column(name = "fecha_visto")
    private LocalDateTime fechaVisto;
    @Column(name = "duracion_vista")
    private int duracionVista;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id")
    private Content content;

    public WatchHistory() {
    }

    public WatchHistory(LocalDateTime fechaVisto, int duracionVista, Content content, Profile profile) {
        this.fechaVisto = fechaVisto;
        this.duracionVista = duracionVista;
        this.content = content;
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

    public LocalDateTime getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(LocalDateTime fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    public int getDuracionVista() {
        return duracionVista;
    }

    public void setDuracionVista(int duracionVista) {
        this.duracionVista = duracionVista;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "WatchHistory{" +
                "id=" + id +
                ", fechaVisto=" + fechaVisto +
                ", duracionVista=" + duracionVista +
                ", profileId=" + profileId +
                ", contentId=" + contentId +
                ", profile=" + profile +
                ", content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchHistory that = (WatchHistory) o;
        return id == that.id && duracionVista == that.duracionVista && profileId == that.profileId && contentId == that.contentId && Objects.equals(fechaVisto, that.fechaVisto) && Objects.equals(profile, that.profile) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaVisto, duracionVista, profileId, contentId, profile, content);
    }
}
