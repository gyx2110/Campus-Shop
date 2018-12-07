package com.cumt.util;

/***
 * 路径处理类
 * @author draymonder
 *
 */
public class PathUtil {
	private static String seperator = System.getProperty("file.separator");
	
	// 获取根目录
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/image/";
		}
		else {
			basePath = "/home/image/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	// 获取存取相对路径
	public static String getShopImagePath(long shopId) {
		String imagePath="/upload/item/shop" + shopId + "/";
		return imagePath.replace("/", seperator);
	}
	
	/**
	 * 获取首页头图路径
	 */
	public static String getHeadLineImagePath() {
		String imagePath = "/upload/item/headLine/";
		return imagePath.replace("/", seperator);
	}

	/**
	 * 获取店铺类别路径
	 */
	public static String getShopCategoryImagePath() {
		String imagePath = "/upload/item/shopcategory/";
		return imagePath.replace("/", seperator);
	}
	
}
