package com.example.EduForums.topic;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
	List<Topic> findAll();

}
