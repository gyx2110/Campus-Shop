package com.cumt.service;

import java.util.List;

import com.cumt.entity.Area;

/***
 * 区域Service
 * 
 * @author draymonder
 *
 */
public interface AreaService {
	public static final String AREALISTKEY = "arealist";
	/***
	 * 获取区域列表
	 * @return
	 */
	public List<Area> getAreaList();
}
