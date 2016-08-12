package com.tcwgq.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcwgq.ssm.po.ItemsCustom;

@Controller
public class JsonController {
	// 请求json，响应json
	@RequestMapping("/requestJson")
	public @ResponseBody
	ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom)
			throws Exception {
		return itemsCustom;
	}

	// 请求key/value，响应json
	@RequestMapping("/responseJson")
	public @ResponseBody
	ItemsCustom responseJson(ItemsCustom itemsCustom) throws Exception {
		return itemsCustom;
	}
}
