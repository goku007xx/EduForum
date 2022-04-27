package com.example.EduForums.post;

import java.util.Optional;

import com.example.EduForums.subject.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository) {
	    this.postRepository = postRepository;
	}


    public void savePost(Post post) {
        postRepository.save(post);
    }
    

    public Post getPostById(String id) {
        Optional<Post> p = postRepository.findById(id);
        return p.get();
    }
    
}
