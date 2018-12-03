package com.cumt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cumt.entity.ShopCategory;
/***
 * 店铺分类接口
 * @author draymonder
 *
 */

public interface ShopCategoryDao {
	/**
	 * 根据查询条件获取分页列表 
	 * 需求：
	 *    1、首页展示一级目录（即parent_id 为 null的店铺类别） 
	 *    2、点进去某个一级目录加载对应目录下的子目录
	 *    3、店铺只能挂在店铺二级类别下
	 *    4、在首页点击某个一级店铺目录 进入店铺展示页面的时候 需要加载对应目录下的子目录
	 * 
	 * @param shopCategoryCondition 传入一个category，然后如果他父亲节点不为null,那就返回父亲节点的所有儿子信息
	 * @param rowIndex              从第几行开始取
	 * @param pageSize              取几行
	 * @return
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/***
	 * 增加shopCategory 店铺种类
	 * @param shopCategory
	 * @return -1 操作失败
	 */
	int insertShopCategory(@Param("shopCategory")ShopCategory shopCategory);
	
	
	/***
	 * 修改shopCategory 店铺种类
	 * @param shopCategory
	 * @return
	 */
	int updateShopCategory(@Param("shopCategory")ShopCategory shopCategory);
	
	/**
	 * 根据Id查询店铺分类信息
	 * 
	 * @param shopCategory
	 * @return
	 */
	ShopCategory selectShopCategoryById(long shopCategoryId);
}
