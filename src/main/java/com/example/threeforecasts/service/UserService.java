package com.example.threeforecasts.service;

import com.example.threeforecasts.model.User;
import com.example.threeforecasts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register (String username, String email, String rawPassword) {
        if (existsByUsername (username)) {
            throw new IllegalArgumentException ("Username already exists");
        } else if (existsByEmail (email)) {
            throw new IllegalArgumentException ("Email already exists");
        }

        User user = new User (username, email, this.passwordEncoder.encode (rawPassword));
        return this.userRepository.save (user);
    }

    public Optional <User> findByUsername (String username) {
        return this.userRepository.findByUsername (username);
    }

    public Optional <User> findByEmail (String email) {
        return this.userRepository.findByEmail (email);
    }

    public boolean existsByUsername (String username) {
        return this.userRepository.existsByUsername (username);
    }

    public boolean existsByEmail (String email) {
        return this.userRepository.existsByEmail (email);
    }
}