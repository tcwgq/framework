package com.tcwgq.ssm.mapper;

import java.util.List;

import com.tcwgq.ssm.po.ItemsCustom;
import com.tcwgq.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
	List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo);
}