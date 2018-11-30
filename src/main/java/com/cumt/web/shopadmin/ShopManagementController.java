package com.cumt.web.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cumt.service.ShopService;


@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	
	// @Autowired
	// private ShopCategoryService shopCategoryService;
	
	/**
	 * 注册店铺
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/registershop", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 1、接受并转化相应参数，包括店铺信息及图片信息
		
		return null;
	}
}
