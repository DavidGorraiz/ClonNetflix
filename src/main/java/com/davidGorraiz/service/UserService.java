package com.davidGorraiz.service;

import com.davidGorraiz.model.User.User;
import com.davidGorraiz.repository.UserRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserService implements UserRepository {

    private EntityManager em;

    public UserService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void findAll() {
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        users.forEach(System.out::println);
    }

    @Override
    public void findById(int id) {

    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }
}
