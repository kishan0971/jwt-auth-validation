package com.in2it.jwtauth.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.in2it.jwtauth.entity.Employee;

public interface EmployeeService {

//	Employee createUser(String username, String password, String email);
//    Optional<User> getUserById(Long id);
//    Optional<User> getUserByUsername(String username);
//    Optional<User> getUserByEmail(String email);
//    List<User> getAllUsers();
//    void deleteUser(Long id);
//    void addRoleToUser(Long userId, Long roleId);
//    void removeRoleFromUser(Long userId, Long roleId);
//    User updateUser(Long id, String username, String email, String password);

	Employee createEmployee(String firstName, String lastName, String username, String password, String email);

	Optional<Employee> getEmployeeById(String id);

	Optional<Employee> getEmployeeByUsername(String username);

	Optional<Employee> getEmployeeByEmail(String email);

	List<Employee> getAllEmployees();

	void deleteEmployee(String id);

	void addRoleToEmployee(String userId, String roleId);

	void removeRoleFromEmployee(String userId, String roleId);

	Employee updateEmployee(String id, String firstName, String lastName, String username, String email, String password);

}
