package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.repository.ContentRepository;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContentService  implements ContentRepository {

    private EntityManager em;

    public ContentService() {
        this.em = UtilEntity.getEntityManager();
    }

    @Override
    public void findAll() {
        em.getTransaction().begin();
        List<Content> contents = em.createQuery("select c from Content c", Content.class).getResultList();
        System.out.println("---- Listar contenido ----");
        contents.forEach(System.out::println);
        System.out.println();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void findById(int id) {

    }

    @Override
    public void save(Content content) {

    }

    @Override
    public void update(Content content, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
