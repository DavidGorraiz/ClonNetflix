package com.davidGorraiz.CRUD;

import com.davidGorraiz.model.Genre;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreCRUD  extends CRUD{

    public GenreCRUD(EntityManager em) {
        super(em);
    }

    @Override
    public void findAll() {
        List<Genre> generos = em.createQuery("select g from Genre g", Genre.class).getResultList();
        generos.forEach(System.out::println);
    }
}
