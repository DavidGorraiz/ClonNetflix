package com.davidGorraiz.repository;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;

import java.util.List;

public interface ProfileRepository{
    public List<Profile> findAll();
    public Profile findById(int id);
    public void save(Profile profile, User user);
    public void update(int id, Profile profile);
    public void delete(int id);
}
