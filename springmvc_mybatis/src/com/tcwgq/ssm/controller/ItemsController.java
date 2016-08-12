package com.tcwgq.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tcwgq.ssm.po.ItemsCustom;
import com.tcwgq.ssm.po.ItemsQueryVo;
import com.tcwgq.ssm.service.ItemsService;
import com.tcwgq.ssm.validation.ValidationGroup2;

@Controller
// 为了对url进行分类管理,这里定义url的根路径，窄化请求映射，访问路径为根路径+子路径
@RequestMapping("/items")
public class ItemsController {
	@Resource
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo vo)
			throws Exception {
		// 测试转发后,request域中的数据可以共享
		// System.out.println(request.getParameter("id"));
		ModelAndView modelAndView = new ModelAndView();
		List<ItemsCustom> itemsList = itemsService.findItemsList(vo);
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

	// 查找商品
	// 限制请求方法
	@RequestMapping(value = "/editItem", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String editItem(Model model,
			@RequestParam(value = "id", required = true) Integer items_id)
			throws Exception {
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		// if (itemsCustom == null) {
		// throw new CustomException("没有找到指定记录");
		// }
		model.addAttribute("item", itemsCustom);
		return "items/editItem";
	}

	// 修改后提交
	@RequestMapping("/editItemSubmit")
	// post请求参数的绑定,要求页面参数与pojo属性一致
	public String editItemSubmit(
			Model model,
			HttpServletRequest request,
			Integer id,
			@ModelAttribute("item") @Validated({ ValidationGroup2.class }) ItemsCustom itemsCustom,
			BindingResult bindResult, MultipartFile items_pic) throws Exception {
		if (bindResult.hasErrors()) {
			List<ObjectError> errors = bindResult.getAllErrors();
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			model.addAttribute("errors", errors);
			// springmvc默认会回显数据，会自动将pojo的数据存到request域，key为pojo的首字母小写
			// @ModelAttribute("item")用于指定数据回显时，springmvc默认生成pojo类的key名称
			// 手动回显
			// model.addAttribute("item", itemsCustom);
			return "items/editItem";
		}
		String originalFilename = items_pic.getOriginalFilename();
		if (items_pic != null && originalFilename != null
				&& originalFilename.length() > 0) {
			String pic_path = "D:/upload/temp/";
			String newFileName = UUID.randomUUID().toString().toLowerCase()
					.replace("-", "")
					+ originalFilename.substring(originalFilename
							.lastIndexOf("."));
			File file = new File(pic_path + newFileName);
			// 保存到磁盘
			items_pic.transferTo(file);
			itemsCustom.setPic(newFileName);
		}
		itemsService.editItems(id, itemsCustom);
		// return "redirect:queryItems.action";
		// return "forward:queryItems.action";
		return "success";
	}

	// 批量删除，参数绑定数组
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] itemsId) throws Exception {
		for (Integer id : itemsId) {
			// 有外键约束，不能删除
			itemsService.deleteItemsById(id);
		}
		return "success";
	}

	// 批量修改商品信息
	@RequestMapping("editItems")
	public String editItems(Model model, Integer[] ids) throws Exception {
		List<ItemsCustom> list = new ArrayList<ItemsCustom>();
		for (Integer id : ids) {
			ItemsCustom itemsCustom = itemsService.findItemsById(id);
			list.add(itemsCustom);
		}
		model.addAttribute("itemsList", list);
		return "items/editItems";
	}

	// 修改后提交
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(ItemsQueryVo vo) throws Exception {
		// 批量修改的方法...
		return "success";
	}

	// 商品分类
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {
		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}

	// 修改后提交
	@RequestMapping("/itemsRestful/{id}")
	public @ResponseBody
	ItemsCustom itemsRestful(@PathVariable("id") Integer id) throws Exception {
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
}
