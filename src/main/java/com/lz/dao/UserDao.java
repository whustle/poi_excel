package com.lz.dao;

import com.lz.bean.User;

import java.util.List;

public interface UserDao {
	List<User> findAll();
	void saveUser(User user);
}
