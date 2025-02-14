package com.salesianostriana.dam.jwt.security.security.jwt.verification;

import com.salesianostriana.dam.jwt.security.security.exceptionhandling.JwtException;

public class VerificationTokenException extends JwtException {
    public VerificationTokenException(String s) {
        super(s);
    }
}
