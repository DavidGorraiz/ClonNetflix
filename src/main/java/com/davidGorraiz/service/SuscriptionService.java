package com.davidGorraiz.service;

import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.Suscription.TipoSuscription;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.repository.SuscriptionRepository;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class SuscriptionService  implements SuscriptionRepository {

    private EntityManager em;

    public SuscriptionService(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Suscription> findAll() {
        List<Suscription> suscriptions = em.createQuery("select s from Suscription s ", Suscription.class).getResultList();
        System.out.println("---- Listar suscripciones ----");
        for (Suscription suscription : suscriptions) {
            System.out.println(suscription);
        }
        System.out.println();
        return suscriptions;
    }

    @Override
    public Suscription findById(int id) {
        Suscription suscription = em.find(Suscription.class, id);
        System.out.println("---- Suscripcion encontrada ----");
        System.out.println(suscription);
        System.out.println();
        return suscription;
    }

    public Suscription findByTipoPrecioMesesInicioFinActivoUser(TipoSuscription tipo, double precio, int meses,
                                                                LocalDate inicio, LocalDate fin, short active, int userId) {
        Suscription suscription =
                em.createQuery(
                        "select s from Suscription s " +
                                "where s.tipo = :tipo and " +
                                "s.precio = :precio and " +
                                "s.duracionMeses = :meses and " +
                                "s.fehcaInicio = :inicio and " +
                                "s.fehcaFin = :fin and " +
                                "s.activo = :active and " +
                                "s.userId = :userId", Suscription.class
                ).setParameter("tipo", tipo)
                        .setParameter("precio", precio)
                        .setParameter("meses", meses)
                        .setParameter("inicio", inicio)
                        .setParameter("fin", fin)
                        .setParameter("active", active)
                        .setParameter("userId", userId)
                        .getSingleResult();
        System.out.println("---- Suscripcion encontrada ----");
        System.out.println(suscription);
        System.out.println();
        return suscription;
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
            suscription.setUserId(newSuscription.getUserId());
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
