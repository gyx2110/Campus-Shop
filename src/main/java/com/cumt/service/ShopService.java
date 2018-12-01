package com.cumt.service;


import org.springframework.web.multipart.MultipartFile;

import com.cumt.dto.ShopExecution;
import com.cumt.entity.Shop;

import exceptions.ShopOperationException;

public interface ShopService {
	ShopExecution addShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;
}
