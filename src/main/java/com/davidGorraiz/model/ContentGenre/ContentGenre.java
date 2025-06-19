package com.davidGorraiz.model.ContentGenre;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Genre;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "content_genre")
@IdClass(ContentGenreKey.class)
public class ContentGenre {

    @Column(name = "content_id", insertable = false, updatable = false)
    private int contentId;
    @Column(name = "genre_id", insertable = false, updatable = false)
    private int genreId;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "content_id")
    private Content content;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public ContentGenre() {
    }

    public ContentGenre(int contentId, int genreId) {
        this.contentId = contentId;
        this.genreId = genreId;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "ContentGenre{" +
                "contentId=" + contentId +
                ", genreId=" + genreId +
                ", content=" + content +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentGenre that = (ContentGenre) o;
        return contentId == that.contentId && genreId == that.genreId && Objects.equals(content, that.content) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, genreId, content, genre);
    }
}
