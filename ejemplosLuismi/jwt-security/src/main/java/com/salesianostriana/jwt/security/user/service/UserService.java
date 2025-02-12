package com.salesianostriana.jwt.security.user.service;

import com.salesianostriana.jwt.security.user.dto.CreateUserDto;
import com.salesianostriana.jwt.security.user.model.User;
import com.salesianostriana.jwt.security.user.model.UserRole;
import com.salesianostriana.jwt.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto createUserDto){
        User user = User.builder()
                .username(createUserDto.username())
                .password(passwordEncoder.encode(createUserDto.password()))
                .roles(Set.of(UserRole.USER))
                .build();

        return userRepository.save(user);
    }
}
