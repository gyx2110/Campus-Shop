package com.cumt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cumt.dto.ProductExecution;
import com.cumt.entity.Product;

import exceptions.ProductOperationException;

/***
 * 商品业务接口
 * 
 * @author draymonder
 *
 */
public interface ProductService {
	/***
	 * 添加商品信息以及图片处理
	 * 
	 * @param product
	 *            商品信息
	 * @param productImg
	 *            商品缩略图
	 * @param productImgList
	 *            商品图片列表
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
			throws ProductOperationException;

	/***
	 * 修改商品信息以及图片处理
	 * 
	 * @param product
	 *            商品信息
	 * @param productImg
	 *            商品信息
	 * @param productImgList
	 *            商品图片列表
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution updateProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
			throws ProductOperationException;
	/***
	 * 根据商品Id查询商品详情
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	/***
	 * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id，商品类别
	 * @param productCondition 查询条件
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
