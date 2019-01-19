package com.cumt.dto;

import java.util.List;

import com.cumt.entity.Product;
import com.cumt.enums.OperationStatusEnum;
import com.cumt.enums.ProductStateEnum;

/***
 * 商品返回结果信息
 * 
 * @author draymonder
 *
 */
public class ProductExecution {
	// 结果状态
	private int state;
	// 状态标识
	private String stateInfo;
	// 商品数量
	private int count;
	// 操作的Product(增删改的时候用)
	private Product product;

	// 获取到的ProductList (查询商品列表用)
	private List<Product> productList;

	public ProductExecution() {
	}

	// 商品操作失败的时候使用的构造器
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 商品操作成功的时候使用的构造器
	public ProductExecution(OperationStatusEnum stateEnum, Product product) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.product = product;
	}

	// 商品操作成功的时候使用的构造器
	public ProductExecution(OperationStatusEnum stateEnum, List<Product> productList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
		this.count = productList.size();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
