package com.davidGorraiz;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.model.Genre;
import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.Suscription.TipoSuscription;
import com.davidGorraiz.model.User.Rol;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.GenreService;
import com.davidGorraiz.service.SuscriptionService;
import com.davidGorraiz.service.UserService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

import static com.davidGorraiz.model.Content.TipoContent.PELICULA;
import static com.davidGorraiz.model.User.Rol.CLIENTE;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        GenreService genreService = new GenreService(em);
        UserService userService = new UserService(em);
        ContentService contentService = new ContentService(em);
        SuscriptionService suscriptionService = new SuscriptionService(em);

        em.getTransaction().begin();

        // Operaciones select
        userService.findAll();
        userService.findById(1);
        genreService.findAll();
        genreService.findById(1);
        contentService.findAll();
        contentService.findById(1);
        suscriptionService.findAll();
        suscriptionService.findById(1);

        // Operaciones insert
//        Genre genre = new Genre("Comedia");
//        genreService.save(genre);
//        genreService.findAll();

//        User user = new User("juan@netflixclone.com",
//                "juan123",
//                "Juan Gonzales",
//                LocalDate.of(2025,01,01),
//                CLIENTE);
//        userService.save(user);
//        userService.findAll();

//        Content content = new Content("Gato con botas el ultimo deseo",
//                "Pelicula animada",
//                PELICULA,
//                LocalDate.of(2022,12,21),
//                102,
//                "+7");
//        contentService.save(content);
//        contentService.findAll();

//        Suscription suscription = new Suscription(TipoSuscription.BÁSICO,
//                12.99D,
//                6,
//                LocalDate.of(2024,2,15),
//                LocalDate.of(2024,8,15),
//                (short) 1);
//        User user = userService.findById(1);
//        suscriptionService.save(suscription, user);
//        suscriptionService.findAll();

        em.getTransaction().commit();
        em.close();
    }
}