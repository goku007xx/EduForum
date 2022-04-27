package com.example.EduForums.post;

import java.util.ArrayList;

import com.example.EduForums.subject.SubjectService;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.topic.Topic;
import com.example.EduForums.topic.TopicService;
import com.example.EduForums.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
    
    private final PostService postService;
	private final TopicService topicService;
	private final SubjectService subjectService;
	//private final SubjectService subjectService;
	
	@Autowired
	public PostController(PostService postService,TopicService topicService, SubjectService subjectService) {
	    this.postService = postService;
		this.topicService = topicService;
		this.subjectService =subjectService;
	}
	
	@GetMapping("topic/{topicid}")
	public String showTopic(@PathVariable("topicid") String topicid, Model model, HttpSession session)
	{
		// TODO
		// Perform logic to check if session obj is owner or has acess before displaying
		//  CHECK SESSION
		User udSession = (User)session.getAttribute("teacher");
		
		if(udSession==null)
		{
			udSession = (User)session.getAttribute("student");
			if(udSession==null)
			{
				System.out.println("Need to login first");
				model.addAttribute("check", "Need to login first");
				return "redirect:../";
			}
		}

		
		/*
		Subject subject = subjectService.getSubject(topicid);
		model.addAttribute("subject", subject);

		List<Topic> topics_list = topicService.getTopicsBySubject(subject);
		model.addAttribute("topics", topics_list);
		*/



		System.out.println(topicid);
		Topic topic = topicService.getTopicbyid(topicid);

		if(!subjectService.isSubAllowUser(udSession.getEmail(), topic.getBelongsToSubject().getSubjectCode()))
		{
			System.out.println("NO access");
			model.addAttribute("check", "No access");
			return "redirect:../";
		}
		//System.out.println(topic);
		model.addAttribute("topic", topic);
		model.addAttribute("posts", topic.getTopicPosts());

		Post po = new Post();
		model.addAttribute("po", po);
		return "topic/home";
	}
	
	@PostMapping("post/addpost")
	public String addpost(@ModelAttribute("po") Post post, Model model, HttpSession session,HttpServletRequest request)
	{
		//  CHECK SESSION
		User udSession = (User)session.getAttribute("teacher");
		
		if(udSession==null)
		{
			udSession = (User)session.getAttribute("student");
			if(udSession==null)
			{
				System.out.println("Need to login first");
				return "redirect:../../";
			}
		}
		
		// set Owner
		post.setOwner(udSession);

		String topicId = request.getParameter("topicId");
		System.out.println("topicId="+topicId);
		//sub.setSubjectTeacher(tdSession);
		Topic topic = topicService.getTopicbyid(topicId);


		if(topic==null)
			System.out.println("topic not exists");			/* HANDLE in frontend redirect */
		post.setTopic(topic);

		if(!subjectService.isSubAllowUser(udSession.getEmail(), post.getTopic().getBelongsToSubject().getSubjectCode()))
		{
			System.out.println("Need to login first");
			model.addAttribute("check", "No access");
			return "redirect:../../";
		}
		
		/*
		topic.setBelongsToSubject(sub);	// MISTAKE TO BE FIXED BY GAURAV/GURUKIRAN
		topic.setOwner(tdSession);
		topicService.saveTopic(topic);
		*/
		
		postService.savePost(post);
		System.out.println("Added post in DB");

		ArrayList<Post> posts_of_topic = topic.getTopicPosts();
		posts_of_topic.add(post);
		topic.setTopicPosts(posts_of_topic);

		topicService.saveTopic(topic);
		System.out.println("Modified Topic in DB");

		model.addAttribute("topic", topic);
		model.addAttribute("posts", topic.getTopicPosts());

		return "topic/home";

		//subjectService.savesubject(sub);

	}

	
	@GetMapping("post/{postid}")
	public String upvote(@PathVariable("postid") String postid, Model model, HttpSession session){
		//  CHECK SESSION
		User udSession = (User)session.getAttribute("teacher");
		
		if(udSession==null)
		{
			udSession = (User)session.getAttribute("student");
			if(udSession==null)
			{
				System.out.println("Need to login first");
				return "redirect:../../";
			}
		}

		Post post = postService.getPostById(postid);
		if(post==null)
		{
			System.out.println("No such post");
				// HANDLE REDIRECT
		}

		Topic topic = post.getTopic();

		if(!subjectService.isSubAllowUser(udSession.getEmail(), topic.getBelongsToSubject().getSubjectCode()))
		{
			System.out.println("NO access");
			model.addAttribute("check", "No access");
			return "redirect:../";
		}

		post.upv();			// update post
		post.updateVotes();
		postService.savePost(post);


		// MODEL ADD TOPIC AND POSTS OF TOPIC
				// redirect to topic home
		return "redirect:../../";
	}
	
}
