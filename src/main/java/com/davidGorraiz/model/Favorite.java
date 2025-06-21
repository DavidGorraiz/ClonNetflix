package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "profile_id", insertable = false, updatable = false)
    private int profileId;
    @Column(name = "content_id", insertable = false, updatable = false)
    private int contentId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id")
    private Content content;

    public Favorite() {
    }

    public Favorite(int profileId, int contentId, Profile profile, Content content) {
        this.profileId = profileId;
        this.contentId = contentId;
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
        return "Favorite{" +
                "id=" + id +
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
        Favorite favorite = (Favorite) o;
        return id == favorite.id && profileId == favorite.profileId && contentId == favorite.contentId && Objects.equals(profile, favorite.profile) && Objects.equals(content, favorite.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, contentId, profile, content);
    }
}
