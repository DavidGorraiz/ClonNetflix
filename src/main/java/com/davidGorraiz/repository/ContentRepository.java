package com.davidGorraiz.repository;

import com.davidGorraiz.model.Content.Content;

import java.util.List;

public interface ContentRepository {
    public List<Content> findAll();
    public Content findById(int id);
    public void save(Content content);
    public void update(Content content, int id);
    public void delete(int id);
}
