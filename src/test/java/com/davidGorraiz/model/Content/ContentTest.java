package com.davidGorraiz.model.Content;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private ContentService contentService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
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
    void longitud_total_registros_content() {
        List<Content> contents = contentService.findAll();
        assertNotNull(contents);
        assertEquals(2, contents.size());
    }

    @Test
    void buscar_por_id() {
        Content content = contentService.findById(1);
        assertNotNull(content);
        assertEquals("The Matrix", content.getTitulo());
    }

    @Test
    void insertar_profile() {
        Content content = new Content(
                "El gato con botas",
                "Pelicula premiada",
                TipoContent.PELICULA,
                LocalDate.of(2023,2,1),
                103,
                "+7"
        );
        em.getTransaction().begin();
        em.persist(content);
        em.getTransaction().commit();

        Content contentFound = contentService.findByNotId(
                content.getTitulo(),
                content.getDescripcion(),
                content.getTipo(),
                content.getFechaLanzamiento(),
                content.getDuracion(),
                content.getClasificacion()
        );

        assertNotNull(contentFound);
        assertEquals("El gato con botas", contentFound.getTitulo());
    }

    @Test
    void update_profile() {
        Content newContent = new Content(
                "Dragon ball",
                "Anime famoso",
                TipoContent.SERIE,
                LocalDate.of(1999,2,1),
                1000,
                "+7"
        );

        Content oldContent = contentService.findById(2);
        em.getTransaction().begin();
        contentService.update(newContent, oldContent.getId());
        em.getTransaction().commit();


        Content contentFound = contentService.findById(2);
        assertNotNull(contentFound);
        assertEquals("Dragon ball", contentFound.getTitulo());
    }

    @Test
    void delete_profile() {
        Content content = contentService.findById(2);
        em.getTransaction().begin();
        contentService.delete(content.getId());
        em.getTransaction().commit();
        Content contentFound = contentService.findById(2);
        assertNull(contentFound);
    }

}