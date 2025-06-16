package com.davidGorraiz.service;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.repository.ProfileRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProfileService implements ProfileRepository {

    private EntityManager em;

    public ProfileService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void findAll() {
        List<Profile> profiles = em.createQuery("select p from Profile p", Profile.class).getResultList();
        System.out.println("---- Mostrar todos los profiles ----");
        for (Profile profile : profiles) {
            System.out.println(profile);
        }
        System.out.println();
    }

    @Override
    public void findById(int id) {
        Profile profile = em.find(Profile.class, id);
        System.out.println("---- Usuario encontrado ----");
        System.out.println(profile);
        System.out.println();
    }

    @Override
    public void save(Profile profile, User user) {
        profile.setUser(user);
        System.out.println("---- Insertar profile -----");
        em.persist(profile);
        System.out.println();
    }

    @Override
    public void update(int id, Profile profile) {

    }

    @Override
    public void delete(int id) {

    }
}
