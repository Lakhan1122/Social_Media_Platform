package com.lakhan.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false, length = 300)
    private String content;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    
    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
    
    @Column(nullable = false)
    private int likes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant instant) {
		this.createdAt = instant;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant instant) {
		this.updatedAt = instant;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Post(Long id, User user, String content, Instant createdAt, Instant updatedAt, int likes) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.likes = likes;
	}

	public Post() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user + ", content=" + content + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", likes=" + likes + "]";
	}
    
    // getters and setters
    
    
}

