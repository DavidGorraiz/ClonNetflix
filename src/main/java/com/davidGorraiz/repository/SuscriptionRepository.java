package com.davidGorraiz.repository;

import com.davidGorraiz.model.Suscription.Suscription;
import com.davidGorraiz.model.User.User;

public interface SuscriptionRepository {
    public void findAll();
    public void findById(int id);
    public void save(Suscription suscription, User user);
    public void update(int id, Suscription suscription);
    public void delete(int id);
}
