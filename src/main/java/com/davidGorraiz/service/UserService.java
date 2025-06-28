package com.davidGorraiz.service;

import com.davidGorraiz.model.User.User;
import com.davidGorraiz.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UserService implements UserRepository {

    private EntityManager em;

    public UserService(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        System.out.println("---- Listar usuarios ----");
        users.forEach(System.out::println);
        System.out.println();
        return users;
    }

    @Override
    public User findById(int id) {
        User user = em.find(User.class, id);
        System.out.println("---- Usuario encontrado ----");
        System.out.println(user);
        System.out.println();
        return user;
    }

    public User findByEmail(String email) {
        try {
            User user = em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
            System.out.println("---- Usuario encontrado ----");
            System.out.println(user);
            System.out.println();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        System.out.println("---- Insertar usuario ----");
        em.persist(user);
        System.out.println();
    }

    @Override
    public void update(int id, User newUser) {
        User user = em.find(User.class, id);
        if (user != null) {
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setNombreCompleto(newUser.getNombreCompleto());
            user.setFechaRegistro(newUser.getFechaRegistro());
            user.setRol(newUser.getRol());
            System.out.println("---- Actualizar usuario ----");
            em.merge(user);
            System.out.println();
        } else {
            System.out.println("El usuario que se quiere actualizar no existe-");
            System.out.println();
        }
    }

    @Override
    public void delete(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            System.out.println("---- Eliminar usuario ----");
            em.remove(user);
        }else {
            System.out.println("El usuario que quiere eliminar no existe-");
        }
    }
}
