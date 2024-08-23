package com.in2it.jwtauth.config;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.in2it.jwtauth.entity.Employee;
import com.in2it.jwtauth.entity.Permission;
import com.in2it.jwtauth.entity.Role;
import com.in2it.jwtauth.repository.EmployeeRepository;
import com.in2it.jwtauth.repository.PermissionRepository;
import com.in2it.jwtauth.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

//@Configuration
//@Slf4j
//public class DataInitializer {
//
//	@Bean
//	CommandLineRunner init(EmployeeRepository employeeRepository, RoleRepository roleRepository,
//			PermissionRepository permissionRepository) {
//		return args -> {
//			// Create and save roles and permissions
//			Role superAdminRole = new Role();
//			superAdminRole.setName("SUPER_ADMIN");
//			Role adminRole = new Role();
//			adminRole.setName("ADMIN");
//			Role employeeRole = new Role();
//			employeeRole.setName("EMPLOYEE");
//			Role traineeRole = new Role();
//			traineeRole.setName("TRAINEE");
//			try {
//				
//				Optional<Role> roleSuperAdmin = roleRepository.findByName(superAdminRole.getName());
//				if(roleSuperAdmin.isEmpty()) {
//					
//					roleRepository.save(superAdminRole);
//				}else {
//					log.debug("Role SuperAdmin all ready exist");
//				}
//				Optional<Role> roleAdmin = roleRepository.findByName(adminRole.getName());
//				if(roleAdmin.isEmpty()) {
//					
//					roleRepository.save(adminRole);
//				}else {
//					log.debug("Role Admin all ready exist");
//				}
//				Optional<Role> roleEmployee = roleRepository.findByName(employeeRole.getName());
//				if(roleEmployee.isEmpty()) {
//					
//					roleRepository.save(employeeRole);
//				}else {
//					log.debug("Role Employee all ready exist");
//				}
//				Optional<Role> roleTrainee = roleRepository.findByName(traineeRole.getName());
//				if(roleTrainee.isEmpty()) {
//					
//					roleRepository.save(traineeRole);
//				}else {
//					log.debug("Role Trainee all ready exist");
//				}
//				
//				
//				roleRepository.save(traineeRole);
//				
//			} catch (Exception e) {
//				log.debug("Role all ready exist"+e.getMessage());
//			}
//
//			Permission readPermission = new Permission();
//			readPermission.setPermission("read");
//			Permission writePermission = new Permission();
//			writePermission.setPermission("write");
//			Permission updatePermission = new Permission();
//			updatePermission.setPermission("update");
//			Permission deletePermission = new Permission();
//			deletePermission.setPermission("delete");
//			try {
//				Optional<Permission> permissionRead = permissionRepository.findByPermission(readPermission.getPermission());
//				if(permissionRead.isEmpty()) {
//					permissionRepository.save(readPermission);					
//				}
//				else {
//					
//					log.debug("read permission all ready exist");
//				}
//				Optional<Permission> permissionWrite = permissionRepository.findByPermission(writePermission.getPermission());
//				if(permissionWrite.isEmpty()) {
//					
//					permissionRepository.save(writePermission);
//				}
//				else {
//					
//					log.debug("write permission all ready exist");
//				}
//				Optional<Permission> permissionUpdate = permissionRepository.findByPermission(updatePermission.getPermission());
//				if(permissionUpdate.isEmpty()) {
//					
//					permissionRepository.save(updatePermission);
//				}
//				else {
//					
//					log.debug("update permission all ready exist");
//				}
//				
//				Optional<Permission> permissionDelete = permissionRepository.findByPermission(deletePermission.getPermission());
//				if(permissionDelete.isEmpty()) {
//					
//					permissionRepository.save(deletePermission);
//				}
//				else {
//					
//					log.debug("delete permission all ready exist");
//				}
//				
//			} catch (Exception e) {
//				log.debug("Exception while createing permissions. !!");
//			}
//
//			superAdminRole.setPermissions(Set.of(readPermission, writePermission, updatePermission, deletePermission));
//			try {
//				Optional<Role> role = roleRepository.findByName(superAdminRole.getName());
//				if(role.isEmpty()) {
//					
//					roleRepository.save(superAdminRole);				
//				}
//				else {
//					
//					log.debug("Super-admin all ready exist");
//				}
//			} catch (Exception e) {
//				log.debug("Exception while createing role SUPER_ADMIN. !!");
//			}
//
//			adminRole.setPermissions(Set.of(readPermission, writePermission, updatePermission));
//			try {
//				Optional<Role> role = roleRepository.findByName(adminRole.getName());
//				if(role.isEmpty()) {
//					
//					roleRepository.save(adminRole);
//				}
//				else {
//					log.debug("Admin all ready exist");
//					
//				}
//				
//			} catch (Exception e) {
//				log.debug("Exception while createing role ADMIN. !!");
//			}
//
//			Employee superAdminEmployee = new Employee();
//			superAdminEmployee.setUsername("superadmin");
//			superAdminEmployee.setPassword(new BCryptPasswordEncoder().encode("superadmin"));
//			superAdminEmployee.setRoles(Set.of(superAdminRole));
//			try {
//				Optional<Employee> username = employeeRepository.findByUsername(superAdminEmployee.getUsername());
//				if(username.isEmpty()) {
//					
//					employeeRepository.save(superAdminEmployee);
//				}else {
//					log.debug("Employee SUPERADMIN allready exist. !!");
//				}
//				
//			} catch (Exception e) {
//				log.debug("Exception while createing SUPER_ADMIN Employee. !!");
//			}
//
//			Employee adminEmployee = new Employee();
//			adminEmployee.setUsername("admin");
//			adminEmployee.setPassword(new BCryptPasswordEncoder().encode("admin"));
//			adminEmployee.setRoles(Set.of(adminRole));
//			try {
//				Optional<Employee> username = employeeRepository.findByUsername(adminEmployee.getUsername());
//				if(username.isEmpty()) {
//					
//					employeeRepository.save(adminEmployee);
//				}else {
//					log.debug("Employee ADMIN allready exist. !!");
//				}
//				
//			} catch (Exception e) {
//				log.debug("Exception while createing ADMIN Employee. !!");
//			}
//		};
//	}
//}

@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository, RoleRepository roleRepository,
                           PermissionRepository permissionRepository) {
        return args -> {
            // Create and save roles
            Role superAdminRole = createAndSaveRole(roleRepository, "SUPER_ADMIN");
            Role adminRole = createAndSaveRole(roleRepository, "ADMIN");
            createAndSaveRole(roleRepository, "EMPLOYEE");
            createAndSaveRole(roleRepository, "TRAINEE");

            // Create and save permissions
            Permission readPermission = createAndSavePermission(permissionRepository, "read");
            Permission writePermission = createAndSavePermission(permissionRepository, "write");
            Permission updatePermission = createAndSavePermission(permissionRepository, "update");
            Permission deletePermission = createAndSavePermission(permissionRepository, "delete");

            // Assign permissions to roles
            superAdminRole.setPermissions(Set.of(readPermission, writePermission, updatePermission, deletePermission));
            roleRepository.save(superAdminRole);

            adminRole.setPermissions(Set.of(readPermission, writePermission, updatePermission));
            roleRepository.save(adminRole);

            // Create and save employees
            createAndSaveEmployee(employeeRepository, "superadmin", "superadmin", superAdminRole);
            createAndSaveEmployee(employeeRepository, "admin", "admin", adminRole);
        };
    }

    private Role createAndSaveRole(RoleRepository roleRepository, String roleName) {
        return roleRepository.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            log.debug("Role {} created", roleName);
            return role;
        });
    }

    private Permission createAndSavePermission(PermissionRepository permissionRepository, String permissionName) {
        return permissionRepository.findByPermission(permissionName).orElseGet(() -> {
            Permission permission = new Permission();
            permission.setPermission(permissionName);
            permissionRepository.save(permission);
            log.debug("Permission {} created", permissionName);
            return permission;
        });
    }

    private void createAndSaveEmployee(EmployeeRepository employeeRepository, String username, String password, Role role) {
        if (employeeRepository.findByUsername(username).isEmpty()) {
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setPassword(new BCryptPasswordEncoder().encode(password));
            employee.setRoles(Set.of(role)); // Ensure that 'role' is saved and not transient
            employeeRepository.save(employee);
            log.debug("Employee {} created with role {}", username, role.getName());
        } else {
            log.debug("Employee {} already exists", username);
        }
    }
}