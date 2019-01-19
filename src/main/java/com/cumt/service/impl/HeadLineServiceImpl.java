package com.cumt.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cumt.cache.JedisUtil;
import com.cumt.dao.HeadLineDao;
import com.cumt.entity.HeadLine;
import com.cumt.exceptions.HeadLineOperationException;
import com.cumt.service.HeadLineService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * 首页头条业务实现类
 * 
 * @author draymonder
 *
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private JedisUtil.Strings jedisStrings;

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Autowired
	private HeadLineDao headLineDao;

	private static Logger log = LoggerFactory.getLogger(HeadLineServiceImpl.class);

	@Override
	@Transactional
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
		// return headLineDao.queryHeadLineList(headLineCondition);
		String key = HEADLINE;
		List<HeadLine> headLineList = null;
		ObjectMapper mapper = new ObjectMapper();
		// 根据查询条件的状态得到不同的key值
		if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
			key = key + "_" + headLineCondition.getEnableStatus();
		}
		// 如果不存在这个key，就直接从数据库读取
		if(!jedisKeys.exists(key)) {
			headLineList = headLineDao.queryHeadLineList(headLineCondition);
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(headLineList);
			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new HeadLineOperationException(e.getMessage());
			}
			jedisStrings.set(key, jsonString);
		} else {
			// 现在存在 直接读取redis
			String jsonString = jedisStrings.get(key);
			// 指定将String 转换成的集合类型
			JavaType javatype = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
			
			try {
				headLineList = mapper.readValue(jsonString, javatype);
			} catch (JsonParseException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new HeadLineOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new HeadLineOperationException(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new HeadLineOperationException(e.getMessage());
			}
			System.out.println("redis获取 " + key + " success");
			log.debug("redis获取 " + key + " success");
		}
		return headLineList;
	}

}
