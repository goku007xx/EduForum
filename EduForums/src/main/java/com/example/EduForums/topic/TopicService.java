package com.example.EduForums.topic;

import java.util.List;

import com.example.EduForums.subject.Subject;
import com.example.EduForums.subject.SubjectService;
import com.example.EduForums.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

    
    public Topic createTopic(String topicName, Subject belongsToSubject, User owner)
    {
        return new Topic(topicName, belongsToSubject, owner);
    }

    public void saveTopic(Topic topic)
    {
        topicRepository.save(topic);
    }

    public void closeTopic(String email,Topic topic){
        if(topic.getOwner().getEmail()==email)
            topic.setStatus(TopicStatus.CLOSED);        // update db as well as obj
        else
            throw new IllegalStateException("No access form email provided");
    }

    public List<Topic> getTopicsBySubject(Subject subject){
        return topicRepository.findTopicByBelongsToSubject(subject);
    }

}

