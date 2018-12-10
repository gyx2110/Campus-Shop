package com.cumt.entity;

import java.util.Date;

/***
 * 区域实体类
 * 
 * @author draymonder
 *
 */
public class Area {
	// ID
	private Integer areaId;
	// 名称
	private String areaName;
	// 权重
	private Integer priority;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date lastEditTime;
	
	public Area() {}
	/***
	 * 
	 * @param areaId
	 * @param areaName
	 * @param priority
	 * @param createTime
	 * @param lastEditTime
	 */
	public Area(Integer areaId, String areaName, Integer priority, Date createTime, Date lastEditTime) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
}
