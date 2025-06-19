package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.EpisodeService;
import com.davidGorraiz.service.GenreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpisodeTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EpisodeService episodeService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        episodeService = new EpisodeService(em);
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
    void longitud_registros_episodes() {
        List<Episode> episodes = episodeService.findAll();
        assertNotNull(episodes);
        assertEquals(2, episodes.size());
    }

    @Test
    void buscar_por_id() {
        Episode episode = episodeService.findById(2);
        assertNotNull(episode);
        assertEquals("Capítulo 2: The Weirdo", episode.getTitulo());
    }

    @Test
    void buscar_no_por_id() {
        Episode episode = episodeService.findNotById(
                "Capítulo 2: The Weirdo",
                "Segundo episodio",
                2,
                1,
                45,
                2
        );
        assertNotNull(episode);
        assertEquals(2, episode.getId());
    }

    @Test
    void insertar_episode() {
        ContentService contentService = new ContentService(em);
        Content content = contentService.findById(2);
        Episode episode = new Episode(
                "Capituo 3",
                "Tercer capitulo",
                3,
                1,
                50
        );
        episode.setContent(content);
        episode.setContentId(content.getId());
        em.getTransaction().begin();
        episodeService.save(episode);
        em.getTransaction().commit();

        Episode saved = episodeService.findNotById(
                "Capituo 3",
                "Tercer capitulo",
                3,
                1,
                50,
                content.getId()
        );
        assertNotNull(saved);
        assertEquals("Capituo 3", saved.getTitulo());
    }

    @Test
    void update_episode() {
        Episode oldEpisode = episodeService.findById(2);
        ContentService contentService = new ContentService(em);
        Content content = contentService.findById(2);
        Episode newEpisode = new Episode(
                "Capituo 2",
                "Second capitulo",
                2,
                1,
                49
        );
        newEpisode.setContent(content);
        newEpisode.setContentId(content.getId());
        em.getTransaction().begin();
        episodeService.update(oldEpisode.getId(), newEpisode);
        em.getTransaction().commit();
        Episode updated = episodeService.findById(2);
        assertNotNull(updated);
        assertEquals("Capituo 2", updated.getTitulo());
    }

    @Test
    void delete_episode() {
        Episode episode = episodeService.findById(1);
        em.getTransaction().begin();
        episodeService.delete(episode.getId());
        em.getTransaction().commit();
        Episode deleted = episodeService.findById(1);
        assertNull(deleted);
    }
}