package com.arcade.gamerarcade.api;

import com.arcade.gamerarcade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        return authService.signup(username, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }
}
