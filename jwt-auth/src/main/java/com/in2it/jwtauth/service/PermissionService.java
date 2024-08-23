package com.in2it.jwtauth.service;

import java.util.List;
import java.util.Optional;

import com.in2it.jwtauth.entity.Permission;


public interface PermissionService {
	
    Permission createPermission(String permission);
    Optional<Permission> getPermissionById(String id);
    Optional<Permission> getPermissionByName(String permission);
    List<Permission> getAllPermissions();
    void deletePermission(String id);

}
