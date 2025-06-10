package org.acme.employee.auth;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.employee.model.entity.User;
import org.acme.employee.repository.UserRepository;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    public Optional<String> login(String username, String password) {
        User user = userRepository.find("username", username).firstResult();
        if (user == null) {
            return Optional.empty();
        }

        // Sementara: password plaintext check (sebaiknya hash di real project)
        if (!user.password.equals(password)) {
            return Optional.empty();
        }

        // Generate JWT token
        String token = Jwt.issuer("quarkus-jwt")
                .upn(user.username)
                .groups(Set.of(user.role)) // e.g., ADMIN or USER
                .expiresIn(Duration.ofHours(1))
                .sign();

        return Optional.of(token);
    }
}
