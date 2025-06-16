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
    void testBuscarGeneroPorId() {
        Genre genre = genreService.findById(1);
        assertNotNull(genre);
        assertEquals("Ciencia Ficción", genre.getNombre());
    }

    @Test
    void testTotalDeGeneros() {
        List<Genre> generos = genreService.findAll();
        assertEquals(4, generos.size());
    }

    @Test
    void testExisteGeneroComedia() {
        Genre g = em.createQuery("SELECT g FROM Genre g WHERE g.nombre = :nombre", Genre.class)
                .setParameter("nombre", "Misterio")
                .getSingleResult();

        assertNotNull(g);
        assertEquals("Misterio", g.getNombre());
    }

}