package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.WatchHistory;
import com.davidGorraiz.repository.WatchHistoryRepository;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WatchHistoryService implements WatchHistoryRepository {
    private EntityManager em;

    public WatchHistoryService(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<WatchHistory> findAll() {
        List<WatchHistory> watchHistories = em.createQuery(
                "select w from WatchHistory w",
                WatchHistory.class
        ).getResultList();
        System.out.println("---- Historial de vistas encontrados ----");
        watchHistories.forEach(System.out::println);
        System.out.println();
        return watchHistories;
    }

    @Override
    public WatchHistory findById(int id) {
        WatchHistory watchHistory = em.find(WatchHistory.class, id);
        if (watchHistory != null) {
            System.out.println("---- Historial de vista  encontrado ----");
            System.out.println(watchHistory);
            System.out.println();
            return watchHistory;
        }else {
            System.out.println("---- El historial de vista no existe ----");
            return null;
        }
    }

    public WatchHistory findNotById(int profileId, int contentId, LocalDateTime fechaVisto, int duracionVista){
        WatchHistory watchHistories = findAll().stream()
                .filter(watchHistory -> watchHistory.getProfileId() == profileId
                        && watchHistory.getContentId() == contentId
                        && watchHistory.getFechaVisto().equals(fechaVisto)
                        && watchHistory.getDuracionVista() == duracionVista)
                .findFirst().orElse(null);

        if (watchHistories != null) {
            System.out.println("---- Historial de vista  encontrado ----");
            System.out.println(watchHistories);
            System.out.println();
            return watchHistories;
        }else {
            System.out.println("El historial de vista no existe");
            return null;
        }
    }

    @Override
    public void save(WatchHistory watchHistory) {
        ProfileService profileService = new ProfileService(em);
        ContentService contentService = new ContentService(em);
        System.out.println("---- Insertar historial de vista ----");
        List<Profile> profilesOreder = profileService.findAll().stream()
                .sorted(Comparator.comparing(profile -> profile.getId()))
                .collect(Collectors.toList());
        Profile profileLast = profilesOreder.get(profilesOreder.size() - 1);
        List<Content> contentOrder = contentService.findAll().stream()
                .sorted(Comparator.comparing(content -> content.getId()))
                .collect(Collectors.toList());
        Content contentLast = contentOrder.get(contentOrder.size() - 1);
        watchHistory.setProfileId(profileLast.getId() + 1);
        watchHistory.setContentId(contentLast.getId() + 1);
        em.persist(watchHistory);
        System.out.println();
    }

    @Override
    public void update(int id, WatchHistory watchHistory) {
        WatchHistory watchHistoryOld = em.find(WatchHistory.class, id);
        if (watchHistoryOld != null) {
            watchHistoryOld.setProfile(watchHistory.getProfile());
            watchHistoryOld.setProfileId(watchHistory.getProfileId());
            watchHistoryOld.setContent(watchHistory.getContent());
            watchHistoryOld.setContentId(watchHistory.getContentId());
            watchHistoryOld.setFechaVisto(watchHistory.getFechaVisto());
            watchHistoryOld.setDuracionVista(watchHistory.getDuracionVista());
            System.out.println("---- Actualizar historial de vista ----");
            em.merge(watchHistoryOld);
            System.out.println();
        }else {
            System.out.println("El historial de vista que quiere actualizar no existe");
        }
    }

    @Override
    public void delete(int id) {
        WatchHistory watchHistory = em.find(WatchHistory.class, id);
        if (watchHistory != null){
            System.out.println("---- Eliminar historial de vista ----");
            em.remove(watchHistory);
            System.out.println();
        } else {
            System.out.println("El historial de vista no existe");
        }
    }
}
