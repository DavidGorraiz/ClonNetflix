package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "profile_id", insertable = false, updatable = false)
    private int profileId;
    @Column(name = "content_id", insertable = false, updatable = false)
    private int contentId;
    @Column(name = "valor")
    private int valor;
    @Column(name = "comentario")
    private String comentario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id")
    private Content content;

    public Rating() {
    }

    public Rating(int valor, String comentario, Profile profile, Content content) {
        this.valor = valor;
        this.comentario = comentario;
        this.profile = profile;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", profileId=" + profileId +
                ", contentId=" + contentId +
                ", valor=" + valor +
                ", comentario='" + comentario + '\'' +
                ", profile=" + profile +
                ", content=" + content +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id && profileId == rating.profileId && contentId == rating.contentId && valor == rating.valor && Objects.equals(comentario, rating.comentario) && Objects.equals(profile, rating.profile) && Objects.equals(content, rating.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, contentId, valor, comentario, profile, content);
    }
}
