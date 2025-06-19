package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.ContentGenre.ContentGenre;
import com.davidGorraiz.model.ContentGenre.ContentGenreKey;
import com.davidGorraiz.model.Genre;
import com.davidGorraiz.repository.ContentGenreRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContentGenreService  implements ContentGenreRepository {
    private EntityManager em;

    public ContentGenreService(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ContentGenre> findAll() {
        List<ContentGenre> contentGenres = em.createQuery(
                "select cg from ContentGenre cg",
                ContentGenre.class
        ).getResultList();
        System.out.println("---- Contenidos Generos  ----");
        contentGenres.forEach(System.out::println);
        System.out.println();
        return contentGenres;
    }

    @Override
    public ContentGenre findById(ContentGenreKey id) {
        ContentGenre contentGenre = em.find(ContentGenre.class, id);
        if (contentGenre != null) {
            System.out.println("---- Contenido Genero ----");
            System.out.println(contentGenre);
            System.out.println();
            return contentGenre;
        } else {
            System.out.println("El contenido genero no existe");
            return null;
        }
    }

    @Override
    public void save(ContentGenre contentGenre) {
        ContentService contentService = new ContentService(em);
        GenreService genreService = new GenreService(em);
        System.out.println("---- Insertar Contenido Genero ----");
        List<Content> contentOrder = contentService.findAll().stream()
                .sorted(Comparator.comparing(content -> content.getId()))
                .collect(Collectors.toList());
        Content contentLast = contentOrder.get(contentOrder.size() - 1);
        List<Genre> genresOrder = genreService.findAll().stream()
                .sorted(Comparator.comparing(genre -> genre.getId()))
                .collect(Collectors.toList());
        Genre genreLast = genresOrder.get(genresOrder.size() - 1);
        contentGenre.setContentId(contentLast.getId() + 1);
        contentGenre.setGenreId(genreLast.getId() + 1);
        em.persist(contentGenre);
        System.out.println();
    }


    @Override
    public void delete(ContentGenreKey id) {
        ContentGenre contentGenre = em.find(ContentGenre.class, id);
        if (contentGenre != null) {
            System.out.println("---- Eliminar Contenido Genero ----");
            em.remove(contentGenre);
            System.out.println();
        }else {
            System.out.println("El contenido genero no existe");
        }
    }

}
