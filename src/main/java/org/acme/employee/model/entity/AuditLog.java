package org.acme.employee.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "audit_log", schema = "quarkus")
public class AuditLog extends PanacheEntity {
    public String user_name;
    public String action;
    public String endpoint;
    public LocalDateTime timestamp;
    public String ip_address; // optional
}
