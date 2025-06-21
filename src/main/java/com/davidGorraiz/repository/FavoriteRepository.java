package com.davidGorraiz.repository;

import com.davidGorraiz.model.Favorite;

import java.util.List;

public interface FavoriteRepository {
    public List<Favorite> findAll();
    public Favorite findById(int id);
    public void save(Favorite favorite);
    public void update(int id, Favorite favorite);
    public void delete(int id);
}
