package com.cumt.entity;

import java.util.Date;

/***
 * 个人信息实体类
 * 
 * @author draymonder
 *
 */
public class PersonInfo {
	// ID
	private Long userId;
	// 姓名
	private String name;
	// 图像地址
	private String profileImg;
	@Override
	public String toString() {
		return "PersonInfo [userId=" + userId + ", name=" + name + ", profileImg=" + profileImg + ", email=" + email
				+ ", gender=" + gender + ", enableStatus=" + enableStatus + ", userType=" + userType + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + ", getUserId()=" + getUserId() + ", getName()="
				+ getName() + ", getProfileImg()=" + getProfileImg() + ", getEmail()=" + getEmail() + ", getGender()="
				+ getGender() + ", getEnableStatus()=" + getEnableStatus() + ", getUserType()=" + getUserType()
				+ ", getCreateTime()=" + getCreateTime() + ", getLastEditTime()=" + getLastEditTime() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	// 邮箱
	private String email;
	// 性别
	private String gender;
	// 启用状态
	private Integer enableStatus;
	// 1.顾客 2.店家 3.超级管理员
	// 店家可以是顾客 超级管理员可以是顾客或者店家
	private Integer userType;

	public PersonInfo() {}
	/***
	 * 
	 * @param userId
	 * @param name
	 * @param profileImg
	 * @param email
	 * @param gender
	 * @param enableStutus
	 * @param userType
	 * @param createTime
	 * @param lastEditTime
	 */
	public PersonInfo(Long userId, String name, String profileImg, String email, String gender, Integer enableStutus,
			Integer userType, Date createTime, Date lastEditTime) {
		super();
		this.userId = userId;
		this.name = name;
		this.profileImg = profileImg;
		this.email = email;
		this.gender = gender;
		this.enableStatus = enableStutus;
		this.userType = userType;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStutus(Integer enableStutus) {
		this.enableStatus = enableStutus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	// 创建时间
	private Date createTime;
	// 修改时间
	private Date lastEditTime;
}
