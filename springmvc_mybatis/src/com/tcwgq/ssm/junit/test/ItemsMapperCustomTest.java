package com.tcwgq.ssm.junit.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcwgq.ssm.mapper.ItemsMapperCustom;
import com.tcwgq.ssm.po.ItemsCustom;
import com.tcwgq.ssm.po.ItemsQueryVo;

public class ItemsMapperCustomTest {

	@Test
	public void testFindItemsList() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:spring-mybatis.xml");
		ItemsMapperCustom mapper = (ItemsMapperCustom) ctx
				.getBean("itemsMapperCustom");
		ItemsQueryVo vo = new ItemsQueryVo();
		ItemsCustom custom = new ItemsCustom();
		custom.setName("台式机");
		vo.setItemsCustom(custom);
		List<ItemsCustom> list = mapper.findItemsList(vo);
		System.out.println(list);
	}

}
