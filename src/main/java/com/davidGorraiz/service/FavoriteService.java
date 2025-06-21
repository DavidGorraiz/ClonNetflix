package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Favorite;
import com.davidGorraiz.model.Profile;
import com.davidGorraiz.repository.FavoriteRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FavoriteService  implements FavoriteRepository {
    private EntityManager em;

    public FavoriteService(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Favorite> findAll() {
        List<Favorite> favorites = em.createQuery(
                "select f from Favorite f",
                Favorite.class
        ).getResultList();
        System.out.println("---- Favoritos encontrados ----");
        favorites.forEach(System.out::println);
        System.out.println();
        return favorites;
    }

    @Override
    public Favorite findById(int id) {
        Favorite favorite = em.find(Favorite.class, id);
        if (favorite != null) {
            System.out.println("---- Favorito encontrado ----");
            System.out.println(favorite);
            System.out.println();
            return favorite;
        } else {
            System.out.println("El favorito no existe");
            return null;
        }
    }

    @Override
    public void save(Favorite favorite) {
        ProfileService profileService = new ProfileService(em);
        ContentService contentService = new ContentService(em);
        System.out.println("---- Insertar favorito ----");
        List<Profile> profilesOreder = profileService.findAll().stream()
                .sorted(Comparator.comparing(profile -> profile.getId()))
                .collect(Collectors.toList());
        Profile profileLast = profilesOreder.get(profilesOreder.size() - 1);
        List<Content> contentOrder = contentService.findAll().stream()
                .sorted(Comparator.comparing(content -> content.getId()))
                .collect(Collectors.toList());
        Content contentLast = contentOrder.get(contentOrder.size() - 1);
        favorite.setProfileId(profileLast.getId() + 1);
        favorite.setContentId(contentLast.getId() + 1);
        em.persist(favorite);
        System.out.println();
    }

    @Override
    public void update(int id, Favorite favorite) {
        Favorite favoriteOld = em.find(Favorite.class, id);
        if (favoriteOld != null) {
            favoriteOld.setProfile(favorite.getProfile());
            favoriteOld.setProfileId(favorite.getProfileId());
            favoriteOld.setContent(favorite.getContent());
            favoriteOld.setContentId(favorite.getContentId());
            System.out.println("---- Actualizar favorito ----");
            em.merge(favoriteOld);
            System.out.println();
        } else {
            System.out.println("El favorito que quiere actualizar no existe");
        }
    }

    @Override
    public void delete(int id) {
        Favorite favorite = em.find(Favorite.class, id);
        if (favorite != null) {
            System.out.println("---- Eliminar favorito ----");
            em.remove(favorite);
            System.out.println();
        } else {
            System.out.println("El favorito que quieres eliminar no existe");
        }
    }
}
