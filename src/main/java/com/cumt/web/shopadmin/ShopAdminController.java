package com.cumt.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 店铺页面管理
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {

	/***
	 * 店铺注册页面/店铺修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}

	/**
	 * 店铺列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	public String shopList() {
		return "shop/shoplist";
	}

	/**
	 * 店铺账号无效页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/usernull", method = RequestMethod.GET)
	public String userNull() {
		return "shop/usernull";
	}
	
	/***
	 * 店铺管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shopmanagement", method = RequestMethod.GET)
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	/**
	 * 商品类别管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
	public String productCategoryManagement() {
		return "shop/productcategorymanagement";
	}
	
	/**
	 * 商品注册页面/商品操作页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productoperation", method = RequestMethod.GET)
	public String productOperation() {
		return "shop/productoperation";
	}

	/**
	 * 商品管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productmanagement", method = RequestMethod.GET)
	public String productManagement() {
		return "shop/productmanagement";
	}
}
