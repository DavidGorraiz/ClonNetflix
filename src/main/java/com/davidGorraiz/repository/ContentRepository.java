package com.davidGorraiz.repository;

import com.davidGorraiz.model.Content.Content;

public interface ContentRepository {
    public void findAll();
    public void findById(int id);
    public void save(Content content);
    public void update(Content content, int id);
    public void delete(int id);
}
