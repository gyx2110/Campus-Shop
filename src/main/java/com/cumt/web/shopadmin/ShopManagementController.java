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
import com.cumt.entity.Area;
import com.cumt.entity.PersonInfo;
import com.cumt.entity.Shop;
import com.cumt.entity.ShopCategory;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ShopStateEnum;
import com.cumt.service.AreaService;
import com.cumt.service.ShopCategoryService;
import com.cumt.service.ShopService;
import com.cumt.util.CodeUtil;
import com.cumt.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * 店铺操作控制类   ***负责后台处理
 * @author draymonder
 *
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	// shop服务
	@Autowired
	private ShopService shopService;
	
	// shopCategory服务
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	// area服务
	@Autowired 
	private AreaService areaService;
	
	/***
	 * 店铺信息初始化: 店铺区域和店铺类别
	 * @return
	 */
	@RequestMapping(value="/getshopinitinfo", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null , 0, 10);
			List<Area> areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
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
		if(!CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码有误");
			return modelMap;
		}
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
		/*
		if(shopImg == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有上传图片");
			return modelMap;
		}
		*/
		
		// 注册店铺 因为shopId是系统分配的。所以不需要判断shopId
		if(shop != null) {
			PersonInfo owner = (PersonInfo)req.getSession().getAttribute("user");
			// if(owner == null) {
				// owner = new PersonInfo();
				// owner.setUserId(1L);
			// }
			shop.setOwner(owner);
			ShopExecution shopExecution = shopService.addShop(shop,shopImg);
			if(shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
				// 返回 CHECK属性就说明 店铺注册成功了
				modelMap.put("success", true);
				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>) req.getSession().getAttribute("shopList");
				if(shopList == null) {
					shopList = new ArrayList<>();
				}
				shopList.add(shopExecution.getShop());
				req.getSession().setAttribute("shopList", shopList);
			}
		}
		else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "没有得到店铺信息");
		}
		return modelMap;
	}
	
	/***
	 * 根据id获取店铺信息
	 * @param req
	 * @return
	 * 返回对应ShopId信息，并且返回区域列表 供用户选择
	 */
	@RequestMapping(value="/getshopbyid", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopById(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		if(shopId > 0) {
			try {
				Shop shop = shopService.getShopById(shopId);
				modelMap.put("shop", shop);
				List<Area> areaList = areaService.getAreaList();
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			}
			catch(Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "getShopByIdError "+e.getMessage());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOP.getStateInfo());
		}
		return modelMap;
	}
	
	/***
	 * 修改店铺
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/updateshop", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateShop(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		if(!CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}
		
		// 1. 接受并转换相应参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(req, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop  shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "shopTranslateError "+e.getMessage());
			return modelMap;
		}
		
		// 2.获取图片文件流
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile shopImg = null;
		MultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());
		if(multipartResolver.isMultipart(req)) {
			multipartRequest = (MultipartHttpServletRequest) req;
			shopImg = (MultipartFile) multipartRequest.getFile("shopImg");
		}
		
		// 3.修改店铺
		if(shop != null && shop.getShopId() != null) {
			/*
			 session中存放着user的信息 
			PersonInfo owner = (PersonInfo)req.getSession().getAttribute("user");
			if(owner == null) {
				owner = new PersonInfo();
				owner.setUserId(1L);
			}
			shop.setOwner(owner);
			*/
			ShopExecution shopExecution = shopService.updateShop(shop, shopImg);
			if(shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			}
			else {
				modelMap.put("success", false);
				modelMap.put("errMsg", shopExecution.getStateInfo());
			}
			// return modelMap;
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOPID.getStateInfo());
		}
		return modelMap;
	}
	
	
}
