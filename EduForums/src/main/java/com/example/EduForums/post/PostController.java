package com.example.EduForums.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {
    
    private final PostService postService;
	//private final SubjectService subjectService;
	
	@Autowired
	public PostController(PostService postService) {
	    this.postService = postService;
	}
}
