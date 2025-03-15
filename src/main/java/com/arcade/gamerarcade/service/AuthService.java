package com.arcade.gamerarcade.service;

import com.arcade.gamerarcade.repo.UserRepository;
import com.arcade.gamerarcade.validate.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public String signup(String username, String password) {
        if (userRepository.findByUserName(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        userService.registerUser(username, password);
        return jwtUtil.generateToken(username);
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(username);
    }
}

