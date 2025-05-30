package com.davidGorraiz;

import com.davidGorraiz.CRUD.GenreCRUD;
import com.davidGorraiz.model.Genre;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        em.getTransaction().begin();
        GenreCRUD genreCRUD = new GenreCRUD(em);

        System.out.println("---- Listar Generos ----");
        genreCRUD.findAll();

        System.out.println("---- Buscar genero por id ----");
        genreCRUD.findById(1);

//        System.out.println("---- Insertar genero ----");
//        Genre genre = new Genre();
//        genre.setNombre("Humor");
//        genreCRUD.save(genre);

//        System.out.println("---- Actualizar Genero ----");
//        Genre genre =  new Genre();
//        genre.setNombre("Comedia");
//        genreCRUD.update(6,genre);

//        System.out.println("---- Eliminar Genero por id ----");
//        genreCRUD.delete(6);

        System.out.println("---- Listar Generos ----");
        genreCRUD.findAll();

        em.close();
    }
}