package com.davidGorraiz.model;

import com.davidGorraiz.service.GenreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private GenreService genreService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        genreService = new GenreService(em);
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
    void buscar_por_id() {
        Genre genre = genreService.findById(1);
        assertNotNull(genre);
        assertEquals("Ciencia Ficción", genre.getNombre());
    }

    @Test
    void longitud_total_de_la_tabla_genres() {
        List<Genre> generos = genreService.findAll();
        assertEquals(4, generos.size());
    }

    @Test
    void existe_genero_misterio() {
        Genre g = em.createQuery("SELECT g FROM Genre g WHERE g.nombre = :nombre", Genre.class)
                .setParameter("nombre", "Misterio")
                .getSingleResult();

        assertNotNull(g);
        assertEquals("Misterio", g.getNombre());
    }

    @Test
    void insertar_genre() {
        Genre genre = new Genre();
        genre.setNombre("Comedia");
        em.getTransaction().begin();
        genreService.save(genre);
        em.getTransaction().commit();

        Genre genreCreated = genreService.findByName(genre.getNombre());

        assertNotNull(genre.getId());
        assertEquals("Comedia", genreCreated.getNombre());
    }

    @Test
    void update_genre() {
        Genre newGenre = new Genre();
        newGenre.setNombre("SiFi");
        Genre oldGenre = genreService.findByName("Ciencia Ficción");
        em.getTransaction().begin();
        genreService.update(oldGenre.getId(), newGenre);
        em.getTransaction().commit();

        Genre genreUpdated = genreService.findByName(newGenre.getNombre());

        assertNotNull(genreUpdated.getId());
        assertEquals("SiFi", genreUpdated.getNombre());
    }

    @Test
    void delete_genre() {
        Genre genre = genreService.findById(1);
        em.getTransaction().begin();
        genreService.delete(genre.getId());
        em.getTransaction().commit();
        Genre genreDeleted = genreService.findById(1);
        assertNull(genreDeleted);
    }
}