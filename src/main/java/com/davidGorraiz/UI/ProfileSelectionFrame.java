package com.davidGorraiz.UI;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfileSelectionFrame extends JFrame {

    private List<Profile> perfiles;
    private User user;
    private JPanel profilesPanel;
    private Profile selectedProfile;
    private EntityManager em = UtilEntity.getEntityManager();
    private ProfileService profileService = new ProfileService(em);

    public ProfileSelectionFrame(List<Profile> perfiles, User user) {
        this.perfiles = perfiles;
        this.user = user;

        setTitle("Selecciona un perfil");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(perfiles);
    }

    private void initComponents(List<Profile> perfiles) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("¿Quién está viendo?", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(label, BorderLayout.NORTH);

        profilesPanel = new JPanel(new FlowLayout());

        loadProfiles();

        mainPanel.add(profilesPanel, BorderLayout.CENTER);

        JPanel adminPanel = createAdminPanel();
        mainPanel.add(adminPanel, BorderLayout.SOUTH);


        add(mainPanel);
    }

    private void loadProfiles() {
        profilesPanel.removeAll();

        for (Profile perfil : perfiles) {
            JButton profileButton = new JButton(perfil.getNombre());
            profileButton.setPreferredSize(new Dimension(120, 120));

            profileButton.addActionListener(e -> {
                selectedProfile = perfil;
                JOptionPane.showMessageDialog(this, "Perfil seleccionado: " + perfil.getNombre());
                // Aquí puedes abrir el catálogo

                // 👉 Aquí llamas a CatalogFrame pasándole el perfil y tu lista real de títulos
                CatalogFrame catalog = new CatalogFrame(selectedProfile, perfiles, user);
                catalog.setVisible(true);
                dispose(); // Opcional: cierra la ventana actual


            });

            profilesPanel.add(profileButton);
        }

        profilesPanel.revalidate();
        profilesPanel.repaint();
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        adminPanel.setBorder(BorderFactory.createTitledBorder("Opciones"));
        adminPanel.setPreferredSize(new Dimension(600, 100));

        JButton addButton = new JButton("Añadir perfil");
        // Botón para volver al login
        JButton backToLoginButton = new JButton("← Volver al Login");
        backToLoginButton.setFont(new Font("Arial", Font.PLAIN, 14));

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Nombre del nuevo perfil:");
            String idioma = JOptionPane.showInputDialog(this, "Idioma del nuevo perfil:");
            if (name != null && !name.trim().isEmpty()) {
                Profile profile = new Profile(
                        name,
                        idioma
                );
                em.getTransaction().begin();
                profileService.save(profile, user);
                em.getTransaction().commit();

                // Recargar la lista de perfiles del usuario
                this.perfiles = profileService.findByUser(user);

                // Volver a cargar la interfaz con la nueva lista
                loadProfiles();
            }
        });

        backToLoginButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            dispose();
        });

        adminPanel.add(addButton);
        adminPanel.add(backToLoginButton);
        return adminPanel;
    }

}
