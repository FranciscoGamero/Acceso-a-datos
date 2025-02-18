package com.salesianostriana.ProyectoConecta.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("profesor")
                .password("{noop}1234")
                .roles("PROFESOR")
                .build();


        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, admin);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.POST, "/profesor", "/trabajador", "/empresa",
                        "/curso", "/demanda", "/convocatoria", "/titulo",
                        "/familiaProfesional").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/contacto", "/empresa", "/trabajador").hasAnyRole("ADMIN", "PROFESOR")

                .requestMatchers(HttpMethod.PUT, "/profesor/**", "/trabajador/**", "/empresa/**",
                        "/curso/**", "/demanda/**", "/convocatoria/**", "/titulo/**",
                        "/familiaProfesional/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/contacto/**", "/empresa/**", "/trabajador/**", "/usuario/**").hasAnyRole("ADMIN", "PROFESOR")

                .requestMatchers(HttpMethod.DELETE, "/profesor/**", "/trabajador/**", "/empresa/**",
                        "/curso/**", "/demanda/**", "/convocatoria/**", "/titulo/**",
                        "/familiaProfesional/**", "usuario/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/contacto/**", "/empresa/**", "/trabajador/**").hasAnyRole("ADMIN", "PROFESOR")

                .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "PROFESOR")
        );

        return http.build();
    }


}
