package com.davidGorraiz.repository;

import com.davidGorraiz.model.Episode;

import java.util.List;

public interface EpisodeRepository {
    public List<Episode> findAll();
    public Episode findById(int id);
    public void save(Episode episode);
    public void update(int id,Episode episode);
    public void delete(Episode episode);
}
