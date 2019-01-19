package com.cumt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.cumt.exceptions.ProductOperationException;
import com.cumt.service.ProductService;
import com.cumt.util.ImageUtil;
import com.cumt.util.PageCalculator;
import com.cumt.util.PathUtil;

/***
 * 商品业务接口实现
 * 
 * @author draymonder
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;

	/***
	 * 1、处理缩略图，获取缩略图相对路径并赋值给product 2、往tb_product写入商品信息，获取productId
	 * 3、结合productId批量处理商品详细图 4、将商品详情图列表批量插入tb_product_img中
	 */
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
			// 缩略图不为空则添加
			if (productImg != null) {
				addProductImg(product, productImg);
			}

			// 创建商品信息
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException(ProductStateEnum.EDIT_ERROR.getStateInfo());
				}
			} catch (Exception e) {
				throw new ProductOperationException(OperationStatusEnum.ERROR.getStateInfo() + e.getMessage());
			}
			if (productImgList != null && !productImgList.isEmpty()) {
				addProductImgList(product, productImgList);
			}
			return new ProductExecution(OperationStatusEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/***
	 * 添加商品缩略图
	 * 
	 * @param product
	 *            商品
	 * @param productImg
	 *            商品缩略图
	 */
	private void addProductImg(Product product, MultipartFile productImg) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String productImgAddr = ImageUtil.genarateThumbnail(productImg, dest);
		product.setImgAddr(productImgAddr);
	}

	/***
	 * 添加商品详情图
	 * 
	 * @param product
	 *            商品
	 * @param productImgList
	 *            商品详情图列表
	 */
	private void addProductImgList(Product product, List<MultipartFile> productImgList) {
		// 获取图片存储路径
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgs = new ArrayList<>();
		for (MultipartFile multipartFile : productImgList) {
			// 将每个图片放到相应店铺的文件夹下
			String imgAddr = ImageUtil.generateNormalImg(multipartFile, dest);
			ProductImg productImg = new ProductImg();
			productImg.setProductId(product.getProductId());
			productImg.setImgAddr(imgAddr);
			productImg.setCreateTime(new Date());
			productImgs.add(productImg);
		}

		// 如果有图片，就执行批量添加操作
		if (productImgs.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgs);
				if (effectedNum <= 0) {
					throw new ProductOperationException(OperationStatusEnum.PIC_UPLOAD_ERROR.getStateInfo());
				}
			} catch (Exception e) {
				throw new ProductOperationException(
						OperationStatusEnum.PIC_UPLOAD_ERROR.getStateInfo() + e.getMessage());
			}
		}
	}
	
	
	@Override
	@Transactional
	public ProductExecution updateProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 设置最后更新时间
			product.setLastEditTime(new Date());
			// 缩略图不为空则添加
			if (productImg != null) {
				// 先获取原有信息，得到原有图片地址
				Product orignalProduct = productDao.queryProductById(product.getProductId());
				// 删除原有图片
				if(orignalProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(orignalProduct.getImgAddr());
				}
				// 增加缩略图
				addProductImg(product, productImg);
			}
			
			// 商品详情图片不为空  则删除原有的图片再添加
			if(productImgList != null && !productImgList.isEmpty()) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImgList);
			}
			
			// 更新商品信息
			try {
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException(ProductStateEnum.EDIT_ERROR.getStateInfo());
				}
				return new ProductExecution(OperationStatusEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException(OperationStatusEnum.ERROR.getStateInfo() + e.getMessage());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	
	/***
	 * 删除某个商品下的详情图
	 * @param productId
	 */
	private void deleteProductImgList(Long productId) {
		// 根据productId获取原有的图片
		List<ProductImg> productImgList = productImgDao.queryProductImgListByProductId(productId);
		if(productImgList != null && !productImgList.isEmpty()) {
			for(ProductImg productImg : productImgList) {
				// 删除文件中的图片
				ImageUtil.deleteFileOrPath(productImg.getImgAddr());
			}
			// 删除数据库的记录
			productImgDao.deleteProductImgByProductId(productId);
		}
		
		
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		// 换算成行数
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		// 获取相应条件下的list
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		return new ProductExecution(OperationStatusEnum.SUCCESS, productList);
	}
}
