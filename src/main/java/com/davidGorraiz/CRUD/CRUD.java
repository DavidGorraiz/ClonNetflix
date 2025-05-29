package com.davidGorraiz.CRUD;

import jakarta.persistence.EntityManager;

import java.util.List;

public abstract class CRUD <T>{

    protected EntityManager em;

    public CRUD(EntityManager em) {
        this.em = em;
    }

    protected abstract void findAll();
}
