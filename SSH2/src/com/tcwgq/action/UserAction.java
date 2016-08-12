package com.tcwgq.action;

import java.util.List;

import javax.annotation.Resource;

import com.tcwgq.domain.User;
import com.tcwgq.service.UserService;

public class UserAction {
	@Resource
	private UserService service;

	private String message;
	private User user;
	private List<User> users;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String list() {
		this.users = service.showUsers();
		return "list";
	}

	public String addUI() {
		return "add";
	}

	public String add() {
		this.service.saveUser(user);
		this.message = "添加成功";
		return "message";
	}

	public String edit() {
		return "edit";
	}

}
