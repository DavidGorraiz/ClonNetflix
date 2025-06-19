package com.davidGorraiz.model.ContentGenre;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.model.Genre;
import com.davidGorraiz.service.ContentGenreService;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.GenreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ContentGenreTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private ContentGenreService service;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        service = new ContentGenreService(em);
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
    void longitud_registros_contentGenre() {
        List<ContentGenre> list = service.findAll();
        assertNotNull(list);
        assertEquals(5, list.size());
    }

    @Test
    void buscar_por_id() {
        ContentService contentService = new ContentService(em);
        Content content = contentService.findById(1);
        GenreService genreService = new GenreService(em);
        Genre genre = genreService.findById(2);
        ContentGenreKey id = new ContentGenreKey(
                content, genre
        );
        ContentGenre contentGenre = service.findById(id);
        assertNotNull(genre);
        assertEquals(2, contentGenre.getGenreId());
    }

    @Test
    void insertar_contentGenre() {
        Content content = new Content(
                "Avatar",
                "Serie de Accion y humor",
                TipoContent.SERIE,
                LocalDate.of(2018,1,2),
                200,
                "+7"
        );
        Genre genre = new Genre(
                "Humor"
        );
        ContentGenre contentGenre = new ContentGenre();
        contentGenre.setContent(content);
        contentGenre.setGenre(genre);

        em.getTransaction().begin();
        service.save(contentGenre);
        em.getTransaction().commit();

        ContentService contentService = new ContentService(em);
        Content contentFound = contentService.findByNotId(
                "Avatar",
                "Serie de Accion y humor",
                TipoContent.SERIE,
                LocalDate.of(2018,1,2),
                200,
                "+7"
        );
        GenreService genreService = new GenreService(em);
        Genre genreFound = genreService.findByName("Humor");
        ContentGenreKey id = new ContentGenreKey(
                contentFound, genreFound
        );
        ContentGenre contentGenreFound = service.findById(id);
        assertNotNull(genreFound);
        assertEquals(5, contentGenreFound.getGenreId());
    }

    @Test
    void delete_contentGenre() {
        ContentService contentService = new ContentService(em);
        Content content = contentService.findById(2);
        GenreService genreService = new GenreService(em);
        Genre genre = genreService.findById(4);
        ContentGenreKey id = new ContentGenreKey(content, genre);

        em.getTransaction().begin();
        service.delete(id);
        em.getTransaction().commit();

        ContentGenre contentGenre = service.findById(id);
        assertNull(contentGenre);
    }
}