package com.davidGorraiz.repository;

import com.davidGorraiz.model.Genre;

import java.util.List;

public interface GenreRepository {
    public List<Genre> findAll();
    public Genre findById(int id);
    public void save(Genre genre);
    public void update(int id, Genre genre);
    public void delete(int id);
}
