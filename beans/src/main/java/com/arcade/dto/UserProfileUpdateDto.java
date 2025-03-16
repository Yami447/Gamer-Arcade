package com.arcade.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserProfileUpdateDto {
    @NotNull
    private String username;
    private String updatedEmail;
    private String updatedPassword;
    private Set<String> addGames;
    private Set<String> deleteGames;
}
