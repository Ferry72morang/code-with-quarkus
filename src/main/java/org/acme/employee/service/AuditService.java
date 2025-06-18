package org.acme.employee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.employee.model.entity.AuditLog;

import java.time.LocalDateTime;

@ApplicationScoped
public class AuditService {

    @Transactional
    public void log(String username, String action, String endpoint, String ipAddress) {
        AuditLog log = new AuditLog();
        log.user_name = username;
        log.action = action;
        log.endpoint = endpoint;
        log.timestamp = LocalDateTime.now();
        log.ip_address = ipAddress;
        log.persist();
    }
}
