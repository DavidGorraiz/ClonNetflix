package com.davidGorraiz;

import com.davidGorraiz.service.GenreService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        em.getTransaction().begin();
        GenreService genreService = new GenreService(em);

        System.out.println("---- Listar Generos ----");
        genreService.findAll();

        System.out.println("---- Buscar genero por id ----");
        genreService.findById(1);

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
        genreService.findAll();

        em.close();
    }
}