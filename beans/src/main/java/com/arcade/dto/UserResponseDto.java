package com.arcade.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private String username;
    private String email;
    private String role;
    private List<String> games;
    private List<String> friends;
    private String jwtToken;
}
