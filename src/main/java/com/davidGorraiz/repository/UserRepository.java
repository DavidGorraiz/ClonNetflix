package com.davidGorraiz.repository;


import com.davidGorraiz.model.User.User;

public interface UserRepository {
    public void findAll();
    public User findById(int id);
    public void save(User user);
    public void update(int id, User user);
    public void delete(int id);
}
