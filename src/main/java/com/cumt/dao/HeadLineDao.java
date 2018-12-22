package com.cumt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cumt.entity.HeadLine;

/***
 * 头条数据接口
 * @author draymonder
 *
 */
public interface HeadLineDao {
	/***
	 * 添加首页头条
	 * @param headLine
	 * @return
	 */
	int insertHeadLine(HeadLine headLine);
	/***
	 * 根据传入的查询条件查询头条信息, 条件查询有(是否可视化)
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLineList(@Param("headLineCondition")HeadLine headLineCondition);
	/***
	 * 根据头条Id查询头条信息
	 * @param lineId
	 * @return
	 */
	HeadLine queryHeadLineById(long lineId);
	/***
	 * 更新头条信息
	 * @param headLine
	 * @return
	 */
	int updateHeadLine(HeadLine headLine);
}
