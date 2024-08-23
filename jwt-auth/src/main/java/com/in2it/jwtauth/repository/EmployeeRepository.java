package com.in2it.jwtauth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in2it.jwtauth.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	Optional<Employee> findByUsername(String username);
	Optional<Employee> findByEmail(String email);
}
