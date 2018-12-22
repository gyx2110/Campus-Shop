package com.cumt.web.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cumt.entity.HeadLine;
import com.cumt.entity.ShopCategory;
import com.cumt.enums.EnableStatusEnum;
import com.cumt.service.HeadLineService;
import com.cumt.service.ShopCategoryService;

/***
 * 初始化前端展示系统的主页信息，包括获取一级店铺类别列表一级头条列表
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping("/front")
public class MainPageController {
	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private HeadLineService headLineService;

	/***
	 * 初始化前端展示系统的主页信息，包括获取一级店铺类别列表一级头条列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listMainPageInfo() {
		Map<String, Object> modelMap = new HashMap<>();
		// 一级店铺列表
		List<ShopCategory> shopCategryList = new ArrayList<>();
		try {
			shopCategryList = shopCategoryService.getShopCategoryList(null, 0, 99);
			modelMap.put("shopCategoryList", shopCategryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 头条列表
		List<HeadLine> headLineList = new ArrayList<>();
		try {
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
			headLineList = headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
