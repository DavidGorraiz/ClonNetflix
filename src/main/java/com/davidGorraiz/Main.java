package com.davidGorraiz;

import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.GenreService;
import com.davidGorraiz.service.UserService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        GenreService genreService = new GenreService(em);
        UserService userService = new UserService(em);
        ContentService contentService = new ContentService(em);

        em.getTransaction().begin();
        userService.findAll();
        userService.findById(1);
        genreService.findAll();
        contentService.findAll();
        em.getTransaction().commit();
        em.close();
    }
}