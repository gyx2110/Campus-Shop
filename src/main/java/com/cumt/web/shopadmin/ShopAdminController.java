package com.cumt.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/***
 * 店铺页面管理
 * @author draymonder
 *
 */
@Controller
@RequestMapping(value="shopadmin", method= {RequestMethod.GET})
public class ShopAdminController {
	
	/***
	 * 店铺注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/shopoperation")
	public String shopOperation(){
		return "shop/shopoperation";
	}
	
	/**
	 * 店铺列表页面
	 * @return
	 */
	@RequestMapping(value="/shoplist", method=RequestMethod.GET)
	public String shopList() {
		return "shop/shoplist";
	}
	
	/***
	 * 店铺管理页面
	 * @return
	 */
	@RequestMapping(value="/shopmanagement", method=RequestMethod.GET)
	public String shopManagement() {
		return "shop/shopmanagement";
	}
}
