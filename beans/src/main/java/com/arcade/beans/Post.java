package com.arcade.beans;

import lombok.Data;

@Data
public class Post {
    private String id;
    private long createdTime;
    private long modifiedTime;
    private String body;
}
