package com.davidGorraiz.service;

import com.davidGorraiz.model.User.User;
import com.davidGorraiz.repository.UserRepository;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserService implements UserRepository {

    private EntityManager em;

    public UserService() {
        this.em = UtilEntity.getEntityManager();
    }

    @Override
    public void findAll() {
        em.getTransaction().begin();
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        System.out.println("---- Listar usuarios ----");
        users.forEach(System.out::println);
        System.out.println();
        em.getTransaction().commit();
        em.close();
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
