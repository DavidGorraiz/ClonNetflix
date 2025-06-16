package com.davidGorraiz;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.model.Genre;
import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.Suscription.TipoSuscription;
import com.davidGorraiz.model.User.Rol;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.*;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;

import static com.davidGorraiz.model.Content.TipoContent.PELICULA;
import static com.davidGorraiz.model.User.Rol.ADMIN;
import static com.davidGorraiz.model.User.Rol.CLIENTE;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        GenreService genreService = new GenreService(em);
        UserService userService = new UserService(em);
        ContentService contentService = new ContentService(em);
        SuscriptionService suscriptionService = new SuscriptionService(em);
        ProfileService profileService = new ProfileService(em);

        em.getTransaction().begin();

        // Operaciones select
        selectOperations(userService, genreService, contentService, suscriptionService, profileService);

        // Operaciones insert
//        insertOperations(genreService, userService, contentService, suscriptionService);

        //Operaciones Update
//        updateOperations(genreService, userService, contentService, suscriptionService);

        // Operaciones delete
//        deleteOperations(genreService, userService, contentService, suscriptionService);

        em.getTransaction().commit();
        em.close();
    }

    private static void deleteOperations(GenreService genreService, UserService userService, ContentService contentService, SuscriptionService suscriptionService) {
        genreService.delete(7);
        genreService.findAll();

        userService.delete(4);
        userService.findAll();

        contentService.delete(3);
        contentService.findAll();

        suscriptionService.delete(2);
        suscriptionService.findAll();
    }

    private static void updateOperations(GenreService genreService, UserService userService, ContentService contentService, SuscriptionService suscriptionService) {
        Genre genreUpdate = new Genre();
        genreUpdate.setNombre("Humor");
        genreService.update(7, genreUpdate);
        genreService.findAll();

        User userUpdate = new User();
        userUpdate.setEmail("david.gonzales1@example.com");
        userUpdate.setPassword("david123");
        userUpdate.setNombreCompleto("David Gonzales");
        userUpdate.setFechaRegistro(LocalDate.of(2025,01,01));
        userUpdate.setRol(Rol.ADMIN);
        userService.update(4, userUpdate);
        userService.findAll();

        Content contentUpdate = new Content(
                "Puss in boots the last wish",
                "Recent movie",
                PELICULA,
                LocalDate.of(2022,12,02),
                105,
                "+7"
        );
        contentService.update(contentUpdate, 3);
        contentService.findAll();

        Suscription suscriptionUpdate = new Suscription(
                TipoSuscription.BÁSICO,
                10.15,
                4,
                LocalDate.of(2024,10,20),
                LocalDate.of(2025, 04, 20),
                (short) 1
        );
        suscriptionUpdate.setUser(userService.findById(4));
        suscriptionService.update(3, suscriptionUpdate);
        suscriptionService.findAll();
    }

    private static void insertOperations(GenreService genreService, UserService userService, ContentService contentService, SuscriptionService suscriptionService) {
        Genre genreUpdate = new Genre("Comedia");
        genreService.save(genreUpdate);
        genreService.findAll();

        User user = new User("juan@netflixclone.com",
                "juan123",
                "Juan Gonzales",
                LocalDate.of(2025,01,01),
                CLIENTE);
        userService.save(user);
        userService.findAll();

        Content content = new Content("Gato con botas el ultimo deseo",
                "Pelicula animada",
                PELICULA,
                LocalDate.of(2022,12,21),
                102,
                "+7");
        contentService.save(content);
        contentService.findAll();

        Suscription suscription = new Suscription(TipoSuscription.BÁSICO,
                12.99D,
                6,
                LocalDate.of(2024,2,15),
                LocalDate.of(2024,8,15),
                (short) 1);
        User userSave = userService.findById(1);
        suscriptionService.save(suscription, userSave);
        suscriptionService.findAll();
    }

    private static void selectOperations(UserService userService, GenreService genreService,
                                         ContentService contentService, SuscriptionService suscriptionService,
                                         ProfileService profileService) {
        userService.findAll();
        userService.findById(1);
        genreService.findAll();
        genreService.findById(1);
        contentService.findAll();
        contentService.findById(1);
        suscriptionService.findAll();
        suscriptionService.findById(1);
        profileService.findAll();
        profileService.findById(1);
    }
}