package com.davidGorraiz.repository;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;

public interface ProfileRepository{
    public void findAll();
    public void findById(int id);
    public void save(Profile profile, User user);
    public void update(int id, Profile profile);
    public void delete(int id);
}
