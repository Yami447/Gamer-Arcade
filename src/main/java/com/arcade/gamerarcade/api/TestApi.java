package com.arcade.gamerarcade.api;


import com.arcade.gamerarcade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestApi {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API passed authentication");
    }

}
