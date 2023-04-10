package com.lakhan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lakhan.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
