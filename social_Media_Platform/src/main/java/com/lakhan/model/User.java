package com.lakhan.model;


import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(length = 200)
    private String bio;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    
    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
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

	public User(Long id, String name, String email, String bio, Instant createdAt, Instant updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public User() {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", bio=" + bio + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
    
    // getters and setters
    
    
}
