package com.davidGorraiz;

import com.davidGorraiz.UI.LoginFrame;
import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.model.Genre;
import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.Suscription.TipoSuscription;
import com.davidGorraiz.model.User.Rol;
import com.davidGorraiz.model.User.User;
import com.davidGorraiz.service.*;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.time.LocalDate;

import static com.davidGorraiz.model.Content.TipoContent.PELICULA;
import static com.davidGorraiz.model.User.Rol.ADMIN;
import static com.davidGorraiz.model.User.Rol.CLIENTE;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });
    }
}