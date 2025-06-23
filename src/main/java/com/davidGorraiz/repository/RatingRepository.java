package com.davidGorraiz.repository;

import com.davidGorraiz.model.Rating;

import java.util.List;

public interface RatingRepository {
    public List<Rating> findAll();
    public Rating findById(int id);
    public void save(Rating rating);
    public void update(int id,Rating rating);
    public void delete(int id);
}
