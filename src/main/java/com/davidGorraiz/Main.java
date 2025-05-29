package com.davidGorraiz;

import com.davidGorraiz.CRUD.GenreCRUD;
import com.davidGorraiz.util.UtilEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        System.out.println("---- Listar Generos ----");
        GenreCRUD genreCRUD = new GenreCRUD(em);
        genreCRUD.findAll();
    }
}