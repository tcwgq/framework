package com.tcwgq.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tcwgq.dao.UserDao;
import com.tcwgq.domain.User;

@Transactional
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public void update(User user) {
		em.merge(user);
	}

	@Override
	public void delete(Integer id) {
		em.remove(em.getReference(User.class, id));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User getUser(Integer id) {
		return em.find(User.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<User> getAllUser() {
		return em.createQuery("select user from User user").getResultList();
	}
}
