package com.lz.service.impl;

import com.lz.bean.User;
import com.lz.dao.UserDao;
import com.lz.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Log4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> getUsers() {
		List<User> users = userDao.findAll();
		return users;
	}
}
