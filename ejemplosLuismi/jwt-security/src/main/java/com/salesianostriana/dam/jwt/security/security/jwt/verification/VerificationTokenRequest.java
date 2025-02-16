package com.salesianostriana.dam.jwt.security.security.jwt.verification;

public record VerificationTokenRequest(String token) {

    public static VerificationTokenRequest of(String token) {
        return new VerificationTokenRequest(token);
    }

}
