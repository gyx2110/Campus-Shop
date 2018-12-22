package com.cumt.web.shopadmin;

import java.util.Date;
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
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ProductCategoryStateEnum;
import com.cumt.exceptions.ProductCategoryOperationException;
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
	 * 
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
			} catch (Exception e) {
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

	/***
	 * 添加商品类别 ，使用@RequestBody接收前端传递过来的productCategoryList
	 * 
	 * @param productCategorylist
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> list, HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		if (list != null && !list.isEmpty()) {
			// 获取当前shopId,并且需要为添加的商品类别赋值shopId
			Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
			if (currentShop != null && currentShop.getShopId() != null) {
				for (ProductCategory productCategory : list) {
					productCategory.setShopId(currentShop.getShopId());
					productCategory.setCreateTime(new Date());
				}
			}
			try {
				ProductCategoryExecution productCategoryExecution = productCategoryService
						.batchInsertProductCategory(list);
				// 添加成功
				if (productCategoryExecution.getState() == OperationStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
					modelMap.put("effectedNum", productCategoryExecution.getEffectedNum());
				} // 添加失败
				else {
					modelMap.put("success", false);
					modelMap.put("errMsg", productCategoryExecution.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductCategoryStateEnum.EMPTY_LIST.getStateInfo());
		}
		return modelMap;
	}
	
	/***
	 * 删除商品类别
	 * @param productCategoryId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		if (productCategoryId != null && productCategoryId > 0) {
			Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
			if (currentShop != null && currentShop.getShopId() != null) {
				try {
					Long shopId = currentShop.getShopId();
					ProductCategoryExecution productCategoryExecution = productCategoryService
							.deleteProductCategory(productCategoryId, shopId);
					if (productCategoryExecution.getState() == OperationStatusEnum.SUCCESS.getState()) {
						modelMap.put("success", true);
					} else {
						modelMap.put("success", false);
						modelMap.put("errMsg", productCategoryExecution.getStateInfo());
					}
				} catch (ProductCategoryOperationException e) {
					// e.printStackTrace();
					modelMap.put("success", false);
					modelMap.put("errMsg", e.toString());
					return modelMap;
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", ProductCategoryStateEnum.NULL_SHOP.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductCategoryStateEnum.NULL_PRODUCT_CATEGORY_ID.getStateInfo());
		}
		return modelMap;
	}
}