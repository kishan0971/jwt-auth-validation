package com.in2it.jwtauth.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.in2it.jwtauth.entity.Role;

public interface RoleService {
	
	public Role createRole(String roleName, Set<String> permissions);
	Role createRole(String name);
    Optional<Role> getRoleById(String id);
    Optional<Role> getRoleByName(String name);
    List<Role> getAllRoles();
    void deleteRole(String id);
    void addPermissionToRole(String roleId, String permissionId);
    void removePermissionFromRole(String roleId, String permissionId);

}
