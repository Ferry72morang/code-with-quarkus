package org.acme.employee.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "employee", schema = "quarkus")
@Data
@Entity
public class Employee extends PanacheEntity {
    public String name;
    public String email;
    public String position;
}