package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.FavoriteService;
import com.davidGorraiz.service.ProfileService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private FavoriteService favoriteService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        favoriteService = new FavoriteService(em);
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
    void longitud_registros_favoritos() {
        List<Favorite> favorites = favoriteService.findAll();
        assertNotNull(favorites);
        assertEquals(3, favorites.size());
    }

    @Test
    void buscar_por_id() {
        Favorite favorite = favoriteService.findById(3);
        assertNotNull(favorite);
        assertEquals(3, favorite.getProfileId());
    }

    @Test
    void insertar_favorito() {
        Profile profile = new Profile(
            "Jane - Perfil Kids",
            "Inglés"
        );
        profile.setUserId(3);
        Content content = new Content(
                "Gato con botas",
                "Pelicula animada",
                TipoContent.PELICULA,
                LocalDate.of(2023,1,1),
                103,
                "+7"
        );
        Favorite favorite = new Favorite();
        favorite.setProfile(profile);
        favorite.setContent(content);

        em.getTransaction().begin();
        favoriteService.save(favorite);
        em.getTransaction().commit();

        Favorite favoriteFound = favoriteService.findById(4);
        assertNotNull(favoriteFound);
        assertEquals(4, favoriteFound.getProfileId());
    }

    @Test
    void update_favorito() {
        ProfileService profileService = new ProfileService(em);
        Profile newProfile = profileService.findById(3);
        ContentService contentService = new ContentService(em);
        Content newContent = contentService.findById(1);
        Favorite newFavorite = new Favorite(
                newProfile.getId(),
                newContent.getId(),
                newProfile,
                newContent
        );
        Favorite oldFavorite = favoriteService.findById(2);

        em.getTransaction().begin();
        favoriteService.update(oldFavorite.getId(), newFavorite);
        em.getTransaction().commit();

        Favorite favoriteFound = favoriteService.findById(2);
        assertNotNull(favoriteFound);
        assertEquals(3, favoriteFound.getProfileId());
    }

    @Test
    void delete_favorito() {
        Favorite favorite = favoriteService.findById(1);
        em.getTransaction().begin();
        favoriteService.delete(favorite.getId());
        em.getTransaction().commit();
        Favorite favoriteFound = favoriteService.findById(1);
        assertNull(favoriteFound);
    }
}