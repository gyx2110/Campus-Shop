package com.cumt.interceptor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cumt.entity.Shop;
/***
 * 店家管理系统操作验证拦截器
 * @author draymonder
 *
 */
public class ShopPermissionInterceptor implements HandlerInterceptor {
	
	/***
	 * 用户操作提交后拦截，未处理请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
		if(currentShop != null && shopList != null) {
			for(Shop shop : shopList) {
				if(shop.getShopId().equals(currentShop.getShopId())) 
					return true;
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
