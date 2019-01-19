package com.cumt.entity;

import java.util.Date;
import java.util.List;

/***
 * 产品实体类
 * 
 * @author draymonder
 *
 */
public class Product {
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", imgAddr=" + imgAddr + ", normalPrice=" + normalPrice + ", promotionPrice=" + promotionPrice
				+ ", priority=" + priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime
				+ ", enableStatus=" + enableStatus + ", productImgList=" + productImgList + ", productCategory="
				+ productCategory + ", shop=" + shop + ", point=" + point + "]";
	}

	private Long productId;
	private String productName;
	private String productDesc;
	// 缩略图addr
	private String imgAddr;
	private String normalPrice;
	// 促销价
	private String promotionPrice;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;
	// 0.下架 1.在前端展示系统展示
	private Integer enableStatus;
	// 商品详情图片
	private List<ProductImg> productImgList;
	private ProductCategory productCategory;
	private Shop shop;
	// 购买商品所积累的积分
	private Integer point;
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Product() {}
	
	/***
	 * 
	 * @param productId
	 * @param productName
	 * @param productDesc
	 * @param imgAddr
	 * @param normalPrice
	 * @param promotionPrice
	 * @param priority
	 * @param createTime
	 * @param lastEditTime
	 * @param enableStatus
	 * @param productImgList
	 * @param productCategory
	 * @param shop
	 */
	public Product(Long productId, String productName, String productDesc, String imgAddr, String normalPrice,
			String promotionPrice, Integer priority, Date createTime, Date lastEditTime, Integer enableStatus,
			List<ProductImg> productImgList, ProductCategory productCategory, Shop shop) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.imgAddr = imgAddr;
		this.normalPrice = normalPrice;
		this.promotionPrice = promotionPrice;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.enableStatus = enableStatus;
		this.productImgList = productImgList;
		this.productCategory = productCategory;
		this.shop = shop;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public List<ProductImg> getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(List<ProductImg> productImgList) {
		this.productImgList = productImgList;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
