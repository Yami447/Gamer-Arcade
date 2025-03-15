package com.arcade.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Accessors(chain = true)
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String role;
}
