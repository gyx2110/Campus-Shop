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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.cumt.dto.ProductExecution;
import com.cumt.entity.Product;
import com.cumt.entity.ProductCategory;
import com.cumt.entity.Shop;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ProductStateEnum;
import com.cumt.exceptions.ProductOperationException;
import com.cumt.service.ProductCategoryService;
import com.cumt.service.ProductService;
import com.cumt.util.CodeUtil;
import com.cumt.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * 商品操作控制器
 * 
 * @author draymonder
 *
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;
	// 支持上传商品详情图的最大数量
	private static final int IMAGE_MAX_COUNT = 6;

	@RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProduct(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		// Step1:检验验证码，在点击商品下架时不需要输入验证码，编辑时需要输入验证码
		boolean statusChange = HttpServletRequestUtil.getBoolean(req, "statusChange");
		// 状态改变且验证码错误时，返回
		if (!statusChange && !CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
		}
		// 获取商品信息
		String productStr = null;
		Product product = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			productStr = HttpServletRequestUtil.getString(req, "productStr");
			// System.out.println(productStr);
			product = mapper.readValue(productStr, Product.class);
			System.out.println(product.getProductName());
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 缩略图
		MultipartFile productImg = null;
		// 详情图
		List<MultipartFile> productImgList = new ArrayList<>();
		try {
			MultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
			if (resolver.isMultipart(req)) {
				productImg = handleImage(req, productImgList);
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// System.out.println("yessssss");
		// 调用service层店铺修改
		if (product != null) {
			// System.out.println(2333);
			try {
				Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
				// 当前商品设置商店
				product.setShop(currentShop);

				ProductExecution pe = productService.updateProduct(product, productImg, productImgList);
				if (pe.getState() == OperationStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		}
		return modelMap;
	}

	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductById(@RequestParam Long productId) {
		Map<String, Object> modelMap = new HashMap<>();
		if (productId != null && productId > 0) {
			Product product = productService.getProductById(productId);
			List<ProductCategory> productCategoryList = productCategoryService
					.getProductCategoryList(product.getShop().getShopId());
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductStateEnum.EMPTY.getStateInfo());
		}
		return modelMap;
	}

	/***
	 * 添加商品
	 * 
	 * 1. 验证码校验
	 * 
	 * 2. 接收前端参数：包括 商品、 商品缩略图、商品详情图片实体类
	 * 
	 * 前端页面通过post方式传递一个包含文件上传的Form会以multipart/form-data请求发送给服务器，
	 * 需要告诉DispatcherServlet如何处理MultipartRequest，我们在spring-web.xml中定义了multipartResolver。
	 * 
	 * 如果某个Request是一个MultipartRequest，它就会首先被MultipartResolver处理， 然后再转发相应的Controller。
	 * 在Controller中，
	 * 将HttpServletRequest转型为MultipartHttpServletRequest，可以非常方便的得到文件名和文件内容
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		// 校验验证码
		if (!CodeUtil.checkVerifyCode(req)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}

		// 接收 商品信息
		String productStr = null;
		Product product = new Product();
		ObjectMapper mapper = new ObjectMapper();
		try {
			productStr = HttpServletRequestUtil.getString(req, "productStr");
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 接收商品缩略图, 文件解析
		MultipartFile productImg = null;
		List<MultipartFile> productImgList = new ArrayList<>();
		try {
			// 创建一个通用的多部分解析器
			MultipartResolver resolver = new CommonsMultipartResolver(req.getSession().getServletContext());
			// 判断req是否有文件上传，即多部分请求
			if (resolver.isMultipart(req)) {
				productImg = handleImage(req, productImgList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", OperationStatusEnum.PIC_EMPTY.getStateInfo());
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 调用Service层
		if (product != null && productImg != null && productImgList != null && productImgList.size() > 0) {
			try {
				// Session中获取Shop信息
				Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
				product.setShop(currentShop);

				// 调用addProduct
				ProductExecution productExecution = productService.addProduct(product, productImg, productImgList);
				if (productExecution.getState() == OperationStatusEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", productExecution.getStateInfo());
				}
			} catch (ProductOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductStateEnum.EMPTY.getStateInfo());
		}
		return modelMap;
	}

	/***
	 * 处理图片方法
	 * 
	 * @param req
	 * @param productImgList
	 * @return
	 */
	private MultipartFile handleImage(HttpServletRequest req, List<MultipartFile> productImgList) {
		MultipartHttpServletRequest multipartReq = null;
		MultipartFile productImg = null;
		// 得到商品缩略图
		multipartReq = (MultipartHttpServletRequest) req;
		productImg = (MultipartFile) multipartReq.getFile("productImg");

		// 得到商品详情图
		for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
			MultipartFile productImgFile = (MultipartFile) multipartReq.getFile("productImg" + i);
			if (productImgFile != null) {
				productImgList.add(productImgFile);
			} else
				break;
		}
		return productImg;
	}

	@RequestMapping(value = "/listproductbyshop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductListByShop(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取页码和每页数量
		int pageIndex = HttpServletRequestUtil.getInt(req, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(req, "pageSize");

		// 获取Session中的shop信息
		Shop currentShop = (Shop) req.getSession().getAttribute("currentShop");
		// 空值判断
		if (pageIndex > -1 && pageSize > -1 && currentShop != null && currentShop.getShopId() != null) {
			long productCategoryId = HttpServletRequestUtil.getLong(req, "productCategoryId");
			String productName = HttpServletRequestUtil.getString(req, "productName");
			Product productCondition = compactProductCondition(currentShop.getShopId(), productCategoryId, productName);
			ProductExecution productExecution = productService.getProductList(productCondition, pageIndex, pageSize);
			modelMap.put("productList", productExecution.getProductList());
			modelMap.put("count", productExecution.getCount());
			modelMap.put("shopId", currentShop.getShopId());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductStateEnum.EMPTY.getStateInfo());
		}
		return modelMap;
	}

	/**
	 * 商品查询条件
	 * 
	 * @param shopId
	 * @param productCategoryId
	 * @param productName
	 * @return
	 */
	private Product compactProductCondition(Long shopId, long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		// 查询类别
		if (productCategoryId != -1) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		// 查询商品名
		if (productName != null) {
			productCondition.setProductName(productName);
		}
		return productCondition;
	}
}
