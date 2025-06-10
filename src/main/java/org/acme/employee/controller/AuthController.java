package org.acme.employee.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.employee.auth.AuthService;
import org.acme.employee.model.dto.LoginRequest;
import org.acme.employee.model.dto.LoginResponse;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword())
                .map(token -> Response.ok(new LoginResponse(token)).build())
                .orElse(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid username or password").build());
    }
}
