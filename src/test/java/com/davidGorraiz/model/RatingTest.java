package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.service.RatingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class RatingTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private RatingService ratingService;
    private ContentService contentService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        ratingService = new RatingService(em);
        contentService = new ContentService(em);
    }

    @AfterEach
    void tearDown() {
        if (em.getTransaction().isActive()) em.getTransaction().rollback();
        em.close();
    }

    @AfterAll
    static void close() {
        emf.close();
    }

    @Test
    void longitud_total_registros() {
        List<Rating> ratings = ratingService.findAll();
        assertNotNull(ratings);
        assertEquals(3, ratings.size());
    }

    @Test
    void buscar_por_id() {
        Rating rating = ratingService.findById(1);
        assertNotNull(rating);
        assertEquals("¡Increíble película!", rating.getComentario());
    }

    @Test
    void promedio_rate() {
        Content content = contentService.findById(2);
        double avg = ratingService.averageRate(content.getId());
        assertNotNull(avg);
        assertEquals(4.5, avg);
    }

    @Test
    void insertar_rating() {
        Profile profile = new Profile(
                "Jane - Perfil 1 Kids",
                "Ingles"
        );
        profile.setUserId(1);
        Content content = new Content(
                "El gato con botas",
                "Pelicula premiada",
                TipoContent.PELICULA,
                LocalDate.of(2023,2,1),
                103,
                "+7"
        );
        Rating newRating = new Rating(
                5,
                "Es muy buena la peli",
                profile,
                content
        );

        em.getTransaction().begin();
        ratingService.save(newRating);
        em.getTransaction().commit();

        Rating ratingFound = ratingService.findNotById(5,
                "Es muy buena la peli",
                profile,
                content);
        assertNotNull(ratingFound);
        assertEquals("Es muy buena la peli", ratingFound.getComentario());
    }

    @Test
    void update_rating() {
        ProfileService profileService = new ProfileService(em);
        Profile profile = profileService.findById(2);
        ContentService contentService = new ContentService(em);
        Content content = contentService.findById(2);
        Rating olRating = ratingService.findById(2);
        Rating newRating = new Rating(5,
                "Es muy buena la peli",
                profile,
                content);
        newRating.setProfileId(profile.getId());
        newRating.setContentId(content.getId());

        em.getTransaction().begin();
        ratingService.update(olRating.getId(), newRating);
        em.getTransaction().commit();

        Rating ratingFound = ratingService.findNotById(
                5,
                "Es muy buena la peli",
                profile,
                content
        );
        assertNotNull(ratingFound);
        assertEquals("Es muy buena la peli", ratingFound.getComentario());
    }

    @Test
    void delete_rating() {
        Rating rating = ratingService.findById(3);
        em.getTransaction().begin();
        ratingService.delete(rating.getId());
        em.getTransaction().commit();
        Rating ratingFound = ratingService.findById(3);
        assertNull(ratingFound);
    }
}