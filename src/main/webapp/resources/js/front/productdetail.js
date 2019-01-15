$(function() {
	var productId = getQueryString('productId');
	var productUrl = '/ssm/front/listproductinfo?productId=' + productId;
	var $normalPrice = $("#normal-price");
	var $promotionPrice = $("#promotion-price");
	
	$.getJSON(
		productUrl,function(data) {
			if (data.success) {
				var product = data.product;
				$('#product-img').attr('src', product.imgAddr);
				$('#product-time').text(new Date(product.lastEditTime).format("Y-m-d")+'更新');
				$('#product-name').text(product.productName);
				$('#product-desc').text(product.productDesc);
				
				if (product.normalPrice !== undefined && product.promotionPrice !== undefined) {
	                // 如果现价和原价都不为空，则都展示，并给原价加个删除符号
	                $normalPrice.html('原价: <del>¥' + product.normalPrice + '</del>');
	                $promotionPrice.html('现价: ¥'+product.normalPrice);
	            } else if (product.normalPrice !== undefined && product.promotionPrice === undefined) {
	                // 如果原价不为空，而现价为空，则只展示原价
	                $normalPrice.html(product.normalPrice);
	            } else if (product.normalPrice === undefined && product.promotionPrice !== undefined) {
	                // 如果原价为空，现价不为空，则只展示现价
	                $promotionPrice.html(product.promotionPrice);
	            }
				
				// 获取商品详情图片列表
				var productDetailImgList = product.productImgList;
				var swiperHtml = '';
				productDetailImgList.map(function(item, index) {
					swiperHtml += ''
                        + '<div class="swiper-slide img-wrap">'
                        +      '<img class="banner-img" src="'+ item.imgAddr +'" alt="'+ item.imgDesc +'">'
                        + '</div>';
				});
				// 生成购买商品的二维码供商家扫描
//				imgListHtml += '<div> <img src="/ssm/front/generateqrcode4product?productId=' + product.productId + '"/></div>';
				$('.swiper-wrapper').html(swiperHtml);
				 // 设置轮播图轮换时间为1秒
	            $(".swiper-container").swiper({
	                autoplay: 1500,
	                // 用户对轮播图进行操作时，是否自动停止autoplay
	                autoplayDisableOnInteraction: true
	            });
			}
		});
	$('#me').click(function() {
		$.openPanel('#panel-left-demo');
	});
	$.init();
});
