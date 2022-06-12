package com.app.reservation.service;

import lombok.RequiredArgsConstructor;

import com.app.reservation.domain.User;
import com.app.reservation.exception.BadRequestException;
import com.app.reservation.repository.UserRepository;
import com.app.reservation.web.rest.resource.UserResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    @Lazy
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s doesn't exist", username)));
    }

    public void register(UserResource userResource) {
        User user = User.builder()
                .username(userResource.getUsername())
                .password(passwordEncoder.encode(userResource.getPassword()))
                .build();

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Username not valid");
        }
    }

}
