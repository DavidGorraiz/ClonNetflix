package com.davidGorraiz.UI;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.model.Episode;
import com.davidGorraiz.service.EpisodeService;
import com.davidGorraiz.service.RatingService;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Set;

public class DetailFrame extends JFrame {

    private Content titulo;
    private EntityManager em = UtilEntity.getEntityManager();
    private EpisodeService episodeService = new EpisodeService(em);
    private RatingService ratingService = new RatingService(em);

    public DetailFrame(Content titulo) {
        this.titulo = titulo;

        setTitle("Detalle - " + titulo.getTitulo());
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior: imagen + texto
        JPanel topPanel = new JPanel(new BorderLayout());

        // Portada (simulada)
        JLabel imageLabel = new JLabel("Portada");
        imageLabel.setPreferredSize(new Dimension(150, 220));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.add(imageLabel, BorderLayout.WEST);

        // Info textual
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(titulo.getTitulo());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        double rate = ratingService.averageRate(titulo.getId());

        JLabel durationLabel = new JLabel(
                titulo.getTipoContent().equals(TipoContent.PELICULA) ? "Duración: " + titulo.getDuracion() + " min" : rate + " rate"
        );

        JTextArea descriptionArea = new JTextArea(titulo.getDescripcion());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(infoPanel.getBackground());

        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(durationLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(descriptionArea);

        topPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton playButton = new JButton("▶ Reproducir");
        JButton addButton = new JButton("➕ Mi lista");
        JButton rateButton = new JButton("⭐ Calificar");
        JButton trailerButton = new JButton("🎬 Ver tráiler");

        buttonPanel.add(playButton);
        buttonPanel.add(addButton);
        buttonPanel.add(rateButton);
        buttonPanel.add(trailerButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Si es serie, mostramos temporadas y episodios (simulados)
        if (titulo.getTipoContent().equals(TipoContent.SERIE)) {
            JPanel seriesPanel = new JPanel();
            seriesPanel.setLayout(new BorderLayout());
            seriesPanel.setBorder(BorderFactory.createTitledBorder("Episodios"));

            List<Episode> episodes = episodeService.findByContentId(titulo.getId());

            Set<Integer> temporadas = new HashSet<>();
            for (int i = 0; i < episodes.size(); i++) {
                Episode episode = episodes.get(i);
                temporadas.add(episode.getTemporada());
            }
            String[] temporadasString = new String[temporadas.size()];
            for (int i = 0; i < temporadas.size(); i++) {
                temporadasString[i] = "Temporada " + temporadas.toString();
            }

            JComboBox<String> seasonSelector = new JComboBox<>(temporadasString);

            String[] episodiosTitulo = new String[episodes.size()];
            for (int i = 0; i < episodes.size(); i++) {
                episodiosTitulo[i] = episodes.get(i).getTitulo();
            }

            JList<String> episodeList = new JList<>(episodiosTitulo);

            seriesPanel.add(seasonSelector, BorderLayout.NORTH);
            seriesPanel.add(new JScrollPane(episodeList), BorderLayout.CENTER);

            mainPanel.add(seriesPanel, BorderLayout.SOUTH);
        }

        add(mainPanel);
    }
}
