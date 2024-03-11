package com.kodnest.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.tunehub.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	boolean existsByEmail(String email);

	boolean existsByPassword(String password);

	User findByEmail(String email);

}

