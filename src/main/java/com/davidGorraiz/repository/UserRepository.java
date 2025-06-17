package com.davidGorraiz.repository;


import com.davidGorraiz.model.User.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll();
    public User findById(int id);
    public void save(User user);
    public void update(int id, User user);
    public void delete(int id);
}
