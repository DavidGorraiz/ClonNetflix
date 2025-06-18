package com.davidGorraiz.model.Suscription;

import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.GenreService;
import com.davidGorraiz.service.SuscriptionService;
import com.davidGorraiz.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuscriptionTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private SuscriptionService suscriptionService;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        suscriptionService = new SuscriptionService(em);
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
    void longitud_total_registros_suscription() {
        List<Suscription> suscriptions = suscriptionService.findAll();
        assertEquals(2, suscriptions.size());
    }

    @Test
    void buscar_por_id() {
        Suscription suscription = suscriptionService.findById(1);
        assertNotNull(suscription);
        assertEquals(1, suscription.getId());
    }

    @Test
    void buscar_por_campos_menos_id() {
        Suscription suscription = suscriptionService.findByTipoPrecioMesesInicioFinActivoUser(
                TipoSuscription.ESTÁNDAR,
                12.99, 6,
                LocalDate.of(2024,2,15),
                LocalDate.of(2024,8,15),
                (short) 1,
                3
        );
        assertNotNull(suscription);
        assertEquals(2, suscription.getId());
    }

    @Test
    void insertar_suscription() {
        UserService userService = new UserService(em);
        User user = userService.findById(1);
        Suscription suscription = new Suscription();
        suscription.setTipo(TipoSuscription.BÁSICO);
        suscription.setPrecio(12.99);
        suscription.setDuracionMeses(3);
        suscription.setFehcaInicio(LocalDate.of(2024,2,15));
        suscription.setFehcaFin(LocalDate.of(2024,8,15));
        suscription.setActivo((short)0);

        em.getTransaction().begin();
        suscriptionService.save(suscription, user);
        em.getTransaction().commit();

        Suscription suscriptionFound = suscriptionService.findByTipoPrecioMesesInicioFinActivoUser(
                suscription.getTipo(),
                suscription.getPrecio(),
                suscription.getDuracionMeses(),
                suscription.getFehcaInicio(),
                suscription.getFehcaFin(),
                suscription.getActivo(),
                user.getId()
        );

        assertNotNull(suscriptionFound);
        assertEquals(3, suscriptionFound.getId());
    }

    @Test
    void update_suscription() {
        UserService userService = new UserService(em);
        User user = userService.findById(1);
        Suscription newSuscription = new Suscription(
                TipoSuscription.ESTÁNDAR,
                12.99,
                6,
                LocalDate.of(2025,2,15),
                LocalDate.of(2025,8,15),
                (short) 1
        );
        newSuscription.setUser(user);
        newSuscription.setUserId(user.getId());

        Suscription oldSuscription = suscriptionService.findById(1);

        em.getTransaction().begin();
        suscriptionService.update(oldSuscription.getId(), newSuscription);
        em.getTransaction().commit();

        Suscription suscriptionFound = suscriptionService.findById(1);
        assertNotNull(suscriptionFound);
        assertEquals(TipoSuscription.ESTÁNDAR, suscriptionFound.getTipo());
    }

    @Test
    void delete_suscription() {
        Suscription suscription = suscriptionService.findById(2);
        em.getTransaction().begin();
        suscriptionService.delete(suscription.getId());
        em.getTransaction().commit();
        Suscription suscriptionFound = suscriptionService.findById(2);
        assertNull(suscriptionFound);
    }
}