package org.acme.employee.model.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // "ADMIN" atau "USER"
}
