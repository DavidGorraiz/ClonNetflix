package com.davidGorraiz.service;

import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.repository.SuscriptionRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SuscriptionService  implements SuscriptionRepository {

    private EntityManager em;

    public SuscriptionService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void findAll() {
        List<Suscription> suscriptions = em.createQuery("select s from Suscription s ", Suscription.class).getResultList();
        System.out.println("---- Listar suscripciones ----");
        for (Suscription suscription : suscriptions) {
            System.out.println(suscription);
        }
        System.out.println();
    }

    @Override
    public void findById(int id) {
        Suscription suscription = em.find(Suscription.class, id);
        System.out.println("---- Suscripcion encontrada ----");
        System.out.println(suscription);
        System.out.println();
    }

    @Override
    public void save(Suscription suscription, User user) {
        suscription.setUser(user);
        System.out.println("---- Insertar suscripcion ----");
        em.persist(suscription);
        System.out.println();
    }

    @Override
    public void update(int id, Suscription suscription) {

    }

    @Override
    public void delete(int id) {

    }
}
