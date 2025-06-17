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
    public List<Profile> findAll() {
        List<Profile> profiles = em.createQuery("select p from Profile p", Profile.class).getResultList();
        System.out.println("---- Mostrar todos los profiles ----");
        for (Profile profile : profiles) {
            System.out.println(profile);
        }
        System.out.println();
        return profiles;
    }

    @Override
    public Profile findById(int id) {
        Profile profile = em.find(Profile.class, id);
        System.out.println("---- Usuario encontrado ----");
        System.out.println(profile);
        System.out.println();
        return profile;
    }

    public Profile findByNombreAndIdioma(String nombre, String idioma) {
        Profile profile = em.createQuery("select p from Profile p where p.nombre = :nombre and p.idioma = :idioma", Profile.class)
                .setParameter("nombre", nombre)
                .setParameter("idioma", idioma)
                .getSingleResult();
        System.out.println("---- Usuario encontrado ----");
        System.out.println(profile);
        System.out.println();
        return profile;
    }

    @Override
    public void save(Profile profile, User user) {
        profile.setUser(user);
        System.out.println("---- Insertar profile -----");
        em.persist(profile);
        System.out.println();
    }

    @Override
    public void update(int id, Profile newProfile) {
        Profile profile = em.find(Profile.class, id);
        if (profile != null) {
            profile.setNombre(newProfile.getNombre());
            profile.setIdioma(newProfile.getIdioma());
            profile.setUser(newProfile.getUser());
            System.out.println("---- Actualizar profile -----");
            em.merge(profile);
            System.out.println();
        }else {
            System.out.println("El usuario que quiere actualizar no existe");
        }
    }

    @Override
    public void delete(int id) {
        Profile profile = em.find(Profile.class, id);
        System.out.println("---- Eliminar profile -----");
        em.remove(profile);
        System.out.println();
    }
}
