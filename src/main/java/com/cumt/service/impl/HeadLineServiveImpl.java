package com.cumt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cumt.dao.HeadLineDao;
import com.cumt.entity.HeadLine;
import com.cumt.service.HeadLineService;

/***
 * 首页头条业务实现类
 * 
 * @author draymonder
 *
 */
@Service
public class HeadLineServiveImpl implements HeadLineService {

	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
		return headLineDao.queryHeadLineList(headLineCondition);
	}

}
