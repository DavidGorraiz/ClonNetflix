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
    public void update(int id, Suscription newSuscription) {
        Suscription suscription = em.find(Suscription.class, id);
        if (suscription != null) {
            suscription.setTipo(newSuscription.getTipo());
            suscription.setPrecio(newSuscription.getPrecio());
            suscription.setDuracionMeses(newSuscription.getDuracionMeses());
            suscription.setFehcaInicio(newSuscription.getFehcaInicio());
            suscription.setFehcaFin(newSuscription.getFehcaFin());
            suscription.setActivo(newSuscription.getActivo());
            suscription.setUser(newSuscription.getUser());
            System.out.println("---- Actualizar suscripcion ----");
            em.merge(suscription);
            System.out.println();
        }else {
            System.out.println("La suscripcion que se quiere actualizar no existe");
        }
    }

    @Override
    public void delete(int id) {
        Suscription suscription = em.find(Suscription.class, id);
        if (suscription != null) {
            System.out.println("---- Eliminar suscripcion ----");
            em.remove(suscription);
        }else {
            System.out.println("La suscripcion que se quiere eliminar no existe");
        }
    }
}
