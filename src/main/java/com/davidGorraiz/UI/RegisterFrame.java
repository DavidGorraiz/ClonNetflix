package com.davidGorraiz.UI;

import com.davidGorraiz.model.User.Rol;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.UserService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RegisterFrame  extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField usernameField;

    public RegisterFrame() {
        setTitle("Registro - CloneFlix");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    public void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Crear Cuenta");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
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

        JLabel confirmPasswordLabel = new JLabel("Confirmar Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(confirmPasswordField, gbc);

        JLabel usernameLabel = new JLabel("Nombre de usuario:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(usernameField, gbc);

        JButton registerButton = new JButton("Crear cuenta");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        // Listener del botón
        registerButton.addActionListener(e -> handleRegister());

        add(panel);
    }

    private void handleRegister(){
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String username = usernameField.getText().trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            return;
        }

        // Aquí se podría guardar en la base de datos o en una lista

        EntityManager em = UtilEntity.getEntityManager();
        UserService userService = new UserService(em);

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setNombreCompleto(username);
        newUser.setFechaRegistro(LocalDate.now());
        newUser.setRol(Rol.CLIENTE);

        if (userService.findByEmail(email) == null) {

            em.getTransaction().begin();
            userService.save(newUser);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "¡Cuenta creada exitosamente para " + username + "!");
            dispose(); // Cierra la ventana de registro

        } else {
            JOptionPane.showMessageDialog(this, "El usuario  con ese email ya existe");
        }
    }
}
