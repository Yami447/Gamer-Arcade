package com.arcade.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String password;
}
