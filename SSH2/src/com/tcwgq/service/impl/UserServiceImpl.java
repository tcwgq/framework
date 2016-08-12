package com.tcwgq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.tcwgq.dao.UserDao;
import com.tcwgq.domain.User;
import com.tcwgq.service.UserService;

public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public List<User> showUsers() {
		return userDao.getAllUser();
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

}
