package com.salesianostriana.dam.jwt.security.security.jwt.verification;


import com.salesianostriana.dam.jwt.security.user.model.User;
import com.salesianostriana.dam.jwt.security.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public VerificationToken create(User user) {
        verificationTokenRepository.deleteByUser(user);
        return verificationTokenRepository.save(
                VerificationToken.builder()
                        .user(user)
                        .expireAt(Instant.now().plusSeconds(durationInMinutes*600))
                        .build()
        );
    }

    public User verifyUser(String verificationToken){
        Optional<VerificationToken> verification = verificationTokenRepository.findById(UUID.fromString(verificationToken));
        if (verification.isPresent()){
            User userVerificated = verification.get().getUser();
            userVerificated.setEnabled(true);
            userRepository.save(userVerificated);
            return userVerificated;
        } else {
            throw new VerificationTokenException("Token no valido");
        }
    }
}
