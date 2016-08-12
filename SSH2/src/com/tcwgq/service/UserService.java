package com.tcwgq.service;

import java.util.List;
import com.tcwgq.domain.User;

public interface UserService {

	public List<User> showUsers();

	public void saveUser(User user);
}
