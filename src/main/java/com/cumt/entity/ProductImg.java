package com.cumt.entity;

import java.util.Date;

/***
 * 商品图片实体类
 * 
 * @author draymonder
 *
 */
public class ProductImg {
	private Long productImgId;
	private String imgAddr;
	private String imgDesc;
	private Integer priority;
	private Date createTime;
	private Long productId;

	public ProductImg() {
	}

	/***
	 * 
	 * @param productImgId
	 * @param imgAddr
	 * @param imgDesc
	 * @param priority
	 * @param createTime
	 * @param productId
	 */
	public ProductImg(Long productImgId, String imgAddr, String imgDesc, Integer priority, Date createTime,
			Long productId) {
		super();
		this.productImgId = productImgId;
		this.imgAddr = imgAddr;
		this.imgDesc = imgDesc;
		this.priority = priority;
		this.createTime = createTime;
		this.productId = productId;
	}

	public Long getProductImgId() {
		return productImgId;
	}

	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductImg [productImgId=" + productImgId + ", imgAddr=" + imgAddr + ", imgDesc=" + imgDesc
				+ ", priority=" + priority + ", createTime=" + createTime + ", productId=" + productId
				+ ", getProductImgId()=" + getProductImgId() + ", getImgAddr()=" + getImgAddr() + ", getImgDesc()="
				+ getImgDesc() + ", getPriority()=" + getPriority() + ", getCreateTime()=" + getCreateTime()
				+ ", getProductId()=" + getProductId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
