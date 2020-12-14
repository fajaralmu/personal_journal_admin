package com.fajar.livestreaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fajar.livestreaming.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findTop1ByUsername(String username);
}
