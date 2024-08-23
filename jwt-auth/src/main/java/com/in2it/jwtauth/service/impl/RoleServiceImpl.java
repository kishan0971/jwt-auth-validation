package com.in2it.jwtauth.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in2it.jwtauth.entity.Permission;
import com.in2it.jwtauth.entity.Role;
import com.in2it.jwtauth.repository.PermissionRepository;
import com.in2it.jwtauth.repository.RoleRepository;
import com.in2it.jwtauth.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Role createRole(String roleName, Set<String> permissions) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Role createRole(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<Role> getRoleById(Long id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<Role> getRoleByName(String name) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public List<Role> getAllRoles() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteRole(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addPermissionToRole(String roleId, String permissionId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void removePermissionFromRole(String roleId, String permissionId) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public Role createRole(String name) {
		Role newRole = new Role();
		newRole.setName(name);
		return roleRepository.save(newRole);
	}

	@Override
	public Optional<Role> getRoleById(String id) {
		return roleRepository.findById(UUID.fromString(id));
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void deleteRole(String id) {
		roleRepository.deleteById(UUID.fromString(id));
	}

	@Override
	public void addPermissionToRole(String roleId, String permissionId) {
		Optional<Role> roleOptional = roleRepository.findById(UUID.fromString(roleId));
		Optional<Permission> permissionOptional = permissionRepository.findById(UUID.fromString(permissionId));

		if (roleOptional.isPresent() && permissionOptional.isPresent()) {
			Role role = roleOptional.get();
			Permission permission = permissionOptional.get();
			role.getPermissions().add(permission);
			roleRepository.save(role);
		}
	}

	@Override
	public void removePermissionFromRole(String roleId, String permissionId) {
		Optional<Role> roleOptional = roleRepository.findById(UUID.fromString(roleId));
		Optional<Permission> permissionOptional = permissionRepository.findById(UUID.fromString(permissionId));

		if (roleOptional.isPresent() && permissionOptional.isPresent()) {
			Role role = roleOptional.get();
			Permission permission = permissionOptional.get();
			role.getPermissions().remove(permission);
			roleRepository.save(role);
		}
	}

}
