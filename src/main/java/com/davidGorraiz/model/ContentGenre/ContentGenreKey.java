package com.davidGorraiz.model.ContentGenre;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Genre;

public class ContentGenreKey {
    private Content content;
    private Genre genre;

    public ContentGenreKey() {
    }

    public ContentGenreKey(Content content, Genre genre) {
        this.content = content;
        this.genre = genre;
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
}
