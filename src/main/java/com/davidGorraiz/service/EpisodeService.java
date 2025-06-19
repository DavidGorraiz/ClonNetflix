package com.davidGorraiz.service;

import com.davidGorraiz.model.Episode;
import com.davidGorraiz.repository.EpisodeRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EpisodeService implements EpisodeRepository {
    private EntityManager em;

    public EpisodeService(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Episode> findAll() {
        List<Episode> episodes = em.createQuery(
                "select e from Episode e", Episode.class
        ).getResultList();
        System.out.println("---- Episodios encontrados ----");
        episodes.forEach(System.out::println);
        System.out.println();
        return episodes;
    }

    @Override
    public Episode findById(int id) {
        Episode episode = em.find(Episode.class, id);
        if (episode != null) {
            System.out.println("---- Episodio encontrado ----");
            System.out.println(episode);
            System.out.println();
            return episode;
        } else {
            System.out.println("El episode que quiere encontrar no existe");
            return null;
        }
    }

    public Episode findNotById(String titulo, String descripcion, int numeroEpisodio, int temporada, int duracion, int contentId) {
        List<Episode> episodes = em.createQuery(
                "select e from Episode e",
                Episode.class
        ).getResultList();
        Stream<Episode> episodeStream = episodes.stream()
                .filter(ep -> ep.getTitulo().equals(titulo) && ep.getDescripcion().equals(descripcion)
                && ep.getNumeroEpisodio() == numeroEpisodio && ep.getTemporada() == temporada
                && ep.getDuracion() == duracion && ep.getContentId() == contentId);
        Episode episode = episodeStream.findFirst().orElse(null);
        if (episode != null) {
            System.out.println("---- Episodio encontrado ----");
            System.out.println(episode);
            System.out.println();
            return episode;
        } else {
            System.out.println("El episode que quiere encontrar no existe");
            return null;
        }
    }

    @Override
    public void save(Episode episode) {
        System.out.println("---- Insertar episodio ----");
        em.persist(episode);
        System.out.println(episode);
        System.out.println();
    }

    @Override
    public void update(int id, Episode episode) {
        Episode oldEpisode = em.find(Episode.class, id);
        if (oldEpisode != null) {
            oldEpisode.setTitulo(episode.getTitulo());
            oldEpisode.setDescripcion(episode.getDescripcion());
            oldEpisode.setNumeroEpisodio(episode.getNumeroEpisodio());
            oldEpisode.setTemporada(episode.getTemporada());
            oldEpisode.setDuracion(episode.getDuracion());
            oldEpisode.setContent(episode.getContent());
            oldEpisode.setContentId(episode.getContentId());
            System.out.println("---- Actualizar episodio ----");
            em.merge(oldEpisode);
            System.out.println(oldEpisode);
            System.out.println();
        } else {
            System.out.println("El episode que quiere encontrar no existe");
        }
    }

    @Override
    public void delete(int id) {
        Episode episode = em.find(Episode.class, id);
        if (episode != null) {
            System.out.println("---- Eliminar episodio ----");
            em.remove(episode);
            System.out.println(episode);
            System.out.println();
        }else {
            System.out.println("El episode que quiere eliminar no existe");
        }
    }
}
