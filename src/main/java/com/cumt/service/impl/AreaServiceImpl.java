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
import com.cumt.dao.AreaDao;
import com.cumt.entity.Area;
import com.cumt.exceptions.AreaOperationException;
import com.cumt.service.AreaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * 区域Service实现类
 * 
 * @author draymonder
 *
 */
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Autowired
	private JedisUtil.Strings jedisStrings;

	private static Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

	/***
	 * 获取区域列表
	 */
	@Override
	@Transactional
	public List<Area> getAreaList() {
		// 定义redis的key
		String key = AREALISTKEY;
		// 定义接受对象
		List<Area> areaList = null;
		// 对象转换为string
		ObjectMapper mapper = new ObjectMapper();
		// 判断key是否存在
		if (!jedisKeys.exists(key)) {
			// 不存在，先从数据库取值
			areaList = areaDao.queryArea();
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(areaList);
			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			}
			// json String 存入redis中
			jedisStrings.set(key, jsonString);
		} else {
			// 缓存中有值，直接取出来
			String jsonString = jedisStrings.get(key);
			// 转换成ArrayList<Area>的形式
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			try {
				areaList = mapper.readValue(jsonString, javaType);
			} catch (JsonParseException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			} catch (JsonMappingException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new AreaOperationException(e.getMessage());
			}
			System.out.println("redis获取 " + key + " success");
			log.debug("redis获取 " + key + " success");
		}
		return areaList;
	}
}
