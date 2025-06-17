package com.davidGorraiz.model.User;

import com.davidGorraiz.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private UserService userService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        userService = new UserService(em);
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
        User user = userService.findById(1);
        assertNotNull(user);
        assertEquals("Administrador", user.getNombreCompleto());
    }
}