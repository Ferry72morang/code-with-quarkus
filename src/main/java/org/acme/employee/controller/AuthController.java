package org.acme.employee.controller;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
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
import org.acme.employee.model.dto.RegisterRequest;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @Inject
    SecurityIdentity identity;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword())
                .map(token -> Response.ok(new LoginResponse(token)).build())
                .orElse(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid username or password").build());
    }

    @POST
    @Path("/register")
    @RolesAllowed("ADMIN") // ⬅️ Hanya bisa diakses oleh ADMIN
    public Response register(RegisterRequest request) {
        String currentUser = identity.getPrincipal().getName();

        boolean success = authService.register(request,currentUser);
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("Username already exists").build();
        }
    }
}
