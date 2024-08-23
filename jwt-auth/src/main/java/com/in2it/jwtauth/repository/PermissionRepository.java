package com.in2it.jwtauth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.jwtauth.entity.Permission;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>{
	
	Optional<Permission> findByPermission(String permission);

}
