package com.cumt.web.front;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.cumt.dto.ProductExecution;
import com.cumt.entity.Product;
import com.cumt.entity.ProductCategory;
import com.cumt.entity.Shop;
import com.cumt.enums.EnableStatusEnum;
import com.cumt.enums.ShopStateEnum;
import com.cumt.service.ProductCategoryService;
import com.cumt.service.ProductService;
import com.cumt.service.ShopService;
import com.cumt.util.HttpServletRequestUtil;

/***
 * 店铺详情页控制器
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping("/front")
public class ShopDetailController {
	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	/***
	 * 获取默认店铺下的店铺详情及商品列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopDetailPageInfo(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		Shop shop = null;
		List<ProductCategory> productCategoryList = new ArrayList<>();
		if (shopId != -1) {
			shop = shopService.getShopById(shopId);
			productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOPID.getStateInfo());
		}
		return modelMap;
	}

	/***
	 * 根据查询条件获取该店铺下的所有商品
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/listproducts", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProducts(HttpServletRequest req) throws UnsupportedEncodingException {
		Map<String, Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(req, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(req, "pageSize");
		long shopId = HttpServletRequestUtil.getLong(req, "shopId");
		if (pageIndex > -1 && pageSize > -1 && shopId > -1) {
			long productCategoryId = HttpServletRequestUtil.getLong(req, "productCategoryId");
			String productName = HttpServletRequestUtil.getString(req, "productName");
			Product productCondition = compactProductCondition3Search(shopId, productCategoryId, productName);
			ProductExecution productExecution = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", productExecution.getProductList());
			modelMap.put("count", productExecution.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
		}
		
		return modelMap;
	}

	/***
	 * 构建商品查询条件
	 * 
	 * @param shopId
	 * @param productCategoryId
	 * @param productName
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private Product compactProductCondition3Search(long shopId, long productCategoryId, String productName) throws UnsupportedEncodingException {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		if (productCategoryId > -1) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if (productName != null) {
			String trueProductName = URLDecoder.decode(productName, "UTF-8");
			productCondition.setProductName(trueProductName);
		}
		productCondition.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
		return productCondition;
	}
}
