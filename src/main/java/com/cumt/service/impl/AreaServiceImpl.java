package com.cumt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumt.dao.AreaDao;
import com.cumt.entity.Area;
import com.cumt.service.AreaService;
/***
 * 区域Service实现类
 * @author draymonder
 *
 */
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> getAreaList() {
		if(areaDao != null) 
			return areaDao.queryArea();
		System.out.println("areaDao为空");
		return null;
	}
}
