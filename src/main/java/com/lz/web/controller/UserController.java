package com.lz.web.controller;

import com.lz.bean.User;
import com.lz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/getUsers")
	public List<User> getUsers(){
		List<User> users = userService.getUsers();
		return users;
	}
}
