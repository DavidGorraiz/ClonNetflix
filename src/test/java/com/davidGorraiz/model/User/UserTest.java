package com.davidGorraiz.model.User;

import com.davidGorraiz.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static com.davidGorraiz.model.User.Rol.CLIENTE;
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
    void longitud_total_registros_users() {
        List<User> users = userService.findAll();
        assertEquals(3, users.size());
    }

    @Test
    void buscar_por_id() {
        User user = userService.findById(1);
        assertNotNull(user);
        assertEquals("Administrador", user.getNombreCompleto());
    }

    @Test
    void buscar_por_email() {
        User user = userService.findByEmail("admin@netflixclone.com");
        assertNotNull(user);
        assertEquals("Administrador", user.getNombreCompleto());
    }

    @Test
    void insertar_user() {
        User user = new User(
                "juan@netflixclone.com",
                "juan123",
                "Juan Gonzales",
                LocalDate.of(2025,01,01),
                CLIENTE
        );
        em.getTransaction().begin();
        userService.save(user);
        em.getTransaction().commit();

        user = userService.findByEmail("juan@netflixclone.com");
        assertNotNull(user);
        assertEquals("Juan Gonzales", user.getNombreCompleto());

    }

    @Test
    void update_user() {
        User userUpdate = new User();
        userUpdate.setEmail("david.gonzales1@example.com");
        userUpdate.setPassword("david123");
        userUpdate.setNombreCompleto("David Gonzales");
        userUpdate.setFechaRegistro(LocalDate.of(2025,01,01));
        userUpdate.setRol(Rol.ADMIN);

        User oldUser = userService.findByEmail("john.doe@example.com");
        em.getTransaction().begin();
        userService.update(oldUser.getId(), userUpdate);
        em.getTransaction().commit();
        assertNotNull(oldUser);
        assertEquals("David Gonzales", oldUser.getNombreCompleto());
    }

    @Test
    void delete_user() {
        User user = userService.findById(3);
        em.getTransaction().begin();
        userService.delete(user.getId());
        em.getTransaction().commit();
        assertNull(userService.findById(3));
    }
}