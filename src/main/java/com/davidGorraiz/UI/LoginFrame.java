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

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Bienvenido a CloneFlix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centra la ventana

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("CloneFlix");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        JLabel emailLabel = new JLabel("Correo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Iniciar Sesión");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Registrarse");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(registerButton, gbc);

        JButton forgotPasswordButton = new JButton("¿Olvidaste tu contraseña?");
        forgotPasswordButton.setBorderPainted(false);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(forgotPasswordButton, gbc);

        // Listeners (provisionalmente solo muestran mensajes)
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Aquí deberías validar contra la base de datos

            EntityManager em = UtilEntity.getEntityManager();
            UserService userService = new UserService(em);

            List<User> users = userService.findAll();

            boolean checkUser = false;
            User actualUser = null;

            for (User user : users) {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    checkUser = true;
                    actualUser = user;
                }
            }

            if (checkUser) {
                JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!");
                // Ir a selección de perfil

                ProfileService profileService = new ProfileService(em);
                List<Profile> perfiles = profileService.findByUser(actualUser);

                ProfileSelectionFrame selector = new ProfileSelectionFrame(perfiles, actualUser);
                selector.setVisible(true);
                this.dispose(); // Cierra la ventana de login
            } else {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
            }
        });

        registerButton.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
        });

        forgotPasswordButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función de recuperación pendiente.");
        });

        add(panel);
    }
}
