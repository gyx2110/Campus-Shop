package com.cumt.dao;

import java.util.List;

import com.cumt.entity.Area;

/**
 * 区域接口
 * 
 * @author draymonder
 *
 */

// Dao层只需要接口 函数名称 就可以在mapper里面写语句
public interface AreaDao {
	/***
	 * 列出区域列表
	 * 
	 * @return
	 */
	public List<Area> queryArea();
}
