package com.tcwgq.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.tcwgq.ssm.exception.CustomException;
import com.tcwgq.ssm.mapper.ItemsMapper;
import com.tcwgq.ssm.mapper.ItemsMapperCustom;
import com.tcwgq.ssm.po.Items;
import com.tcwgq.ssm.po.ItemsCustom;
import com.tcwgq.ssm.po.ItemsQueryVo;
import com.tcwgq.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	@Resource
	private ItemsMapperCustom itemsMapperCustom;
	@Resource
	private ItemsMapper itemsMapper;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
			throws Exception {
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		if (items == null) {
			throw new CustomException("没有找到指定记录");
		}
		// 向下转型可能存在不安全因素
		ItemsCustom itemsCustom = null;
		if (items != null) {
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		return itemsCustom;
	}

	@Override
	public void editItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		itemsCustom.setId(id);
		// 根据ID更新表中的所有字段，包括大文本字段，所以之前必须设置ID
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

	@Override
	public void deleteItemsById(Integer id) throws Exception {
		itemsMapper.deleteByPrimaryKey(id);
	}
}
