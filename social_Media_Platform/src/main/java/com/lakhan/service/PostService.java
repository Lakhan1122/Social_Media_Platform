package com.lakhan.service;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakhan.exception.PostNotFoundException;
import com.lakhan.model.Post;
import com.lakhan.repo.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());
        post.setLikes(0);
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id).map(post -> {
            post.setContent(updatedPost.getContent());
            post.setUpdatedAt(Instant.now());
            return postRepository.save(post);
        }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void likePost(Long id) {
        postRepository.findById(id).map(post -> {
            post.setLikes(post.getLikes() + 1);
            return postRepository.save(post);
        }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void unlikePost(Long id) {
        postRepository.findById(id).map(post -> {
            if (post.getLikes() > 0) {
                post.setLikes(post.getLikes() - 1);
            }
            return postRepository.save(post);
        }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public List<Post> getTopLikedPosts(int limit) {
        return postRepository.findTopNLikedPosts(limit);
    }
}

