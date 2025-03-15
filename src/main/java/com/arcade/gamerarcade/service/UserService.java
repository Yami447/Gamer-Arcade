package com.arcade.gamerarcade.service;

import com.arcade.beans.User;
import com.arcade.gamerarcade.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(String username, String password) {
        Optional<User> existingUser = userRepository.findByUserName(username);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists!");
        }

        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt password
        user.setRole("USER");

        userRepository.save(user);
        return "User registered successfully!";
    }
}
