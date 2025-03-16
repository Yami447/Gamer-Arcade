package com.arcade.beans;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private Set<String> games;
    private List<Post> posts;
    private List<Comment> comments;
    private List<String> friends;
    private List<String> pendingFriendRequests;
    private List<String> sentFriendRequests;
    private List<String> friendRecommendations;
    private Map<String, String> friendRecommendationByGame;
}
