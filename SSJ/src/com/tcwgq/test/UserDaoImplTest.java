package com.tcwgq.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcwgq.dao.UserDao;
import com.tcwgq.domain.User;

public class UserDaoImplTest {
	private static UserDao userDao;
	static {
		userDao = (UserDao) new ClassPathXmlApplicationContext(
				"ApplicationContext.xml").getBean("userDao");
	}

	@Test
	public void testSave() {
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setName("name_" + i);
			userDao.save(user);
		}
	}

	@Test
	public void testUpdate() {
		User user = userDao.getUser(1);
		user.setName("liSi");
		userDao.update(user);
	}

	@Test
	public void testDelete() {
		userDao.delete(1);
	}

	@Test
	public void testGetUser() {
		User user = userDao.getUser(2);
		System.out.println(user);
	}

	@Test
	public void testGetAllUser() {
		List<User> list = userDao.getAllUser();
		for (User user : list) {
			System.out.println(user);
		}
	}

}
