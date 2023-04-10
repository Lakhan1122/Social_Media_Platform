package com.lakhan.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lakhan.model.Post;
import com.lakhan.repo.PostRepository;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	

		    @Autowired
		    private PostRepository postRepository;
		    
		    @PostMapping("")
		    public Post createPost(@RequestBody Post post) {
		        return postRepository.save(post);
		    }
		    
		    @GetMapping("/{id}")
		    public Post getPostById(@PathVariable Long id) {
		        return postRepository.findById(id)
		            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
		    }

            @PutMapping("/{id}")
            public Post updatePostById(@PathVariable Long id, @RequestBody Post post) {
                Post existingPost = postRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
                existingPost.setContent(post.getContent());
                return postRepository.save(existingPost);
            }

            @DeleteMapping("/{id}")
            public void deletePostById(@PathVariable Long id) {
                postRepository.deleteById(id);
            }

            @PostMapping("/{id}/like")
            public Post likePostById(@PathVariable Long id) {
                Post post = postRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
                post.setLikes(post.getLikes() + 1);
                return postRepository.save(post);
            }

            @PostMapping("/{id}/unlike")
            public Post unlikePostById(@PathVariable Long id) {
                Post post = postRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
                if (post.getLikes() > 0) {
                    post.setLikes(post.getLikes() - 1);
                }
                return postRepository.save(post);
            }

            @GetMapping("/analytics/posts")
            public long getTotalPostCount() {
                return postRepository.count();
            }

            @GetMapping("/analytics/posts/top-liked")
            public List<Post> getTopLikedPosts() {
                return postRepository.findAll(Sort.by(Sort.Direction.DESC, "likes")).stream()
                    .limit(5)
                    .collect(Collectors.toList());
            }
}