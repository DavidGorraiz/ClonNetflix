package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Profile;
import com.davidGorraiz.model.Rating;
import com.davidGorraiz.repository.RatingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RatingService implements RatingRepository {
    private EntityManager em;

    public RatingService(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Rating> findAll() {
        List<Rating> ratings = em.createQuery(
                "select r from Rating r",
                Rating.class).getResultList();
        System.out.println("---- Ratings encontrados ----");
        ratings.forEach(System.out::println);
        System.out.println();
        return ratings;
    }

    @Override
    public Rating findById(int id) {
        Rating rating = em.find(Rating.class, id);
        if (rating != null) {
            System.out.println("---- Rating encontrado ----");
            System.out.println(rating);
            System.out.println();
            return rating;
        }else {
            System.out.println("El rating que se busca no existe");
            return null;
        }
    }

    public Double averageRate(int contentId){
        Query query = em.createNativeQuery("select avg(valor) as rate from rating\n" +
                "where content_id = ?");
        query.setParameter(1, contentId);

        Object result = query.getSingleResult();

        if (result == null) return null;

        System.out.println("---- Rate ----");
        System.out.println(result);
        System.out.println();

        return ((Number) result).doubleValue(); // 🔒 Seguro con Double, BigDecimal, etc.
        
    }

    public Rating findNotById(int valor, String comentario, Profile profile, Content content){
        List<Rating> ratings = em.createQuery(
                "select r from Rating r",
                Rating.class).getResultList();
        Rating ratingStream = ratings.stream()
                .filter(rating -> rating.getValor() == valor
                && rating.getComentario().equals(comentario)
                && rating.getProfile().equals(profile)
                && rating.getContent().equals(content))
                .findFirst().orElse(null);

        if (ratingStream != null) {
            System.out.println("---- Rating encontrado ----");
            System.out.println(ratingStream);
            System.out.println();
            return ratingStream;
        } else {
            System.out.println("El rating que se busca no existe");
            return null;
        }
    }

    @Override
    public void save(Rating rating) {
        ProfileService profileService = new ProfileService(em);
        ContentService contentService = new ContentService(em);
        System.out.println("---- Insertar rating ----");
        List<Profile> profilesOreder = profileService.findAll().stream()
                .sorted(Comparator.comparing(profile -> profile.getId()))
                .collect(Collectors.toList());
        Profile profileLast = profilesOreder.get(profilesOreder.size() - 1);
        List<Content> contentOrder = contentService.findAll().stream()
                .sorted(Comparator.comparing(content -> content.getId()))
                .collect(Collectors.toList());
        Content contentLast = contentOrder.get(contentOrder.size() - 1);
        rating.setProfileId(profileLast.getId() + 1);
        rating.setContentId(contentLast.getId() + 1);
        em.persist(rating);
        System.out.println();
    }

    @Override
    public void update(int id, Rating rating) {
        Rating oldRating = em.find(Rating.class, id);
        if (oldRating != null) {
            oldRating.setValor(rating.getValor());
            oldRating.setComentario(rating.getComentario());
            oldRating.setProfile(rating.getProfile());
            oldRating.setContent(rating.getContent());
            oldRating.setProfileId(rating.getProfileId());
            oldRating.setContentId(rating.getContentId());
            System.out.println("--- Actualizar rating ----");
            em.merge(oldRating);
            System.out.println();
        }else {
            System.out.println("El rating que se busca no existe");
        }
    }

    @Override
    public void delete(int id) {
        Rating rating = em.find(Rating.class, id);
        if (rating != null) {
            System.out.println("---- Eliminar rating ----");
            em.remove(rating);
            System.out.println();
        }else {
            System.out.println("El rating que quiere eliminar no existe");
        }
    }
}
