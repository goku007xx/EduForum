package com.example.EduForums.post;


import com.example.EduForums.topic.Topic;
import com.example.EduForums.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Post")
public class Post {
    @Id
	private String postId;

    private String postTitle;
    private String postDescription;

    @DBRef
    private Topic topic;
    
    @Transient
    private Integer upvotes;
    @Transient
    private Integer downvotes;
    private User owner;

    public Post(String postId, String postTitle,String postDescription, User owner, Topic topic) {
        //this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.topic = topic;
        this.owner = owner;
        this.downvotes = 0;
        this.upvotes = 0;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Post(){
        this.downvotes = 0;
        this.upvotes = 0;
    }

    public void upv() {
        this.upvotes++;   
    }

    public void downv() {
        this.downvotes--;   
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    
    
}