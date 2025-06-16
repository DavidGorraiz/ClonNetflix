package com.davidGorraiz.repository;

import com.davidGorraiz.model.Genre;
import com.davidGorraiz.model.Profile;

public interface ProfileRepository {
    public void findAll();
    public void findById(int id);
    public void save(Profile profile);
    public void update(int id, Profile profile);
    public void delete(int id);
}
