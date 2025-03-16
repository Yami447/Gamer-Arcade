package com.arcade.beans;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String sourceId;
    private String body;
    private String createdTime;
    private String modifiedTime;
}
