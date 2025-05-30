package com.davidGorraiz.service;

import com.davidGorraiz.model.Genre;
import com.davidGorraiz.repository.GenreRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreService implements GenreRepository {

    private EntityManager em;

    public GenreService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void findAll() {
        List<Genre> generos = em.createQuery("select g from Genre g", Genre.class).getResultList();
        generos.forEach(System.out::println);
    }

    @Override
    public void findById(int id) {
        Genre genre = em.find(Genre.class, id);
        System.out.println(genre);
    }

    @Override
    public void save(Genre genre) {
        em.persist(genre);
        em.getTransaction().commit();
    }

    @Override
    public void update(int id, Genre newGenre) {
        Genre genre = em.find(Genre.class, id);
        if (genre != null) {
            genre.setNombre(newGenre.getNombre());
            em.merge(genre);
            em.getTransaction().commit();
        } else {
            System.out.println("El genero que se quiere actualizar no existe");
        }
    }

    @Override
    public void delete(int id) {
        Genre genre = em.find(Genre.class, id);
        if (genre != null) {
            em.remove(genre);
            em.getTransaction().commit();
        } else {
            System.out.println("El genero que se quiere eliminar no existe");
        }
    }
}
