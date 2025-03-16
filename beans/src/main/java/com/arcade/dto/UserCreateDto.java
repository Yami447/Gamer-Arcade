package com.arcade.dto;

import com.arcade.beans.Comment;
import com.arcade.beans.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Map;

@Data
public class UserCreateDto {
    @NotBlank
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String role;
    private List<String> games;
}
