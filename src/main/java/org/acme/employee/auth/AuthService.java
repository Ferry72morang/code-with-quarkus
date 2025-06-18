package org.acme.employee.auth;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.employee.model.dto.RegisterRequest;
import org.acme.employee.model.entity.User;
import org.acme.employee.repository.UserRepository;
import org.acme.employee.service.AuditService;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Inject
    AuditService auditService;

    private static final String SECRET_KEY = "Ferrysitumorangsecretkey123456!!"; // minimal 32 karakter

    public Optional<String> login(String username, String password) {
        User user = userRepository.find("username", username).firstResult();
        if (user == null || !BCrypt.checkpw(password, user.password)) {
            return Optional.empty();
        }

        String token = Jwt.claims()
                .issuer("quarkus-jwt")
                .upn(user.username)
                .groups(Set.of(user.role))
                .expiresIn(Duration.ofHours(1))
                .signWithSecret(SECRET_KEY); // langsung gunakan secret
        auditService.log(user.username, "LOGIN", "/login", "127.0.0.1");
        return Optional.of(token);
    }

    @Transactional
    public boolean register(RegisterRequest request,String curentUser) {
        if (User.find("username", request.getUsername()).firstResult() != null) {
            return false; // user sudah ada
        }

        User user = new User();
        user.username = request.getUsername();
        user.password = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        user.role = request.getRole();
        user.active = true;
        user.persist();

        auditService.log(curentUser, "REGISTER_USER", "/register", "127.0.0.1");
        return true;
    }
}
