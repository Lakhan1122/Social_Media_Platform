package com.lakhan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lakhan.model.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findTopNLikedPosts(int limit);

}
