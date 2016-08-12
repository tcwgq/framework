package com.tcwgq.ssm.service;

import java.util.List;

import com.tcwgq.ssm.po.ItemsCustom;
import com.tcwgq.ssm.po.ItemsQueryVo;

public interface ItemsService {
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

	public ItemsCustom findItemsById(Integer id) throws Exception;

	public void editItems(Integer id, ItemsCustom itemsCustom) throws Exception;

	public void deleteItemsById(Integer id) throws Exception;
}
