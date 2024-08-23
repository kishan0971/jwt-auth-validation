package com.in2it.jwtauth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in2it.jwtauth.entity.Role;
import com.in2it.jwtauth.service.RoleService;

@RestController
@RequestMapping("in2it/employees/roles")
public class RoleController {
	
	@Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestParam String name) {
        return roleService.createRole(name);
    }

    @GetMapping("/{id}")
    public Optional<Role> getRole(@PathVariable String id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<Role> getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public void addPermissionToRole(@PathVariable String roleId, @PathVariable String permissionId) {
        roleService.addPermissionToRole(roleId, permissionId);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public void removePermissionFromRole(@PathVariable String roleId, @PathVariable String permissionId) {
        roleService.removePermissionFromRole(roleId, permissionId);
    }

}
