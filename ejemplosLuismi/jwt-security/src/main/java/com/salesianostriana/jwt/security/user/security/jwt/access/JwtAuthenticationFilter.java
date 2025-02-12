package com.salesianostriana.jwt.security.user.security.jwt.access;

import com.salesianostriana.jwt.security.user.model.User;
import com.salesianostriana.jwt.security.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJwtAccessTokenFromRequest(request);

        //Validar el token
        //Si es válido, autenticar al usuario

        try {
            if(StringUtils.hasText(token) && jwtService.validateAcessToken(token))

            filterChain.doFilter(request,response);
        } catch (JwtException e) {
            throw new RuntimeException(e);
        }

        UUID id = jwtService.getUserIdFromAccessToken(token);

        Optional<User> result = userRepository.findById(id);

        if (result.isPresent()){
            User user = result.get();
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

    }
    private String getJwtAccessTokenFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(JwtService.TOKEN_HEADER);
        // Bearer kjljklsajdklajdlkj.askljdlkajdlkasj.jkasdjlakjdlj
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtService.TOKEN_PREFIX+" "))
            return bearerToken.substring(JwtService.TOKEN_PREFIX.length());
        return bearerToken;
    }
}
