package com.example.EduForums.post;

import java.util.ArrayList;
import java.util.Optional;

import com.example.EduForums.subject.SubjectRepository;
import com.example.EduForums.user.User;

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
    

    public void UpvotePost(Post post, User us) {
        //  assume permission granted
        ArrayList<User> upv = post.getUpVoteList();

        for(int i=0; i<upv.size();i++){
            if(upv.get(i).getEmail().equals(us.getEmail())){
                System.out.println("ALREADY UPVOTED BY USER "+us.getEmail());
                return;
            }
        }
        

        ArrayList<User> downv = post.getDownVoteList();
        for(int i=0; i<downv.size();i++){
            if(downv.get(i).getEmail().equals(us.getEmail())){
                System.out.println("USER has downvoted need to toggle "+us.getEmail());
                downv.remove(i);
                post.upv();
                break;
            }
        }
        
        upv.add(us);

        post.setUpVoteList(upv);
        post.setDownVoteList(downv);
        post.upv();
        post.updateVotes();

        postRepository.save(post);
    }

    public void DownvotePost(Post post, User us) {
        //  assume permission granted
        ArrayList<User> downv = post.getDownVoteList();
        for(int i=0; i<downv.size();i++){
            if(downv.get(i).getEmail().equals(us.getEmail())){
                System.out.println("Already downvoted by USER"+us.getEmail());
                return;
            }
        }

        ArrayList<User> upv = post.getUpVoteList();
        for(int i=0; i<upv.size();i++){
            if(upv.get(i).getEmail().equals(us.getEmail())){
                System.out.println("USER has upvoted need to toggle "+us.getEmail());
                upv.remove(i);
                post.downv();
                break;
            }
        }
        
        downv.add(us);

        post.setUpVoteList(upv);
        post.setDownVoteList(downv);
        post.downv();
        post.updateVotes();

        postRepository.save(post);
    }

}
