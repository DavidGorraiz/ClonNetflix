package com.davidGorraiz.UI;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.ContentService;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogFrame extends JFrame {

    private User user;
    private List<Profile> profiles;
    private Profile perfilActual;
    private List<Content> catalogo;
    private EntityManager em = UtilEntity.getEntityManager();
    private ContentService contentService = new ContentService(em);
    private ProfileService profileService = new ProfileService(em);

    public CatalogFrame(Profile perfil, List<Profile> profiles, User user) {
        this.user = user;
        this.profiles = profiles;
        this.perfilActual = perfil;
        this.catalogo =contentService.findAll();

        setTitle("CloneFlix - Catálogo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Título y botón de cerrar sesión
        JLabel profileLabel = new JLabel("Bienvenido, " + perfilActual.getNombre(), SwingConstants.LEFT);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profileLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        // Crear los nuevos botones
        JButton buscarPorNombreButton = new JButton("Buscar por nombre");
        JButton buscarPorGeneroButton = new JButton("Buscar por género");

        // Agregar acciones a los botones (puedes personalizarlas luego)
        buscarPorNombreButton.addActionListener(e -> {
            // Acción para buscar por nombre
            JOptionPane.showMessageDialog(null, "Buscar por nombre no implementado aún");
        });

        buscarPorGeneroButton.addActionListener(e -> {
            // Acción para buscar por género
            JOptionPane.showMessageDialog(null, "Buscar por género no implementado aún");
        });

        // Panel derecho con los tres botones
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightButtonsPanel.add(buscarPorNombreButton);
        rightButtonsPanel.add(buscarPorGeneroButton);
        rightButtonsPanel.add(logoutButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(profileLabel, BorderLayout.WEST);
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel adminPanel = createAdminPanel();
        mainPanel.add(adminPanel, BorderLayout.SOUTH);

        // Contenido con scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        ContentService contentService = new ContentService(em);

        addSeccion(contentPanel, "Películas", contentService.findByType(TipoContent.PELICULA));
        addSeccion(contentPanel, "Series", contentService.findByType(TipoContent.SERIE));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addSeccion(JPanel parent, String titulo, List<Content> items) {
        JLabel sectionLabel = new JLabel(titulo);
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sectionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        parent.add(sectionLabel);

        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (Content item : items) {
            JButton btn = new JButton("<html><center>" + item.getTitulo() + "<br/>" + item.getClasificacion() + "</center></html>");
            btn.setPreferredSize(new Dimension(140, 80));
            btn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Mostrando detalles de: " + item.getTitulo()));
            fila.add(btn);
        }

        parent.add(fila);
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        adminPanel.setBorder(BorderFactory.createTitledBorder("Administrar perfil"));
        adminPanel.setPreferredSize(new Dimension(600, 100));

        JButton deleteButton = new JButton("Eliminar perfil");
        JButton editButton = new JButton("Editar perfil");
        // Botón para volver al login
        JButton backToProfiles = new JButton("← Volver a perfiles");
        backToProfiles.setFont(new Font("Arial", Font.PLAIN, 14));



        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar perfil: " + perfilActual.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                em.getTransaction().begin();
                profileService.delete(perfilActual.getId());
                em.getTransaction().commit();

                // Recargar la lista de perfiles del usuario
                this.profiles = profileService.findByUser(user);

                SwingUtilities.invokeLater(() -> new ProfileSelectionFrame(profiles, user).setVisible(true));
                dispose();
            }
        });

        editButton.addActionListener(e -> {
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", perfilActual.getNombre());
            String nuevoIdioma = JOptionPane.showInputDialog(this, "Nuevo idioma:", perfilActual.getIdioma());

            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty() && nuevoIdioma != null && !nuevoIdioma.trim().isEmpty()) {
                Profile newProfile = new Profile(
                        nuevoNombre,
                        nuevoIdioma
                );
                newProfile.setUserId(user.getId());
                newProfile.setUser(user);

                em.getTransaction().begin();
                profileService.update(perfilActual.getId(), newProfile);
                em.getTransaction().commit();

                this.perfilActual = newProfile;
                this.profiles = profileService.findByUser(user);
                SwingUtilities.invokeLater(() -> new ProfileSelectionFrame(profiles, user).setVisible(true));
                dispose();
            }
        });

        backToProfiles.addActionListener(e -> {

            SwingUtilities.invokeLater(() -> new ProfileSelectionFrame(profiles, user).setVisible(true));
            dispose();
        });

        adminPanel.add(deleteButton);
        adminPanel.add(editButton);
        adminPanel.add(backToProfiles);

        return adminPanel;
    }

}
