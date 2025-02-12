package com.salesianostriana.jwt.security.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "user entity")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NaturalId //Buscar que hace
    @Column(unique = true, updatable = false)
    private String username;
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    @Override
    //Devuelve una coleccion de cualquier clase que implemente GrantedAuthority
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return roles.stream()
               //Transforma los roles en un string ROLE_ADMIN (Por ejemplo)
               .map(role -> "ROLE_"+ role)
               //Lo convierte en una clase que implementa GrantedAuthority
               .map(SimpleGrantedAuthority::new)
               .collect(Collectors.toSet());
    }
}
