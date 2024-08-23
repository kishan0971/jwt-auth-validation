package com.in2it.jwtauthenticationandauthorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/in2it/v1/admin")
public class AdminController {
	
	@GetMapping
	public String adminController() {return "Secured Endpoint this end point only can call by Super Admin and Admin";}

}
