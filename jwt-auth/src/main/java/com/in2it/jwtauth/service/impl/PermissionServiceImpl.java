package com.in2it.jwtauth.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in2it.jwtauth.entity.Permission;
import com.in2it.jwtauth.repository.PermissionRepository;
import com.in2it.jwtauth.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService{

//	@Override
//	public Permission createPermission(String permission) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<Permission> getPermissionById(String id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<Permission> getPermissionByName(String permission) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public List<Permission> getAllPermissions() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deletePermission(String id) {
//		// TODO Auto-generated method stub
//		
//	}
	
	@Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission createPermission(String permission) {
        Permission newPermission = new Permission();
        newPermission.setPermission(permission);
        return permissionRepository.save(newPermission);
    }

    @Override
    public Optional<Permission> getPermissionById(String id) {
        return permissionRepository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<Permission> getPermissionByName(String permission) {
        return permissionRepository.findByPermission(permission);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public void deletePermission(String id) {
        permissionRepository.deleteById(UUID.fromString(id));
    }

}
