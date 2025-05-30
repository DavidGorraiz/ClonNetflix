package com.davidGorraiz.repository;

import com.davidGorraiz.model.Genre;

public interface GenreRepository {
    public void findAll();
    public void findById(int id);
    public void save(Genre genre);
    public void update(int id, Genre genre);
    public void delete(int id);
}
