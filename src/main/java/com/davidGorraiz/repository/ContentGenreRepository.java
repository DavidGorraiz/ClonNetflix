package com.davidGorraiz.repository;

import com.davidGorraiz.model.ContentGenre.ContentGenre;
import com.davidGorraiz.model.ContentGenre.ContentGenreKey;

import java.util.List;

public interface ContentGenreRepository {
    public List<ContentGenre> findAll();
    public ContentGenre findById(ContentGenreKey id);
    public void save(ContentGenre contentGenre);
    public void delete(ContentGenreKey id);
}
