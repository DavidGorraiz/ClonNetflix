package com.davidGorraiz.model;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.service.WatchHistoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WatchHistoryTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private WatchHistoryService watchHistoryService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        watchHistoryService = new WatchHistoryService(em);
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
    void longitud_registros_watchHistory() {
        List<WatchHistory> watchHistories = watchHistoryService.findAll();
        assertNotNull(watchHistories);
        assertEquals(3, watchHistories.size());
    }

    @Test
    void buscar_por_id() {
        WatchHistory watchHistory = watchHistoryService.findById(1);
        assertNotNull(watchHistory);
        assertEquals(
                LocalDateTime.of(2024,05,01,20,0,0),
                watchHistory.getFechaVisto()
        );
    }

    @Test
    void buscar_por_algo_diferente_a_id() {
        ContentService contentService = new ContentService(em);
        Content content = contentService.findById(1);
        ProfileService profileService = new ProfileService(em);
        Profile profile = profileService.findById(1);
        WatchHistory watchHistory = watchHistoryService.findNotById(
                profile.getId(),
                content.getId(),
                LocalDateTime.of(2024,05,01,20,0,0),
                120
        );
        assertNotNull(watchHistory);
        assertEquals(1, watchHistory.getId());
    }

    @Test
    void insertar_watchHistory() {
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
        WatchHistory watchHistory = new WatchHistory(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                100,
                content,
                profile
        );
        em.getTransaction().begin();
        watchHistoryService.save(watchHistory);
        em.getTransaction().commit();

        WatchHistory watchHistoryFound = watchHistoryService.findById(4);
        assertNotNull(watchHistoryFound);
        assertEquals(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), watchHistoryFound.getFechaVisto());
    }

    @Test
    void update_watchHistory() {
        ProfileService profileService = new ProfileService(em);
        Profile newProfile = profileService.findById(3);
        ContentService contentService = new ContentService(em);
        Content newContent = contentService.findById(1);
        WatchHistory newWatchHistory = new WatchHistory(
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                50,
                newContent,
                newProfile
        );
        newWatchHistory.setProfileId(newProfile.getId());
        newWatchHistory.setContentId(newContent.getId());
        WatchHistory oldWatchHistory = watchHistoryService.findById(3);

        em.getTransaction().begin();
        watchHistoryService.update(oldWatchHistory.getId(), newWatchHistory);
        em.getTransaction().commit();

        WatchHistory watchHistoryUpdated = watchHistoryService.findById(3);
        assertNotNull(watchHistoryUpdated);
        assertEquals(newWatchHistory.getFechaVisto(), watchHistoryUpdated.getFechaVisto());
    }

    @Test
    void delete_watchHistory() {
        WatchHistory watchHistory = watchHistoryService.findById(2);
        em.getTransaction().begin();
        watchHistoryService.delete(watchHistory.getId());
        em.getTransaction().commit();
        WatchHistory watchHistoryDeleted = watchHistoryService.findById(2);
        assertNull(watchHistoryDeleted);
    }
}