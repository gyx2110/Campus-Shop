package com.cumt.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cumt.dto.ShopExecution;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.Shop;
import com.cumt.enums.ShopStateEnum;
import com.cumt.service.ShopService;
import com.cumt.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	public Map<String, Object> registerShop(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		// 1、接受并转化相应参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(req, "shopStr");
		// jackson-databind-->https://github.com/FasterXML/jackson-databind将json转换为pojo
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		MultipartHttpServletRequest multipartHttpServletRequest = null;
		MultipartFile shopImg = null;
		MultipartResolver  mutipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());
		// 判断req 是否有文件上传请求
		if(mutipartResolver.isMultipart(req)) {
			multipartHttpServletRequest = (MultipartHttpServletRequest)req;
			// 获取req中上传文件中的shopImg文件
			shopImg = (MultipartFile)multipartHttpServletRequest.getFile("shopImg");
		}
		if(shopImg == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有上传图片");
			return modelMap;
		}
		
		// 注册店铺
		if(shop != null) {
			PersonInfo owner = (PersonInfo)req.getSession().getAttribute("user");
			shop.setOwner(owner);
			ShopExecution shopExecution = shopService.addShop(shop,shopImg);
			if(shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
				// 返回 CHECK属性就说明 店铺注册成功了
				modelMap.put("success", true);
				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>) req.getSession().getAttribute("shopList");
				if(shopList == null || shopList.isEmpty()) {
					shopList = new ArrayList<>();
				}
				shopList.add(shop);
				req.getSession().setAttribute("shopList", shopList);
			}
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有得到店铺信息");
		}
		return modelMap;
	}
}
