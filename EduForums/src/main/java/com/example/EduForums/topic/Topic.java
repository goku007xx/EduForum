package com.example.EduForums.topic;

import java.util.ArrayList;

import com.example.EduForums.post.Post;
import com.example.EduForums.subject.Subject;
import com.example.EduForums.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Topic")
public class Topic {
	@Id
	private String topicId;
	private String topicName;
	private Subject belongsToSubject;
	private User owner; 
	private TopicStatus status;
	private ArrayList<Post> topicPosts;
	
	public Topic( String topicName, Subject belongsToSubject, User owner) {
		// this.topicId = topicId;
		this.topicName = topicName;
		this.belongsToSubject = belongsToSubject;
		this.owner = owner;
		this.status = TopicStatus.ONGOING;
	}

	public Topic(){

	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Subject getBelongsToSubject() {
		return belongsToSubject;
	}

	public void setBelongsToSubject(Subject belongsToSubject) {
		this.belongsToSubject = belongsToSubject;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public ArrayList<Post> getTopicPosts() {
		return topicPosts;
	}

	public void setTopicPosts(ArrayList<Post> topicPosts) {
		this.topicPosts = topicPosts;
	}

	

	
}
