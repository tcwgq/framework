package com.tcwgq.ssm.junit.test;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcwgq.ssm.mapper.UserMapper;
import com.tcwgq.ssm.po.User;

public class ItemsMapperTest {

	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByPrimaryKey() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:spring-mybatis.xml");
		UserMapper mapper = (UserMapper) ctx.getBean("userMapper");
		User user = mapper.selectByPrimaryKey(1);
		System.out.println(user);
	}

}
