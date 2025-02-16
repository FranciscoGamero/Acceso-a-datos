package com.salesianostriana.dam.jwt.security.security.jwt.verification;


import com.salesianostriana.dam.jwt.security.user.model.User;
import com.salesianostriana.dam.jwt.security.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
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

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;


    @Value("${jwt.refresh.duration}")
    private int durationInMinutes;

    public VerificationToken create(User user) {
        verificationTokenRepository.deleteByUser(user);

        // Crear el token de verificación
        VerificationToken verificationToken = verificationTokenRepository.save(
                VerificationToken.builder()
                        .user(user)
                        .expireAt(Instant.now().plusSeconds(durationInMinutes * 600)) // Esto está bien calculado como segundos
                        .build());

        Email email = EmailBuilder.startingBlank()
                .from("sevillista916@gmial.com")
                .to(user.getCorreo())
                .withSubject("Correo de verificación")
                .withPlainText("El código de verificación es el siguiente: " + verificationToken.getToken())
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(mailHost, mailPort)
                .buildMailer();

        mailer.sendMail(email);

        return verificationToken;
    }

    public User verifyUser(String token) {
        Optional<VerificationToken> verification = verificationTokenRepository.findById(UUID.fromString(token));
        if (verification.isPresent()) {
            User userVerificated = verification.get().getUser();
            userVerificated.setEnabled(true);
            userRepository.save(userVerificated);
            return userVerificated;
        } else {
            throw new VerificationTokenException("Token no valido");
        }
    }

}
