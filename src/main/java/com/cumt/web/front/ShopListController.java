package com.cumt.web.front;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cumt.dto.ShopExecution;
import com.cumt.entity.Area;
import com.cumt.entity.Shop;
import com.cumt.entity.ShopCategory;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.service.AreaService;
import com.cumt.service.ShopCategoryService;
/***
 * 商品查询控制器
 * @author draymonder
 *
 */
import com.cumt.service.ShopService;
import com.cumt.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/front")
public class ShopListController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	/***
	 * 返回商品列表里的一级或二级ShopCategory，以及区域信息列表
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "listshopspageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopsPageInfo(HttpServletRequest req) {
		Map<String, Object> modelMap = new HashMap<>();
		// 前端获取parentId
		long parentId = HttpServletRequestUtil.getLong(req, "parentId");
		List<ShopCategory> shopCategoryList = null;
		// parentId存在
		if (parentId != -1) {
			ShopCategory shopCategoryCondition = new ShopCategory();
			ShopCategory parentShopCategory = new ShopCategory();
			parentShopCategory.setShopCategoryId(parentId);
			shopCategoryCondition.setParent(parentShopCategory);
			try {
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition, 0, 10);
				// System.out.println("size = " + shopCategoryList.size());
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else { // parentId 不存在, 及全部商店列表
			try {
				shopCategoryList = shopCategoryService.getShopCategoryList(null, 0, 10);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		List<Area> areaList = null;
		try {
			areaList = areaService.getAreaList();
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		modelMap.put("success", true);
		modelMap.put("areaList", areaList);
		return modelMap;
	}

	/***
	 * 获取指定查询条件下的店铺列表
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "listshops", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShops(HttpServletRequest req) throws UnsupportedEncodingException {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取页码
		int pageIndex = HttpServletRequestUtil.getInt(req, "pageIndex");
		// 获取每页数量
		int pageSize = HttpServletRequestUtil.getInt(req, "pageSize");
		if (pageIndex != -1 && pageSize != -1) {
			// 获取一级类别Id
			long parentId = HttpServletRequestUtil.getLong(req, "parentId");
			// 获取二级类别Id
			long shopCategoryId = HttpServletRequestUtil.getLong(req, "shopCategoryId");
			// 获取模糊查询的店铺名称
			String shopName = HttpServletRequestUtil.getString(req, "shopName");
			// 获取区域id
			int areaId = HttpServletRequestUtil.getInt(req, "areaId");
			// 获取组合后的查询条件
			Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
			// 查询
			ShopExecution shopExecution = shopService.getShopList(shopCondition, pageIndex, pageSize);
			modelMap.put("success", true);
			modelMap.put("shopList", shopExecution.getShopList());
			modelMap.put("count", shopExecution.getCount());
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.PAGIN_EMPTY.getStateInfo());
		}
		return modelMap;

	}

	/***
	 * 根据查询条件组合
	 * 
	 * @param parentId
	 *            一级类别Id
	 * @param shopCategoryId
	 *            二级类别Id
	 * @param areaId
	 *            区域id
	 * @param shopName
	 *            模糊查询的店铺名称
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) throws UnsupportedEncodingException {
		Shop shopCondition = new Shop();
		// 查询某个一级shopCategory下的所有二级shopCateg的店铺列表
		if (parentId != -1) {
			ShopCategory childShopCategory = new ShopCategory();
			ShopCategory parentShopCategory = new ShopCategory();
			parentShopCategory.setShopCategoryId(parentId);
			childShopCategory.setParent(parentShopCategory);
			shopCondition.setShopCategory(childShopCategory);
		}
		// 查询某个二级shopCategory下的店铺列表
		if (shopCategoryId != -1) {
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		// 查询某个区域下的店铺列表
		if (areaId != -1) {
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}
		// 模糊查询店铺名称的列表
		if (shopName != null) {
			String trueShopName = new String(shopName.getBytes("ISO-8859-1"), "utf-8");
			// System.out.println("\n" + trueShopName + "\n");
			shopCondition.setShopName(trueShopName);
		}
		return shopCondition;
	}
}
