package com.example.EduForums.topic;

import org.springframework.data.annotation.Id;

public class Topic {
	@Id
	private String topicId;
	private String topicName;
	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}
	

}
