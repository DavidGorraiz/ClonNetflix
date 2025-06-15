package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.repository.ContentRepository;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContentService  implements ContentRepository {

    private EntityManager em;

    public ContentService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void findAll() {
        List<Content> contents = em.createQuery("select c from Content c", Content.class).getResultList();
        System.out.println("---- Listar contenido ----");
        contents.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public void findById(int id) {
        Content content = em.find(Content.class, id);
        System.out.println("---- Contenido encontrado ----");
        System.out.println(content);
        System.out.println();
    }

    @Override
    public void save(Content content) {
        System.out.println("---- Insertar contenido ----");
        em.persist(content);
        System.out.println();
    }

    @Override
    public void update(Content newContent, int id) {
        Content content = em.find(Content.class, id);
        if (content != null) {
            content.setTitulo(newContent.getTitulo());
            content.setDescripcion(newContent.getDescripcion());
            content.setTipo(newContent.getTipo());
            content.setFechaLanzamiento(newContent.getFechaLanzamiento());
            content.setDuracion(newContent.getDuracion());
            content.setClasificacion(newContent.getClasificacion());
            System.out.println("---- Actualizar contenido ----");
            em.merge(content);
            System.out.println();
        }else {
            System.out.println("El contenido que quiere actualizar no existe");
        }
    }

    @Override
    public void delete(int id) {

    }
}
