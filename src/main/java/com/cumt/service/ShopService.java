package com.cumt.service;


import org.springframework.web.multipart.MultipartFile;

import com.cumt.dto.ShopExecution;
import com.cumt.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop, MultipartFile  shopImg);
}
