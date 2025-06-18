package org.acme.employee.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "quarkus") // ganti dari "user" ke "users"
public class User extends PanacheEntity {

    @SequenceGenerator(
            name = "userSeq",
            sequenceName = "quarkus.users_seq", // ini yang akan dicari oleh Hibernate
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    public Long id;

    public String username;
    public String password;
    public String role;
    public boolean active;
}
