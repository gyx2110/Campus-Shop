/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : o2o

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2019-01-15 17:27:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(100) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('1', '梅苑', '10', '2018-11-20 17:30:42', '2018-11-20 17:30:46');
INSERT INTO `tb_area` VALUES ('2', '兰苑', '10', '2018-11-20 17:40:59', '2018-11-20 17:41:02');
INSERT INTO `tb_area` VALUES ('3', '竹苑', '10', '2018-11-20 17:41:29', '2018-11-20 17:41:32');
INSERT INTO `tb_area` VALUES ('4', '杏苑', '10', '2018-11-20 17:42:23', '2018-11-20 17:42:25');
INSERT INTO `tb_area` VALUES ('5', '桃苑', '11', '2018-11-20 17:43:13', '2018-11-20 17:43:15');

-- ----------------------------
-- Table structure for tb_award
-- ----------------------------
DROP TABLE IF EXISTS `tb_award`;
CREATE TABLE `tb_award` (
  `award_id` int(10) NOT NULL AUTO_INCREMENT,
  `award_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `award_desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `award_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` int(10) NOT NULL DEFAULT '0',
  `priority` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `shop_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`award_id`),
  KEY `fb_award_shop_idx` (`shop_id`),
  CONSTRAINT `fk_award_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_award
-- ----------------------------

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line` (
  `line_id` int(20) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(256) NOT NULL,
  `line_link` varchar(256) NOT NULL,
  `line_img` varchar(256) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------
INSERT INTO `tb_head_line` VALUES ('1', '头条展示', '', '\\upload\\item\\headLine\\2018122121342177520.jpg', '0', '1', '2018-12-20 20:13:09', '2018-12-20 20:16:30');
INSERT INTO `tb_head_line` VALUES ('2', '头条展示', '', '\\upload\\item\\headLine\\2018122121343633167.jpg', '0', '1', '2018-12-20 20:13:21', '2018-12-20 20:13:21');
INSERT INTO `tb_head_line` VALUES ('3', '头条展示', '', '\\upload\\item\\headLine\\2018122121355831787.jpg', '0', '1', '2018-12-20 20:15:49', '2018-12-20 20:15:49');

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`username`) USING BTREE,
  KEY `fk_localauth_profile` (`user_id`) USING BTREE,
  CONSTRAINT `tb_local_auth_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
INSERT INTO `tb_local_auth` VALUES ('1', '2', 'ixiaobing', 'ixiaobing', '2019-01-05 14:11:53', '2019-01-14 16:00:15');

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `profile_img` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(2) COLLATE utf8mb4_bin DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:禁止使用本商城, 1:允许使用本商城',
  `user_type` int(2) NOT NULL DEFAULT '1' COMMENT '1:顾客, 2:店家, 3:超级管理员',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES ('1', 'draymonder', null, '93958042@qq.com', '1', '1', '2', null, null);
INSERT INTO `tb_person_info` VALUES ('2', 'Draymonder', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ertaKhsLXicFrdZ5icFuicv6PB1dXFGa3bGFibkI4KVc12gCaEGXT4KsPkiau68UwKiagm3yHqegWXw28YQ/132', null, '1', '1', '1', '2018-12-29 15:35:17', null);
INSERT INTO `tb_person_info` VALUES ('3', 'Draymonder', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ertaKhsLXicFrdZ5icFuicv6PB1dXFGa3bGFibkI4KVc12gCaEGXT4KsPkiaSjYlbNcmqGAibhWandhI06w/132', null, '1', '1', '1', '2019-01-05 12:01:24', null);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) NOT NULL,
  `product_desc` varchar(64) NOT NULL,
  `img_addr` varchar(256) DEFAULT '',
  `normal_price` varchar(32) DEFAULT NULL,
  `promotion_price` varchar(32) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_procate` (`product_category_id`) USING BTREE,
  KEY `fk_product_shop` (`shop_id`) USING BTREE,
  CONSTRAINT `tb_product_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `tb_product_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '珍珠奶茶', '珍珠奶茶', '\\upload\\item\\shop1\\2018122016430268604.jpeg', '15', '12', '10', null, '2018-12-20 17:00:51', '1', '1', '1');
INSERT INTO `tb_product` VALUES ('2', '暖手杯', '舒服的体验,很适合你', '', '199', '89', '0', '2018-12-18 21:53:27', '2018-12-20 17:05:05', '1', '2', '1');
INSERT INTO `tb_product` VALUES ('3', '被子', '冬天喝水很暖和呢', '\\upload\\item\\shop1\\2018121900203232838.jpg', '20', '18', '0', '2018-12-19 00:20:32', null, '1', null, '1');
INSERT INTO `tb_product` VALUES ('4', '强生药品', '强生药品，你值得信赖', '\\upload\\item\\shop4\\2018121913412149375.jpg', '20', '18.5', '2', '2018-12-19 13:41:21', '2018-12-20 15:35:41', '1', '32', '4');
INSERT INTO `tb_product` VALUES ('5', '牛奶', 'test', '\\upload\\item\\shop1\\2018122017015167736.jpeg', '1', '1', '0', '2018-12-20 17:01:51', '2018-12-23 13:21:55', '1', '2', '1');

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(64) NOT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`) USING BTREE,
  CONSTRAINT `tb_product_category_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES ('1', '珍珠奶茶', '10', '2018-12-09 11:56:27', '1');
INSERT INTO `tb_product_category` VALUES ('2', '测试种类', '1', null, '1');
INSERT INTO `tb_product_category` VALUES ('26', '测试商品种类5', '16', '2018-12-10 19:08:39', '5');
INSERT INTO `tb_product_category` VALUES ('27', '测试商品种类5', '18', '2018-12-10 19:08:39', '5');
INSERT INTO `tb_product_category` VALUES ('32', '药品', '10', '2018-12-11 00:43:28', '4');
INSERT INTO `tb_product_category` VALUES ('33', '零食', '30', '2018-12-11 09:13:00', '6');
INSERT INTO `tb_product_category` VALUES ('34', '液体', '5', '2018-12-19 20:13:10', '4');
INSERT INTO `tb_product_category` VALUES ('35', '水', '5', '2018-12-19 20:13:30', '4');
INSERT INTO `tb_product_category` VALUES ('36', '饮料', '8', '2018-12-19 20:13:30', '4');
INSERT INTO `tb_product_category` VALUES ('37', '咖啡', '10', '2018-12-19 20:19:16', '3');
INSERT INTO `tb_product_category` VALUES ('38', '奶茶', '5', '2018-12-19 20:19:33', '3');
INSERT INTO `tb_product_category` VALUES ('39', '奶盖', '8', '2018-12-19 20:19:33', '3');
INSERT INTO `tb_product_category` VALUES ('41', '咖啡', '6', '2018-12-20 16:28:48', '2');
INSERT INTO `tb_product_category` VALUES ('42', '游戏厅', '10', '2018-12-20 16:33:56', '2');

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(256) NOT NULL,
  `img_desc` varchar(256) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`) USING BTREE,
  CONSTRAINT `tb_product_img_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------
INSERT INTO `tb_product_img` VALUES ('1', '\\upload\\item\\shop2\\2018120522153315025.jpg', 'test', '0', '2018-12-13 22:45:34', '2');
INSERT INTO `tb_product_img` VALUES ('2', '\\upload\\item\\shop2\\2018120522153315025.jpg', 'test', '0', '2018-12-18 21:40:22', '2');
INSERT INTO `tb_product_img` VALUES ('3', '\\upload\\item\\shop2\\2018120522153315025.jpg', 'test', '0', '2018-12-18 21:40:22', '2');
INSERT INTO `tb_product_img` VALUES ('4', '\\upload\\item\\shop1\\2018121900203345229.jpg', 'test', '0', '2018-12-19 00:20:34', '1');
INSERT INTO `tb_product_img` VALUES ('5', '\\upload\\item\\shop1\\2018121900203432471.jpg', 'test', '0', '2018-12-19 00:20:34', '1');
INSERT INTO `tb_product_img` VALUES ('6', '\\upload\\item\\shop4\\2018121913412127207.jpg', 'test', '0', '2018-12-19 13:41:21', '4');
INSERT INTO `tb_product_img` VALUES ('7', '\\upload\\item\\shop4\\2018121913412126046.jpg', 'test', '0', '2018-12-19 13:41:21', '4');
INSERT INTO `tb_product_img` VALUES ('8', '\\upload\\item\\shop1\\2018122017015177596.jpeg', 'test', '0', '2018-12-20 17:01:51', '1');

-- ----------------------------
-- Table structure for tb_product_sell_daily
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_sell_daily`;
CREATE TABLE `tb_product_sell_daily` (
  `product_id` int(100) DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `total` int(10) DEFAULT '0',
  KEY `fk_product_sell_product` (`product_id`),
  KEY `fk_product_sell_shop` (`shop_id`),
  CONSTRAINT `fk_product_sell_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`),
  CONSTRAINT `fk_product_sell_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_sell_daily
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创始人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) NOT NULL,
  `shop_desc` varchar(256) DEFAULT NULL,
  `shop_addr` varchar(256) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `shop_img` varchar(256) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_area` (`area_id`) USING BTREE,
  KEY `fk_shop_profile` (`owner_id`) USING BTREE,
  KEY `fk_shop_shopcate` (`shop_category_id`) USING BTREE,
  CONSTRAINT `tb_shop_ibfk_1` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `tb_shop_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `tb_shop_ibfk_3` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('1', '1', '1', '2', '奶茶店', '这里有新鲜的咖啡和奶茶', null, '1100', '\\upload\\item\\shop2\\2018120522153315025.jpg', '100', '2018-11-23 00:08:26', '2018-11-23 10:30:30', '1', '审核中');
INSERT INTO `tb_shop` VALUES ('2', '1', '3', '3', '畅玩之家', '这是一家游戏厅，欢迎大家来玩', '江苏省徐州市铜山区', '15930909602', '\\upload\\item\\shop2\\2018120522153315025.jpg', '10', '2018-11-23 00:10:12', '2018-12-20 16:26:41', '1', '已经开业');
INSERT INTO `tb_shop` VALUES ('3', '1', '5', '3', '咖啡之翼', '这是一家咖啡厅，欢迎大家品尝新鲜的Coffee', '江苏省徐州市铜山区', '13115201926', '\\upload\\item\\shop2\\2018120522153315025.jpg', '10', '2018-11-30 20:26:11', '2018-12-07 18:13:45', '1', '需要更好的装修');
INSERT INTO `tb_shop` VALUES ('4', '1', '1', '2', '985生活超市', '这里满足了学生们的基本生活需求', '梅二楼下', '13115201111', '\\upload\\item\\shop4\\2018113023061455549.jpg', '12', '2018-11-30 23:06:14', '2018-12-20 16:22:26', '1', '审核中');
INSERT INTO `tb_shop` VALUES ('5', '1', '5', '3', '自助咖啡店', '自助咖啡店，欢迎大家品尝', '桃二楼下', '13115203333', '\\upload\\item\\shop5\\2018120221324575766.jpg', null, '2018-12-02 21:32:45', '2018-12-02 21:32:45', '1', null);
INSERT INTO `tb_shop` VALUES ('6', '1', '3', '4', '自助水果店', '自助水果店', '自助水果店', '13115204444', '\\upload\\item\\shop6\\2018120221411184279.jpg', null, '2018-12-02 21:41:10', '2018-12-02 21:41:10', '1', null);
INSERT INTO `tb_shop` VALUES ('7', '1', '1', '4', '985', '', '', '', '\\upload\\item\\shop2\\2018120522153315025.jpg', null, '2018-12-12 17:35:30', '2018-12-12 17:35:30', '0', null);
INSERT INTO `tb_shop` VALUES ('9', '1', '1', '4', '天水图文', '天水图文，专门为矿大学生服务的打印店', '梅2楼下', '1311999999', '\\upload\\item\\shop2\\2018120522153315025.jpg', null, '2018-12-20 16:01:33', '2018-12-20 16:01:33', '0', null);
INSERT INTO `tb_shop` VALUES ('10', '1', '5', '6', '华为手机', '华为手机专卖店', '桃院楼下', '13111111111', '\\upload\\item\\shop2\\2018120522153315025.jpg', null, '2018-12-20 16:06:27', '2018-12-20 16:06:27', '0', null);
INSERT INTO `tb_shop` VALUES ('11', '2', '2', '6', '小米手机', '小米手机，性价比巨高的无二之选。', '兰苑楼下', '13115201999', '/upload/item/shop11/2019011416131930814.jpg', null, '2019-01-14 16:13:20', '2019-01-14 16:13:20', '1', null);

-- ----------------------------
-- Table structure for tb_shop_auth_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_auth_map`;
CREATE TABLE `tb_shop_auth_map` (
  `shop_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_flag` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`shop_auth_id`),
  KEY `uk_shop_auth_map_shop` (`shop_id`),
  KEY `uk_shop_auth_map` (`employee_id`,`shop_id`),
  CONSTRAINT `fk_shop_auth_map_employee` FOREIGN KEY (`employee_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_auth_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_auth_map
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(256) DEFAULT '',
  `shop_category_img` varchar(256) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`) USING BTREE,
  CONSTRAINT `tb_shop_category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES ('1', '奶茶咖啡', '奶茶咖啡', '\\upload\\item\\shopcategory\\2018120321464952086.jpg', '12', '2018-12-03 15:03:55', '2018-12-22 09:45:58', null);
INSERT INTO `tb_shop_category` VALUES ('2', '咖啡', '咖啡巨好喝的 欢迎品尝', '\\upload\\item\\shopcategory\\2018120321464952086.jpg', '1', '2018-12-03 15:03:55', '2018-12-03 17:09:19', '1');
INSERT INTO `tb_shop_category` VALUES ('3', '奶茶', '奶茶', '\\upload\\item\\shopcategory\\2018120321464952086.jpg', '1', '2018-12-03 15:03:55', '2018-12-22 09:23:55', '1');
INSERT INTO `tb_shop_category` VALUES ('4', '数码', '数码包括新型电子设备', '\\upload\\item\\shopcategory\\2018120321464952086.jpg', '10', '2018-12-03 15:03:55', '2018-12-03 15:03:55', null);
INSERT INTO `tb_shop_category` VALUES ('6', '手机', '手机是信息社会每个人的必需品', '\\upload\\item\\shopcategory\\2018120321464952086.jpg', '10', '2018-12-03 17:07:47', '2018-12-03 21:46:49', '4');

-- ----------------------------
-- Table structure for tb_user_award_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_award_map`;
CREATE TABLE `tb_user_award_map` (
  `user_award_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `award_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `operator_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `user_status` int(2) NOT NULL DEFAULT '0',
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_award_id`),
  KEY `fk_user_award_map_profile` (`user_id`),
  KEY `fk_user_award_map_award` (`award_id`),
  KEY `fk_user_award_map_shop` (`shop_id`),
  CONSTRAINT `fk_user_award_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_user_award_map_award` FOREIGN KEY (`award_id`) REFERENCES `tb_award` (`award_id`),
  CONSTRAINT `fk_user_award_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_user_award_map
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_product_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_product_map`;
CREATE TABLE `tb_user_product_map` (
  `user_product_id` int(30) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `operator_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `point` int(10) DEFAULT '0',
  PRIMARY KEY (`user_product_id`),
  KEY `fk_user_product_map_profile` (`user_id`),
  KEY `fk_user_product_map_product` (`product_id`),
  KEY `fk_user_product_map_shop` (`shop_id`),
  CONSTRAINT `fk_user_product_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_user_product_map_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`),
  CONSTRAINT `fk_user_product_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_product_map
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_shop_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_shop_map`;
CREATE TABLE `tb_user_shop_map` (
  `user_shop_id` int(30) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_shop_id`),
  UNIQUE KEY `uq_user_shop` (`user_id`,`shop_id`),
  KEY `fk_user_shop_shop` (`shop_id`),
  CONSTRAINT `fk_user_shop_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`),
  CONSTRAINT `fk_user_shop_user` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_shop_map
-- ----------------------------

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  UNIQUE KEY `UK_openid` (`open_id`) USING BTREE,
  KEY `fk_wechatauth_profile` (`user_id`) USING BTREE,
  CONSTRAINT `tb_wechat_auth_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
INSERT INTO `tb_wechat_auth` VALUES ('1', '1', '111', '2018-12-29 15:35:17');
INSERT INTO `tb_wechat_auth` VALUES ('2', '2', 'o_ycj5ia9MhHwN2tlYq0749hSnDk', '2019-01-05 12:01:24');
