package com.cumt.service;

import java.util.List;

import com.cumt.entity.HeadLine;

/***
 * 首页头条业务接口
 * @author draymonder
 *
 */
public interface HeadLineService {
	public static final String HEADLINE = "headlinelist";
	
	/***
	 * 根据条件查询头条列表
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
