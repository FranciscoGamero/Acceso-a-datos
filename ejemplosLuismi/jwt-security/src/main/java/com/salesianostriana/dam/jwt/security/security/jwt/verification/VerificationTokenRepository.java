package com.salesianostriana.dam.jwt.security.security.jwt.verification;

import com.salesianostriana.dam.jwt.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {
    void deleteByUser(User user);
}
