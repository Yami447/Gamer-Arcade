package com.arcade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddFriendDto {
    @NotBlank
    private String sourceUsername;
    @NotBlank
    private String targetUsername;
}
