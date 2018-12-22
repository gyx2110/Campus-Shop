package com.cumt.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 前端页面控制器
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping("/front")
public class FrontController {
	/***
	 * 首页路由
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "front/mainpage";
	}

	/***
	 * 商铺列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	public String shopList() {
		return "front/shoplist";
	}
	
	/***
	 * 商铺详情
	 * @return
	 */
	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	public String shopDetail() {
		return "front/shopdetail";
	}

	/***
	 * 商品详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	public String productList() {
		return "/front/productdetail";
	}
}
