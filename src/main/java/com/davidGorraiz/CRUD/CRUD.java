package com.davidGorraiz.CRUD;

import com.davidGorraiz.model.Genre;
import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class CRUD <T>{

    protected EntityManager em;

    public CRUD(EntityManager em) {
        this.em = em;
    }

    protected abstract void findAll();
    protected abstract void findById(int id);
    protected abstract void save(Object o);
    protected abstract void update(int id, Object o);
    protected abstract void delete(int id);
}
