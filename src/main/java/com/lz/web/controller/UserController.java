package com.lz.web.controller;

import com.lz.bean.User;
import com.lz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/getUsers")
	public List<User> getUsers(){
		List<User> users = userService.getUsers();
		return users;
	}
}
