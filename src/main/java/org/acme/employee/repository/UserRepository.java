package org.acme.employee.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.employee.model.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }
}