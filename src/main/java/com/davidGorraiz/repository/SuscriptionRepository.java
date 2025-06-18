package com.davidGorraiz.repository;

import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.User.User;

import java.util.List;

public interface SuscriptionRepository {
    public List<Suscription> findAll();
    public Suscription findById(int id);
    public void save(Suscription suscription, User user);
    public void update(int id, Suscription suscription);
    public void delete(int id);
}
