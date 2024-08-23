package com.in2it.jwtauthenticationandauthorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("in2it/v1/supper-admin")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {
	
	
	@GetMapping
	public String superAdminApi() {
		return "Secured Endpoint this end point only can call by super admin";
	}
	

}
