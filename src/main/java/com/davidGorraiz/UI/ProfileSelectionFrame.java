package com.davidGorraiz.UI;

import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.ProfileService;
import com.davidGorraiz.service.UserService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProfileSelectionFrame extends JFrame {

    private List<Profile> perfiles;
    private JPanel profilesPanel;
    private Profile selectedProfile;
    private EntityManager em = UtilEntity.getEntityManager();
    private ProfileService profileService = new ProfileService(em);

    public ProfileSelectionFrame(List<Profile> perfiles) {
        this.perfiles = perfiles;

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
        System.out.println("loadProfiles");
        System.out.println(perfiles);
        profilesPanel.removeAll();

        for (Profile perfil : perfiles) {
            JButton profileButton = new JButton(perfil.getNombre());
            profileButton.setPreferredSize(new Dimension(120, 120));

            profileButton.addActionListener(e -> {
                selectedProfile = perfil;
                JOptionPane.showMessageDialog(this, "Perfil seleccionado: " + perfil.getNombre());
                // Aquí puedes abrir el catálogo
            });

            profilesPanel.add(profileButton);
        }

        profilesPanel.revalidate();
        profilesPanel.repaint();
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        adminPanel.setBorder(BorderFactory.createTitledBorder("Administrar perfiles"));
        adminPanel.setPreferredSize(new Dimension(600, 100));

        JButton addButton = new JButton("Añadir perfil");
        JButton deleteButton = new JButton("Eliminar perfil");
        JButton editButton = new JButton("Editar nombre");
        JButton markKidButton = new JButton("Marcar como infantil");

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Nombre del nuevo perfil:");
            String idioma = JOptionPane.showInputDialog(this, "Idioma del nuevo perfil:");
            if (name != null && !name.trim().isEmpty()) {

                UserService userService = new UserService(em);
                int userId = perfiles.get(0).getUserId();
                User user = userService.findById(userId);
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

        deleteButton.addActionListener(e -> {
            if (selectedProfile == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un perfil primero.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar perfil: " + selectedProfile.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Aun no implementado");
                loadProfiles();
            }
        });

        editButton.addActionListener(e -> {
            if (selectedProfile == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un perfil primero.");
                return;
            }
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", selectedProfile.getNombre());
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Aun no implementado");
                loadProfiles();
            }
        });

        markKidButton.addActionListener(e -> {
            if (selectedProfile == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un perfil primero.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Aun no implementado");
        });

        adminPanel.add(addButton);
        adminPanel.add(deleteButton);
        adminPanel.add(editButton);
        adminPanel.add(markKidButton);

        return adminPanel;
    }

}
