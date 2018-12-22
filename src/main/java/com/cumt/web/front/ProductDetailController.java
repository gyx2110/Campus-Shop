package com.cumt.web.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cumt.entity.Product;
import com.cumt.enums.ProductStateEnum;
import com.cumt.service.ProductService;
import com.cumt.util.HttpServletRequestUtil;

/***
 * 商品详情控制器
 * @author draymonder
 *
 */


@Controller
@RequestMapping(value="/front")
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	
	/***
	 * 获取商品详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listproductinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductInfo(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Long productId = HttpServletRequestUtil.getLong(req, "productId");
		Product product = null;
		if(productId != -1) {
			product = productService.getProductById(productId);
			modelMap.put("product", product);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductStateEnum.PRODUCT_ID_EMPTY.getStateInfo());
		}
		return modelMap;
	}
}
