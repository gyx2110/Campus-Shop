package com.cumt.entity;

import java.util.Date;

/***
 * 店铺种类实体类
 * 
 * @author draymonder
 *
 */
public class ShopCategory {
	private Long shopCategoryId;
	// 商铺类别名
	private String shopCategoryName;
	// 类别描述
	private String shopCategoryDesc;
	// 类别图片
	private String shopCategoryImg;
	// 优先级 用于展示
	private Integer priority;
	private Date createTime;

	public ShopCategory() {}
	/***
	 * 
	 * @param shopCategoryId
	 * @param shopCategoryName
	 * @param shopCategoryDesc
	 * @param shopCategoryImg
	 * @param priority
	 * @param createTime
	 * @param lastEditTime
	 * @param parent
	 */
	public ShopCategory(Long shopCategoryId, String shopCategoryName, String shopCategoryDesc, String shopCategoryImg,
			Integer priority, Date createTime, Date lastEditTime, ShopCategory parent) {
		super();
		this.shopCategoryId = shopCategoryId;
		this.shopCategoryName = shopCategoryName;
		this.shopCategoryDesc = shopCategoryDesc;
		this.shopCategoryImg = shopCategoryImg;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.parent = parent;
	}

	public Long getShopCategoryId() {
		return shopCategoryId;
	}

	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}

	public String getShopCategoryName() {
		return shopCategoryName;
	}

	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}

	public String getShopCategoryDesc() {
		return shopCategoryDesc;
	}

	public void setShopCategoryDesc(String shopCategoryDesc) {
		this.shopCategoryDesc = shopCategoryDesc;
	}

	public String getShopCategoryImg() {
		return shopCategoryImg;
	}

	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
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

	public ShopCategory getParent() {
		return parent;
	}

	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}

	private Date lastEditTime;
	// 上级类， 如果为空，代表为根
	private ShopCategory parent;
}
