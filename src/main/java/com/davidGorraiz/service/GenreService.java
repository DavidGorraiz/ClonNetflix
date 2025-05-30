package com.davidGorraiz.service;

import com.davidGorraiz.model.Genre;
import com.davidGorraiz.repository.GenreRepository;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreService implements GenreRepository {

    private EntityManager em;

    public GenreService() {
        this.em = UtilEntity.getEntityManager();
    }

    @Override
    public void findAll() {
        em.getTransaction().begin();
        List<Genre> generos = em.createQuery("select g from Genre g", Genre.class).getResultList();
        System.out.println("---- Listar generos ----");
        generos.forEach(System.out::println);
        System.out.println();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void findById(int id) {
        em.getTransaction().begin();
        Genre genre = em.find(Genre.class, id);
        System.out.println("---- Genero encontrado -----");
        System.out.println(genre);
        System.out.println();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void save(Genre genre) {
        em.getTransaction().begin();
        System.out.println("---- Actualizar genero ----");
        em.persist(genre);
        System.out.println();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(int id, Genre newGenre) {
        em.getTransaction().begin();
        Genre genre = em.find(Genre.class, id);
        if (genre != null) {
            genre.setNombre(newGenre.getNombre());
            System.out.println("---- Actualizar genero -----");
            em.merge(genre);
            System.out.println();
            em.getTransaction().commit();
        } else {
            System.out.println("El genero que se quiere actualizar no existe");
            System.out.println();
        }
        em.close();
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        Genre genre = em.find(Genre.class, id);
        if (genre != null) {
            em.remove(genre);
            em.getTransaction().commit();
        } else {
            System.out.println("El genero que se quiere eliminar no existe");
        }
        em.close();
    }
}
