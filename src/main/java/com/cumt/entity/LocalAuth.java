package com.cumt.entity;

import java.util.Date;

/***
 * 本地用户实体类
 * 
 * @author draymonder
 *
 */
public class LocalAuth {
	// id
	private Long localAuthId;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 创建时间
	private Date createTime;
	@Override
	public String toString() {
		return "LocalAuth [localAuthId=" + localAuthId + ", username=" + username + ", password=" + password
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", personInfo=" + personInfo
				+ ", getLocalAuthId()=" + getLocalAuthId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getCreateTime()=" + getCreateTime() + ", getLastEditTime()=" + getLastEditTime()
				+ ", getPersonInfo()=" + getPersonInfo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	// 修改时间
	private Date lastEditTime;

	public LocalAuth() {}
	
	/***
	 * 
	 * @param localAuthId
	 * @param username
	 * @param password
	 * @param createTime
	 * @param lastEditTime
	 * @param personInfo
	 */
	public LocalAuth(Long localAuthId, String username, String password, Date createTime, Date lastEditTime,
			PersonInfo personInfo) {
		super();
		this.localAuthId = localAuthId;
		this.username = username;
		this.password = password;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
		this.personInfo = personInfo;
	}

	public Long getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	// 用户信息
	private PersonInfo personInfo;
}
