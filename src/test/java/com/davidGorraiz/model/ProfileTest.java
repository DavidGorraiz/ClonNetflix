package com.davidGorraiz.model;

import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    private ProfileService profileService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        profileService = new ProfileService(em);
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
    void longitud_total_registros_profiles() {
        List<Profile> profiles = profileService.findAll();
        assertNotNull(profiles);
        assertEquals(3, profiles.size());
    }

    @Test
    void buscar_por_id() {
        Profile profile = profileService.findById(1);
        assertNotNull(profile);
        assertEquals("John - Perfil 1", profile.getNombre());
    }

    @Test
    void insertar_profile() {
        UserService userService = new UserService(em);
        User user = userService.findById(3);
        Profile profile = new Profile(
                "Jane - Perfil 1 Kids",
                "Ingles"
        );
        em.getTransaction().begin();
        profileService.save(profile, user);
        em.getTransaction().commit();

        Profile profileFound = profileService.findByNombreAndIdioma("Jane - Perfil 1 Kids",
                "Ingles");

        assertEquals("Jane - Perfil 1 Kids", profileFound.getNombre());
    }

    @Test
    void update_profile() {
        UserService userService = new UserService(em);
        User newUser = userService.findById(1);
        Profile newProfile = new Profile();
        newProfile.setNombre("David - Perfil 1");
        newProfile.setIdioma("Español");
        newProfile.setUser(newUser);
        newProfile.setUserId(newUser.getId());

        Profile oldProfile = profileService.findById(2);

        em.getTransaction().begin();
        profileService.update(oldProfile.getId(), newProfile);
        em.getTransaction().commit();
        Profile profileFound = profileService.findById(2);
        assertEquals("David - Perfil 1", profileFound.getNombre());
    }

    @Test
    void delete_profile() {
        Profile profile = profileService.findById(3);
        em.getTransaction().begin();
        profileService.delete(profile.getId());
        em.getTransaction().commit();
        Profile profileFound = profileService.findById(3);
        assertNull(profileFound);
    }
}