package org.acme.employee.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "quarkus") // ganti dari "user" ke "users"
public class User extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String role; // "ADMIN" or "USER"
}
