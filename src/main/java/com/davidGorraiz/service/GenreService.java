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
    public List<Genre> findAll() {
        List<Genre> generos = em.createQuery("select g from Genre g", Genre.class).getResultList();
        System.out.println("---- Listar generos ----");
        generos.forEach(System.out::println);
        System.out.println();
        return generos;
    }

    @Override
    public Genre findById(int id) {
        Genre genre = em.find(Genre.class, id);
        System.out.println("---- Genero encontrado -----");
        System.out.println(genre);
        System.out.println();
        return genre;
    }

    @Override
    public void save(Genre genre) {
        System.out.println("---- Insertar genero ----");
        em.persist(genre);
        System.out.println();
    }

    @Override
    public void update(int id, Genre newGenre) {
        Genre genre = em.find(Genre.class, id);
        if (genre != null) {
            genre.setNombre(newGenre.getNombre());
            System.out.println("---- Actualizar genero -----");
            em.merge(genre);
            System.out.println();
        } else {
            System.out.println("El genero que se quiere actualizar no existe");
            System.out.println();
        }
    }

    @Override
    public void delete(int id) {
        Genre genre = em.find(Genre.class, id);
        if (genre != null) {
            System.out.println("---- Eliminar genre ----");
            em.remove(genre);
        } else {
            System.out.println("El genero que se quiere eliminar no existe");
        }
    }
}
