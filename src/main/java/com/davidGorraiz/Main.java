package com.davidGorraiz;

import com.davidGorraiz.service.GenreService;
import com.davidGorraiz.service.UserService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        em.getTransaction().begin();
        GenreService genreService = new GenreService(em);
        UserService userService = new UserService(em);

        System.out.println("---- Listar usuarios ----");
        userService.findAll();

        em.close();
    }
}