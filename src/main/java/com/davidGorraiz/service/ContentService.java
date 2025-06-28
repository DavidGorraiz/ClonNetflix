package com.davidGorraiz.service;

import com.davidGorraiz.model.Content.Content;
import com.davidGorraiz.model.Content.TipoContent;
import com.davidGorraiz.repository.ContentRepository;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ContentService  implements ContentRepository {

    private EntityManager em;

    public ContentService(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Content> findAll() {
        List<Content> contents = em.createQuery("select c from Content c", Content.class).getResultList();
        System.out.println("---- Listar contenido ----");
        contents.forEach(System.out::println);
        System.out.println();
        return contents;
    }

    @Override
    public Content findById(int id) {
        Content content = em.find(Content.class, id);
        System.out.println("---- Contenido encontrado ----");
        System.out.println(content);
        System.out.println();
        return content;
    }

    public Content findByNotId(String titulo, String descripcion, TipoContent tipoContent, LocalDate fechaLanzamiento, int duracion, String clasificacion) {
        Content content = em.createQuery(
                "select c from Content c " +
                        "where c.titulo = :titulo and " +
                        "c.descripcion = :descripcion and " +
                        "c.tipoContent = :tipo and " +
                        "c.fechaLanzamiento = :fecha and " +
                        "c.duracion = :duracion and " +
                        "c.clasificacion = :clasificacion",
                Content.class
        ).setParameter("titulo", titulo)
                .setParameter("descripcion", descripcion)
                .setParameter("tipo", tipoContent)
                .setParameter("fecha", fechaLanzamiento)
                .setParameter("duracion", duracion)
                .setParameter("clasificacion", clasificacion)
                .getSingleResult();

        System.out.println("---- Contenido encontrado ----");
        System.out.println(content);
        System.out.println();
        return content;
    }

    public List<Content> findByType(TipoContent tipoContent) {
        List<Content> contents = findAll().stream()
                .filter(content -> content.getTipoContent().equals(tipoContent))
                .toList();
        System.out.println("---- Listar contenido por tipo ----");
        contents.forEach(System.out::println);
        System.out.println();
        return contents;
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
        Content content = em.find(Content.class, id);
        if (content != null) {
            System.out.println("---- Eliminar contenido ----");
            em.remove(content);
        }else{
            System.out.println("El contenido que quiere eliminar no existe");
        }
    }
}
