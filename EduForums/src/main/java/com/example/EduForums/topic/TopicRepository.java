package com.example.EduForums.topic;

import java.util.List;
// import java.util.Optional;

import com.example.EduForums.subject.Subject;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
	List<Topic> findAll();
	List<Topic> findTopicByBelongsToSubject(Subject subject);
	//Topic findTopicByTopicId(String topicId);

}
