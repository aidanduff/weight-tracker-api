package com.aidanduff.weighttrackerapi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aidanduff.weighttrackerapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);
}
