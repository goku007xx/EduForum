package com.example.EduForums.post;


import com.example.EduForums.user.User;

import org.springframework.data.annotation.Id;

public class Post {
    @Id
	private String postId;

    private String postTitle;
    private User owner;

    public Post(String postId, String postTitle, User owner) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.owner = owner;
    }
    
    
}