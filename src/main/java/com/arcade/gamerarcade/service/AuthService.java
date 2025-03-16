package com.arcade.gamerarcade.service;

import com.arcade.dto.UserCreateDto;
import com.arcade.dto.UserResponseDto;
import com.arcade.gamerarcade.repo.UserRepository;
import com.arcade.gamerarcade.service.user.UserService;
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

    public UserResponseDto signup(UserCreateDto userCreateDto) {
        if (userRepository.findByUsername(userCreateDto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        UserResponseDto userResponseDto = userService.createUser(userCreateDto);
        userResponseDto.setJwtToken(jwtUtil.generateToken(userResponseDto.getUsername()));
        return userResponseDto;
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(username);
    }
}

