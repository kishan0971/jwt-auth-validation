package com.in2it.jwtauthenticationandauthorization.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.jwtauthenticationandauthorization.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User>  findByEmail(String email);

}
