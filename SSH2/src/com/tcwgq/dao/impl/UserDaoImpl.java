package com.tcwgq.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tcwgq.dao.UserDao;
import com.tcwgq.domain.User;

@Transactional
public class UserDaoImpl implements UserDao {
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	@Override
	public void delete(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class,
				id);
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User getUser(Integer id) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class,
				id);
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<User> getAllUser() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) sessionFactory.getCurrentSession()
				.createQuery("from User").list();
		return list;
	}

}
