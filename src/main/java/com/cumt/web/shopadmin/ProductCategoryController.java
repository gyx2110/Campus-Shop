package com.cumt.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cumt.dto.ProductCategoryExecution;
import com.cumt.dto.Result;
import com.cumt.entity.ProductCategory;
import com.cumt.entity.Shop;
import com.cumt.enums.ProductCategoryStateEnum;
import com.cumt.service.ProductCategoryService;

/***
 * 商品类别操作控制类 *** 负责后台处理
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryController {
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/***
	 * 返回商品种类列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest req) {
		List<ProductCategory> list = null;
		Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
		if (currentShop != null && currentShop.getShopId() != null) {
			try {
				long shopId = currentShop.getShopId();
				// long shopId = 1L;
				list = productCategoryService.getProductCategoryList(shopId);
				return new Result<List<ProductCategory>>(true, list);
			} catch(Exception e) {
				e.printStackTrace();
				ProductCategoryExecution productCategoryExecution = new ProductCategoryExecution(
						ProductCategoryStateEnum.NULL_PRODUCT_CATEGORY);
				return new Result<List<ProductCategory>>(false, productCategoryExecution.getState(),
						productCategoryExecution.getStateInfo());
			}
		} else {
			ProductCategoryExecution productCategoryExecution = new ProductCategoryExecution(
					ProductCategoryStateEnum.NULL_PRODUCT_CATEGORY);
			return new Result<List<ProductCategory>>(false, productCategoryExecution.getState(),
					productCategoryExecution.getStateInfo());
		}
	}
	
	@RequestMapping(value="addproductcategorys", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> list, HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		return modelMap;
	}
}
