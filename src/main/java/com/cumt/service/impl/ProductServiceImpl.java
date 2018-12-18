package com.cumt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cumt.dao.ProductDao;
import com.cumt.dao.ProductImgDao;
import com.cumt.dto.ProductExecution;
import com.cumt.entity.Product;
import com.cumt.entity.ProductImg;
import com.cumt.enums.EnableStatusEnum;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ProductStateEnum;
import com.cumt.service.ProductService;
import com.cumt.util.ImageUtil;
import com.cumt.util.PathUtil;

import exceptions.ProductOperationException;

/***
 * 商品业务接口实现
 * 
 * @author draymonder
 *
 */
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;
	/***
	 * 1、处理缩略图，获取缩略图相对路径并赋值给product
	 * 2、往tb_product写入商品信息，获取productId
	 * 3、结合productId批量处理商品详细图
	 * 4、将商品详情图列表批量插入tb_product_img中
	 */
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
			throws ProductOperationException {
		// 空值判断
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
			// 缩略图不为空则添加
			if(productImg != null) {
				addProductImg(product, productImg);
			}
			
			//创建商品信息
			try {
				int effectedNum = productDao.insertProduct(product);
				if(effectedNum <= 0) {
					throw new ProductOperationException(ProductStateEnum.EDIT_ERROR.getStateInfo());
				}
			} catch(Exception e) {
				throw new ProductOperationException(OperationStatusEnum.ERROR.getStateInfo()+e.getMessage());
			}
			if(productImgList != null && !productImgList.isEmpty()) {
				addProductImgList(product, productImgList);
			}
			return new ProductExecution(OperationStatusEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	/***
	 * 添加商品缩略图 
	 * @param product		商品
	 * @param productImg	商品缩略图
	 */
	private void addProductImg(Product product, MultipartFile productImg) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String productImgAddr = ImageUtil.genarateThumbnail(productImg, dest);
		product.setImgAddr(productImgAddr);
	}

	private void addProductImgList(Product product, List<MultipartFile> productImgList) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgs = new ArrayList<>();
		for(MultipartFile multipartFile : productImgList) {
			String imgAddr = ImageUtil.generateNormalImg(multipartFile, dest);
			ProductImg productImg = new ProductImg();
			productImg.setProductId(product.getProductId());
			productImg.setImgAddr(imgAddr);
			productImg.setCreateTime(new Date());
			productImgs.add(productImg);
		}
		
	}
}
