package com.davidGorraiz.repository;

import com.davidGorraiz.model.WatchHistory;

import java.util.List;

public interface WatchHistoryRepository {
    public List<WatchHistory> findAll();
    public WatchHistory findById(int id);
    public void save(WatchHistory watchHistory);
    public void update(int id,WatchHistory watchHistory);
    public void delete(int id);
}
