package com.tcwgq.dao;

import java.util.List;
import com.tcwgq.domain.User;

public interface UserDao {
	public void save(User user);

	public void update(User user);

	public void delete(Integer id);

	public User getUser(Integer id);

	public List<User> getAllUser();
}
