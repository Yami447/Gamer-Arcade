package com.arcade.beans;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@Data
@Accessors(chain = true)
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String email;
    private String password;
    private String role;
    private Set<String> games = new HashSet<>();
    private List<Post> posts = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<String> friends = new ArrayList<>();
    private List<String> pendingFriendRequests = new ArrayList<>();
    private List<String> sentFriendRequests = new ArrayList<>();
    private List<String> friendRecommendations = new ArrayList<>();
    private Map<String, String> friendRecommendationByGame;
}
