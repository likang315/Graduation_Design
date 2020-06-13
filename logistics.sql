/*
 Navicat Premium Data Transfer

 Source Server         : ngrok.makalu.cc
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : ngrok.makalu.cc:3306
 Source Schema         : logistics

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 21/02/2020 19:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dicTypeId` int(11) DEFAULT NULL,
  `dicKey` varchar(20) DEFAULT NULL,
  `dicName` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic
-- ----------------------------
BEGIN;
INSERT INTO `dic` VALUES (1, 1, 'res_directory', '目录', '目录');
INSERT INTO `dic` VALUES (2, 1, 'res_menu', '菜单', '菜单');
INSERT INTO `dic` VALUES (3, 1, 'res_btn', '按扭', '按扭');
COMMIT;

-- ----------------------------
-- Table structure for dic_type
-- ----------------------------
DROP TABLE IF EXISTS `dic_type`;
CREATE TABLE `dic_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dicTypeKey` varchar(20) DEFAULT NULL,
  `dicTypeName` varchar(40) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dic_type
-- ----------------------------
BEGIN;
INSERT INTO `dic_type` VALUES (1, 'res_type', '菜单类型', '菜单类型是目录,菜单,按扭,');
INSERT INTO `dic_type` VALUES (2, '2', '2', '2');
COMMIT;

-- ----------------------------
-- Table structure for mkl_courier_store
-- ----------------------------
DROP TABLE IF EXISTS `mkl_courier_store`;
CREATE TABLE `mkl_courier_store` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logistics` varchar(100) DEFAULT NULL COMMENT '订单号（订单id）',
  `courier_Phone` varchar(255) DEFAULT NULL COMMENT '快递员电话',
  `courier_longitude` double(10,7) DEFAULT NULL COMMENT '快递员经度',
  `courier_latitude` double(10,7) DEFAULT NULL COMMENT '快递员当前纬度',
  `actualTime` datetime DEFAULT NULL COMMENT '实时定位时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=182 DEFAULT CHARSET=utf8 COMMENT='快递员实时坐标';

-- ----------------------------
-- Records of mkl_courier_store
-- ----------------------------
BEGIN;
INSERT INTO `mkl_courier_store` VALUES (134, '20171122181306258', '15091637106', 108.9530983, 34.2777999, '2017-11-22 18:17:32');
INSERT INTO `mkl_courier_store` VALUES (135, '20171122181306508', '15091637106', 108.9530983, 34.2777999, '2017-11-22 18:17:51');
INSERT INTO `mkl_courier_store` VALUES (136, '20171122182132312', '13259710305', 108.9530983, 34.2777999, '2017-11-22 18:26:45');
INSERT INTO `mkl_courier_store` VALUES (137, '20171122182132469', '13259710305', 108.9530983, 34.2777999, '2017-11-22 18:27:38');
INSERT INTO `mkl_courier_store` VALUES (138, '20171122182132312', '13259710305', 108.9530983, 34.2777999, '2017-11-22 18:28:46');
INSERT INTO `mkl_courier_store` VALUES (139, '20171122183241773', '13259710305', 108.9530983, 34.2777999, '2017-11-22 18:33:40');
INSERT INTO `mkl_courier_store` VALUES (140, '20180419182723364', '13545675856', 108.9230349, 34.1901088, '2018-05-04 14:10:28');
INSERT INTO `mkl_courier_store` VALUES (145, '20180504152818402', '13545675856', 108.9500000, 34.2700000, '2018-05-29 15:56:29');
INSERT INTO `mkl_courier_store` VALUES (146, '20180504160422000', '15091637106', 108.9530983, 34.2777999, '2018-05-04 16:04:57');
INSERT INTO `mkl_courier_store` VALUES (147, '20180509184256912', '13545675856', 108.9530983, 34.2777999, '2018-05-11 11:48:14');
INSERT INTO `mkl_courier_store` VALUES (148, '20180508173902147', '13545675856', 108.9300030, 34.2932280, '2018-06-15 10:29:06');
INSERT INTO `mkl_courier_store` VALUES (149, '20180511103540233', '13545675856', 108.9229230, 34.1901380, '2018-06-14 17:54:20');
INSERT INTO `mkl_courier_store` VALUES (150, '20180508173803051', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:02:40');
INSERT INTO `mkl_courier_store` VALUES (151, '20180508164902148', '13545675856', 108.9530983, 34.2777999, '2018-05-11 14:53:08');
INSERT INTO `mkl_courier_store` VALUES (152, '20180508164902412', '13545675856', 108.9292870, 34.2930170, '2018-05-14 18:55:14');
INSERT INTO `mkl_courier_store` VALUES (153, '20180419182832101', '13545675856', 108.9230340, 34.1901010, '2018-05-04 18:24:04');
INSERT INTO `mkl_courier_store` VALUES (154, '20180510204800825', '13545675856', 108.9229236, 34.1900848, '2018-05-16 19:23:26');
INSERT INTO `mkl_courier_store` VALUES (155, '20171122182132469', '13545675856', 108.9229060, 34.1901320, '2018-06-19 09:49:44');
INSERT INTO `mkl_courier_store` VALUES (156, '20180511103540233', '13545675856', 108.9229230, 34.1901380, '2018-06-14 17:54:20');
INSERT INTO `mkl_courier_store` VALUES (157, '20180510204800825', '13545675856', 108.9229236, 34.1900848, '2018-05-16 19:23:26');
INSERT INTO `mkl_courier_store` VALUES (158, '20180504160422000', '13545675856', 108.9230050, 34.1901070, '2018-05-17 19:22:17');
INSERT INTO `mkl_courier_store` VALUES (159, '20180508165127237', '13545675856', 108.9230050, 34.1901070, '2018-05-22 19:37:22');
INSERT INTO `mkl_courier_store` VALUES (163, '20180511103540337', '13545675856', 108.9292900, 34.2929320, '2018-05-19 11:52:09');
INSERT INTO `mkl_courier_store` VALUES (161, '20180508173902147', '13545675856', 108.9300030, 34.2932280, '2018-06-15 10:29:06');
INSERT INTO `mkl_courier_store` VALUES (164, '20180614102005869', '13545675856', 108.9229020, 34.1901300, '2018-06-14 10:20:58');
INSERT INTO `mkl_courier_store` VALUES (165, '20180614102230808', '13545675856', 108.9300030, 34.2932280, '2018-06-15 10:29:58');
INSERT INTO `mkl_courier_store` VALUES (166, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (167, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (168, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (169, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (170, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (171, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (172, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (173, '20180614102230808', '13545675856', 108.9300030, 34.2932280, '2018-06-15 10:29:58');
INSERT INTO `mkl_courier_store` VALUES (174, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (175, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (176, '20180614212026650', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:01:15');
INSERT INTO `mkl_courier_store` VALUES (177, '20180508173803051', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:02:40');
INSERT INTO `mkl_courier_store` VALUES (178, '20180615110317796', '13545675856', 108.9300030, 34.2932280, '2018-06-20 12:04:01');
INSERT INTO `mkl_courier_store` VALUES (179, '20180509184442642', '13545675856', 108.9229350, 34.1901230, '2018-06-15 11:10:37');
INSERT INTO `mkl_courier_store` VALUES (180, '20180615110317796', '13545675856', 108.9300030, 34.2932280, '2018-06-20 12:04:01');
INSERT INTO `mkl_courier_store` VALUES (181, '20180615110317796', '13545675856', 108.9300030, 34.2932280, '2018-06-20 12:04:01');
COMMIT;

-- ----------------------------
-- Table structure for mkl_courier_temp
-- ----------------------------
DROP TABLE IF EXISTS `mkl_courier_temp`;
CREATE TABLE `mkl_courier_temp` (
  `accountName` varchar(255) DEFAULT NULL COMMENT '导入快递员的账号',
  `real_name` varchar(255) DEFAULT NULL COMMENT '导入快递员的姓名',
  `createUser` varchar(255) DEFAULT NULL COMMENT '导入该批数据的管理员',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `accountType` varchar(255) DEFAULT NULL COMMENT '用户类型（0普通用户，1店长，2快递员，3管理）',
  `state` varchar(255) DEFAULT NULL COMMENT '是否可用',
  `createTime` datetime DEFAULT NULL COMMENT '账户的创建时间',
  `groupId` int(11) DEFAULT NULL COMMENT '组织架构的ID',
  `auth_flag` int(11) DEFAULT NULL COMMENT '审核标示(1 未审核 2 成功 3 失败)',
  `company` char(32) DEFAULT NULL COMMENT '归属快递公司的id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='快递员临时表数据';

-- ----------------------------
-- Table structure for mkl_express_company
-- ----------------------------
DROP TABLE IF EXISTS `mkl_express_company`;
CREATE TABLE `mkl_express_company` (
  `id` char(32) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `company_address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_phone` char(11) DEFAULT NULL COMMENT '创建人电话',
  `delete_flag` int(11) DEFAULT '0' COMMENT '数据状态（0正常，1删除）',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='快递公司';

-- ----------------------------
-- Records of mkl_express_company
-- ----------------------------
BEGIN;
INSERT INTO `mkl_express_company` VALUES ('f914f79fb6924e6b9ba321ff07de2527', '中通快递', '南三环店', '2017-04-13 19:14:16', '15229356823', 1);
INSERT INTO `mkl_express_company` VALUES ('70057c0b93ec4e72a8c4c6fd4cb6175d', '韵达', '杜城店', '2017-04-13 19:14:47', '15229356823', 0);
INSERT INTO `mkl_express_company` VALUES ('3f9abe1782494e23bd8dc28bda9e9e8c', '顺丰', '钟楼', '2017-04-18 15:00:29', '15229356823', 1);
INSERT INTO `mkl_express_company` VALUES ('91ce2f88f37349968542e2ebf3f56721', '顺丰', '钟楼', '2017-04-18 15:03:25', '15229356823', 0);
INSERT INTO `mkl_express_company` VALUES ('f66d731428f74e69a5386e56cb9fae58', '中通', '雁塔区雁环路55号', '2017-04-18 15:07:16', '15229356823', 0);
INSERT INTO `mkl_express_company` VALUES ('d0d36ea936a548babf862955317fa289', '申通', '凤城五路中段', '2017-04-18 15:10:12', '15229356823', 0);
INSERT INTO `mkl_express_company` VALUES ('bc2af42b2f594cf1a0129fc7b095782f', '天天快递', '雁环中路', '2017-04-18 15:20:18', '15229356823', 1);
INSERT INTO `mkl_express_company` VALUES ('fef1bae57f2946f8bccaa985abd0e299', '圆通', '电子正街', '2017-11-21 16:45:13', 'root', 0);
COMMIT;

-- ----------------------------
-- Table structure for mkl_mail_info
-- ----------------------------
DROP TABLE IF EXISTS `mkl_mail_info`;
CREATE TABLE `mkl_mail_info` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主键（年月日时分秒毫秒+3位随机数）',
  `shipperName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '发货人姓名',
  `shipper` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货人电话',
  `shipTime` datetime DEFAULT NULL COMMENT '发货时间',
  `groupId` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发货人的组织id',
  `province` varchar(10) DEFAULT NULL COMMENT '收货地址所属省（冗余）',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货地址所属市',
  `county` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货人所属县区',
  `qdmc` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货人渠道',
  `store` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货门店名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货具体地址',
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货人电话',
  `logistics` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '物流单号（冗余）',
  `predictTime` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮寄预计时长',
  `factTime` varchar(225) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '实际邮寄时长',
  `state` int(1) DEFAULT '0' COMMENT '发货状态（默认0为待发货，1为订单已生成，2快递员接收订单，3快递员已收货,4订单派送中,5货物已送达，6已签收,7拒签,8快递员返货中，9返货成功，10需求审核中）',
  `leadTime` datetime DEFAULT NULL COMMENT '收货时间',
  `signPeople` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '签收人',
  `signPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收货电话',
  `isDelete` varchar(1) DEFAULT '0' COMMENT '是否删除（必须是待邮寄状态）',
  `deleteReason` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '删除原因',
  `question` varchar(100) DEFAULT NULL COMMENT '疑问',
  `rejectReason` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '拒绝收货原因',
  `isReplace` varchar(1) DEFAULT '0' COMMENT '是否代收(0代表否，1代表是)',
  `agentName` varchar(100) DEFAULT NULL COMMENT '代收人姓名',
  `agentPhone` varchar(100) DEFAULT NULL COMMENT '代收人电话',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `createTime` datetime DEFAULT NULL COMMENT '数据插入时间',
  `material` varchar(100) DEFAULT NULL COMMENT '物资id（以“，”进行分割）',
  `materialContent` varchar(100) DEFAULT NULL COMMENT '物资名称（以“，”分割）',
  `materialNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '物质数量（以“，”分割）',
  `courierPhone` char(11) DEFAULT NULL COMMENT '快递员电话',
  `courierName` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '快递员姓名',
  `recipientTime` datetime DEFAULT NULL COMMENT '快递员收件时间',
  `serviceTime` datetime DEFAULT NULL COMMENT '快递员送达时间',
  `store_longitude` double(10,7) DEFAULT NULL COMMENT '门店经度',
  `store_latitude` double(10,7) DEFAULT NULL COMMENT '门店纬度',
  `channel_code` varchar(255) DEFAULT NULL COMMENT '收货地址渠道编码',
  `orderImg` varchar(255) DEFAULT NULL COMMENT '订单照片图片信息',
  `storeImg` varchar(255) DEFAULT NULL COMMENT '门店的图片的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='邮寄明细表';

-- ----------------------------
-- Records of mkl_mail_info
-- ----------------------------
BEGIN;
INSERT INTO `mkl_mail_info` VALUES ('20171122181306258', '殷瑜泰', '15229356823', '2017-11-22 18:13:06', '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, '2', '2分7秒', 2, '2017-11-22 18:17:44', '王磊岐', '18792462923', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2017-11-22 18:13:06', NULL, '实名制（过塑）,赠品,号卡', '20,20,20', '13545675856', '王丰', '2017-11-22 18:15:37', '2017-11-22 18:17:44', 108.9530983, 34.2777999, 'AR7858', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20171122181306508', '殷瑜泰', '15229356823', '2017-11-22 18:13:06', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼31号', '钟楼店长', '15236356991', NULL, '2', '29秒', 2, '2017-11-22 18:18:20', '钟楼店长', '15236356991', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2017-11-22 18:13:06', NULL, '实名制（过塑）,赠品,号卡', '20,20,20', '13545675856', '王丰', '2017-11-22 18:17:51', '2017-04-19 18:18:20', 108.9530983, 34.2777999, 'AR7858', 'http://112.74.88.25/logistics/upload/295ac0be-f46e-47c4-8e55-e3192cffcbd8.jpg', 'http://112.74.88.25/logistics/upload/6f47de11-7f70-4238-a9ef-460fec0a0a80.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20171122182132312', 'root', 'root', '2017-11-22 18:28:29', '1', NULL, NULL, NULL, NULL, '1小寨店', '小寨三福隔壁', '王磊岐', '18792462923', NULL, '2~3', NULL, 2, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2017-11-22 18:21:32', NULL, '实名制（过塑）,海报,副卡,单页', '10.0,10.0,0,50', '13545675856', '王丰', '2017-11-22 18:28:46', '2017-04-19 17:16:04', NULL, NULL, 'AR7858', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20171122182132469', 'root', 'root', '2018-05-16 19:14:31', '1', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '王磊岐', '18792462923', NULL, '2~3', NULL, 4, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'vivo', '2017-11-22 18:21:32', NULL, '实名制（过塑）,海报,单页,桌卡,摇摇,工单,空卡,副卡,', '20.0,0,0,0,0,0,0,0,', '13545675856', '王丰', '2018-05-16 19:21:31', '2017-04-19 17:16:09', 108.9530983, 34.2777999, 'AR7858', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20171122183241773', '殷瑜泰', '15229356823', '2017-11-22 18:32:41', '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, '2', '43秒', 2, '2017-11-22 18:34:10', '王磊岐', '18792462923', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2017-11-22 18:32:41', NULL, '实名制（过塑）', '10', '13545675856', '王丰', '2017-11-22 18:33:27', '2017-11-22 18:34:10', 108.9530983, 34.2777999, 'A00001', 'http://112.74.88.25/logistics/upload/731b5935-e29e-47ee-bfa7-e608421e7eaf.jpg', 'http://112.74.88.25/logistics/upload/7b528bc8-1e42-4023-a352-166a704c91d6.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180419182723364', '殷瑜泰', '15229356823', '2018-04-19 18:27:24', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '2', '16小时14分31秒', 2, '2018-05-04 14:13:12', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-04-19 18:27:24', NULL, '实名制（过塑）', '10', '13545675856', '王丰', '2018-05-03 21:58:41', '2018-05-04 14:13:12', 108.9530983, 34.2777999, 'AR7858', 'http://112.74.88.25/logistics/upload/ce7348f4-09e3-4a56-9be8-bbc98d1f431f.png', 'http://112.74.88.25/logistics/upload/e1c28e8c-ac19-48ba-ae16-fef6b0a32b02.png');
INSERT INTO `mkl_mail_info` VALUES ('20180419182832101', '殷瑜泰', '15229356823', '2018-04-19 18:28:32', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼31号', '钟楼店长', '15236356991', NULL, '2', NULL, 2, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-04-19 18:28:32', NULL, '工单', '10', '15091637106', '张芝', '2018-05-16 18:24:00', NULL, 108.9530983, 34.2777999, 'A00262', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180504152818402', '殷瑜泰', '15229356823', '2018-05-04 15:28:32', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '2', '21分44秒', 2, '2018-05-04 16:03:16', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-05-04 15:28:34', NULL, '号卡', '40', '13545675856', '王丰', '2018-05-04 15:41:32', '2018-05-04 16:03:16', 108.9530983, 34.2777999, 'AR7858', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180504160422000', '殷瑜泰', '15229356823', '2018-05-04 16:04:22', '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, '2', '2018-05-16 15:28:43', 4, '2018-05-16 15:28:43', '王磊岐', '18792462923', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-04 16:04:22', NULL, '工单,单页', '20,20', '15091637106', '张芝', '2018-05-16 19:22:15', '2018-05-16 15:28:43', 108.9530983, 34.2777999, 'A00001', 'http://112.74.88.25/logistics/upload/07fd779f-ede3-4856-8132-dbb79c80a46f.png', 'http://112.74.88.25/logistics/upload/6735c508-ee1d-4afe-8b7c-8752e8ec6f46.png');
INSERT INTO `mkl_mail_info` VALUES ('20180508164902148', '殷瑜泰', '15229356823', '2018-05-08 16:49:02', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼25号', '钟楼店长', '15236356985', NULL, '1', '2分26秒', 2, '2018-05-11 14:55:34', '钟楼店长', '15236356985', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-08 16:49:02', NULL, '桌卡,摇摇', '5,1', '13545675856', '张四', '2018-05-11 14:53:08', '2018-05-11 14:55:34', 108.9530983, 34.2777999, 'A00230', 'http://112.74.88.25/logistics/upload/af956d4a-6276-4567-becb-fcdd4f18cd2e.jpg', 'http://112.74.88.25/logistics/upload/e0a2b08a-a486-4c03-9a88-5e0d115accbc.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180508164902412', '殷瑜泰', '15229356823', '2018-05-08 16:49:02', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼27号', '钟楼店长', '15236356987', NULL, '1', NULL, 2, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-08 16:49:02', NULL, '桌卡,摇摇', '5,1', '13545675856', '张四', '2018-05-11 22:53:21', NULL, 108.9530983, 34.2777999, 'A00258', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180508165127237', '殷瑜泰', '15229356823', '2018-05-08 16:51:27', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼28号', '钟楼店长', '15236356988', NULL, '1', '2018-05-11 18:05:49', 4, '2018-05-11 18:05:49', '钟楼店长', '15236356988', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-08 16:51:27', NULL, '实名制（过塑）,摇摇', '2,1', '13545675856', '张四', '2018-05-16 19:37:21', '2018-05-11 18:05:49', 108.9530983, 34.2777999, 'A00259', 'http://112.74.88.25/logistics/upload/cd522538-6fc7-4524-b520-1ddf41afbfbc.png', 'http://112.74.88.25/logistics/upload/2aec22a3-9cf7-4754-bf34-0f3439a8c660.png');
INSERT INTO `mkl_mail_info` VALUES ('20180508173803051', '殷瑜泰', '15229356823', '2018-05-08 17:38:03', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼26号', '钟楼店长', '15236356986', NULL, '1', '5分19秒', 4, '2018-05-11 14:47:14', '钟楼店长', '15236356986', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-05-08 17:38:03', NULL, '实名制（过塑）', '1', '13545675856', '张四', '2018-06-15 11:02:38', '2018-05-11 14:47:14', 108.9530983, 34.2777999, 'A00212', 'http://112.74.88.25/logistics/upload/79c05ad4-efac-4b71-b1ea-4d137bb8e324.jpg', 'http://112.74.88.25/logistics/upload/f32ca37a-50fd-48a7-a78e-69760fba3821.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180508173902147', '殷瑜泰', '15229356823', '2018-05-08 17:39:02', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼26号', '钟楼店长', '15236356986', NULL, '1', '1天3小时45分22秒', 4, '2018-05-11 14:21:29', '钟楼店长', '15236356986', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-05-08 17:39:02', NULL, '实名制（过塑）', '1', '13545675856', '李四', '2018-05-16 19:43:52', '2018-05-11 14:21:29', 0.0000000, NULL, 'A00212', 'http://112.74.88.25/logistics/upload/d2ee53e1-2eca-46bd-847f-591b81110b49.jpg', 'http://112.74.88.25/logistics/upload/721cb138-7455-495c-a948-366b4d353c77.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180509184256912', '殷瑜泰', '15229356823', '2018-05-09 18:42:56', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼31号', '钟楼店长', '15236356991', NULL, '1', '1天1小时12分40秒', 2, '2018-05-11 11:48:33', '钟楼店长', '15236356991', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-09 18:42:56', NULL, '实名制（过塑）,摇摇', '3,4', '13545675856', '张四', '2018-05-10 10:35:53', '2018-05-11 11:48:33', 108.9530983, 34.2777999, 'A00262', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180509184256967', '殷瑜泰', '15229356823', '2018-05-09 18:42:56', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼28号', '钟楼店长', '15236356988', NULL, '1', NULL, 2, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-09 18:42:56', NULL, '实名制（过塑）,摇摇', '3,4', '13259710305', '张四', NULL, NULL, 108.9530983, 34.2777999, 'A00259', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180509184442221', '殷瑜泰', '15229356823', '2018-05-09 18:44:43', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼31号', '钟楼店长', '15236356991', NULL, '1', '2018-05-11 22:03:17', 2, '2018-05-11 22:03:17', '钟楼店长', '15236356991', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-09 18:44:43', NULL, '实名制（过塑）', '1', '13259710305', '张四', NULL, '2018-05-11 22:03:17', 108.9530983, 34.2777999, 'A00262', '', '');
INSERT INTO `mkl_mail_info` VALUES ('20180509184442642', '殷瑜泰', '15229356823', '2018-05-09 18:44:44', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼27号', '钟楼店长', '15236356987', NULL, '1', '', 4, '2018-05-11 12:11:32', '????', '15236356987', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-09 18:44:44', NULL, '实名制（过塑）', '1', '13259710305', '张四', '2018-06-15 11:10:30', '2018-05-11 12:11:32', 108.9530983, 34.2777999, 'A00258', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180510094043245', '殷瑜泰', '15229356823', '2018-05-10 09:40:43', '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, '2', '', 2, '2018-05-11 12:04:43', '???', '18792462923', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-10 09:40:43', NULL, '实名制（过塑）,摇摇', '4,2', '15091637106', '张芝', NULL, '2018-05-11 12:04:43', 108.9530983, 34.2777999, 'A00001', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180510204800825', '殷瑜泰', '15229356823', '2018-05-10 20:48:00', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '1', NULL, 4, '2018-05-11 11:46:29', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-05-10 20:48:00', NULL, '实名制（过塑）', '1', '13545675856', '王丰', '2018-05-16 19:21:56', '2018-05-11 11:46:29', 108.9530983, 34.2777999, 'AR7858', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180511103540233', '殷瑜泰', '15229356823', '2018-05-11 10:35:40', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼27号', '钟楼店长', '15236356987', NULL, '1', '1小时2分49秒', 4, '2018-05-11 11:40:32', '钟楼店长', '15236356987', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-11 10:35:40', NULL, '实名制（过塑）', '1', '13545675856', '王丰', '2018-05-16 19:21:45', '2018-05-11 11:40:32', 108.9530983, 34.2777999, 'A00258', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180511103540337', '殷瑜泰', '15229356823', '2018-05-11 10:35:40', '435', NULL, NULL, NULL, NULL, '钟楼店', '钟楼28号', '钟楼店长', '15236356988', NULL, '1', '', 4, '2018-05-11 11:58:47', 'undefined', 'undefined', '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-05-11 10:35:40', NULL, '实名制（过塑）', '1', '13545675856', '王丰', '2018-05-19 11:52:12', '2018-05-11 11:58:47', 108.9530983, 34.2777999, 'A00259', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180516193915012', '殷瑜泰', '15229356823', '2018-05-16 19:39:15', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '1', '2018-06-15 09:12:17', 6, '2018-06-15 09:12:17', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-05-16 19:39:15', NULL, '实名制（过塑）', '2', '13545675856', '王丰', '2018-05-17 10:33:28', '2018-06-15 09:12:17', 108.9229960, 34.1901140, 'AR7858', 'http://112.74.88.25/logistics/upload/48de3e74-28b2-4fea-8377-9cb0a0c95cd8.jpg', 'http://112.74.88.25/logistics/upload/d6e826fe-7d66-41c8-b7d4-24acf18cab80.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180614102005869', '殷瑜泰', '15229356823', '2018-06-14 10:20:05', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '1', '', 6, '2018-06-14 10:21:43', '???', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-06-14 10:20:05', NULL, '实名制（过塑）', '1', '13545675856', '王丰', '2018-06-14 10:20:39', '2018-06-14 10:21:43', 108.9229960, 34.1902040, 'AR7858', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180614102230808', '殷瑜泰', '15229356823', '2018-06-14 10:22:30', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '2', '2018-06-15 10:30:22', 6, '2018-06-15 10:30:22', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-06-14 10:22:30', NULL, '单页', '1', '13545675856', '王丰', '2018-06-15 10:29:56', '2018-06-15 10:30:22', 108.9305350, 34.2934780, 'AR7858', 'http://112.74.88.25/logistics/upload/c63fec52-4e58-4b8b-a90b-cd4d0243d7a9.jpg', 'http://112.74.88.25/logistics/upload/f7d27139-6b1f-4eed-b2d6-7bb23260383a.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180614104756192', '殷瑜泰', '15229356823', NULL, '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, NULL, NULL, 0, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-06-14 00:00:00', NULL, '实名制（过塑）,单页,,实名制（过塑）,单页', '10,10,,10,10', NULL, NULL, NULL, NULL, 108.9229960, 34.1902040, 'A00001', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180614105127865', '殷瑜泰', '15229356823', NULL, '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, NULL, NULL, 0, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-06-14 00:00:00', NULL, '实名制（过塑）,,实名制（过塑）', '3,,3', NULL, NULL, NULL, NULL, 108.9229960, 34.1902040, 'A00001', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180614105133863', '殷瑜泰', '15229356823', NULL, '435', NULL, NULL, NULL, NULL, '电子城OPPO直营店', '南三环', '王磊岐', '18792462923', NULL, NULL, NULL, 0, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, 'OPPO', '2018-06-14 00:00:00', NULL, '实名制（过塑）,摇摇,,实名制（过塑）,摇摇', '10,10,,10,10', '13545675856', NULL, NULL, NULL, 108.9229960, 34.1902040, 'A00001', NULL, NULL);
INSERT INTO `mkl_mail_info` VALUES ('20180614212026650', '殷瑜泰', '15229356823', '2018-06-14 21:20:26', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '1', '17秒', 6, '2018-06-15 11:01:30', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-06-14 21:20:26', NULL, '实名制（过塑）', '1', '13545675856', '王丰', '2018-06-15 11:01:13', '2018-06-15 11:01:30', 108.9229960, 34.1902040, 'AR7858', 'http://112.74.88.25/logistics/upload/7557a003-ddd6-4b2a-9e51-018cfb60ee7d.jpg', 'http://112.74.88.25/logistics/upload/bd8c29b5-1d1d-4b98-b786-05d17432132c.jpg');
INSERT INTO `mkl_mail_info` VALUES ('20180615110317796', '殷瑜泰', '15229356823', '2018-06-15 11:03:17', '435', NULL, NULL, NULL, NULL, '中铁尚都城VIVO社会门店', '中铁尚都城', '殷瑜泰', '15269863698', NULL, '1', NULL, 4, '2018-06-19 09:49:37', '殷瑜泰', '15269863698', '0', NULL, NULL, NULL, '0', NULL, NULL, 'VIVO', '2018-06-15 11:03:17', NULL, '实名制（过塑）,海报,单页,桌卡,摇摇,工单,空卡,副卡,', '20.0,0,0,0,0,0,0,0,', '13545675856', '王丰', '2018-06-20 12:03:59', NULL, 108.9305350, 34.2934780, 'AR7858', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mkl_mail_report_temp
-- ----------------------------
DROP TABLE IF EXISTS `mkl_mail_report_temp`;
CREATE TABLE `mkl_mail_report_temp` (
  `channel_code` varchar(20) DEFAULT NULL,
  `store` varchar(100) NOT NULL,
  `state` int(2) DEFAULT NULL,
  `materialName` varchar(50) DEFAULT NULL,
  `materialNumber` varchar(100) DEFAULT NULL,
  `createUserStr` char(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mkl_material_info
-- ----------------------------
DROP TABLE IF EXISTS `mkl_material_info`;
CREATE TABLE `mkl_material_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_name` varchar(255) DEFAULT NULL COMMENT '物质名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_phone` varchar(255) DEFAULT NULL COMMENT '创建人电话',
  `delete_flag` int(2) DEFAULT '0' COMMENT '数据状态（0正常，1删除）',
  `code` char(8) DEFAULT NULL COMMENT '物资编码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='物质表';

-- ----------------------------
-- Records of mkl_material_info
-- ----------------------------
BEGIN;
INSERT INTO `mkl_material_info` VALUES (20, '实名制（过塑）', '2017-04-13 19:16:06', 'root', 0, 'GQRU7Z47');
INSERT INTO `mkl_material_info` VALUES (19, '号卡', '2017-04-13 19:15:32', 'root', 0, 'GPWZE4CQ');
INSERT INTO `mkl_material_info` VALUES (21, '海报', '2017-04-13 19:16:13', 'root', 0, 'UZ4SR0XL');
INSERT INTO `mkl_material_info` VALUES (22, '单页', '2017-04-13 19:16:22', 'root', 0, 'BMUUKV4J');
INSERT INTO `mkl_material_info` VALUES (23, '桌卡', '2017-04-13 19:16:29', 'root', 0, 'JF4IOGXN');
INSERT INTO `mkl_material_info` VALUES (24, '摇摇', '2017-04-13 19:16:38', 'root', 0, 'P6GHGAXK');
INSERT INTO `mkl_material_info` VALUES (25, '工单', '2017-04-13 19:16:49', 'root', 0, 'GZNT04VM');
INSERT INTO `mkl_material_info` VALUES (26, '空卡', '2017-04-13 19:17:05', 'root', 0, 'U1GN3YTQ');
INSERT INTO `mkl_material_info` VALUES (27, '副卡', '2017-04-13 19:17:20', 'root', 1, 'GCTUOV8Y');
INSERT INTO `mkl_material_info` VALUES (29, '手机', '2017-04-18 15:23:17', '15229356823', 1, '5ABRLFYE');
INSERT INTO `mkl_material_info` VALUES (30, '手机', '2017-04-18 15:40:16', '15229356823', 1, 'CX5F2MIF');
INSERT INTO `mkl_material_info` VALUES (31, '赠品', '2017-11-21 16:27:16', 'root', 1, 'CL02JR11');
INSERT INTO `mkl_material_info` VALUES (32, '赠品', '2017-11-21 16:27:26', 'root', 0, 'IMZ1QT2C');
COMMIT;

-- ----------------------------
-- Table structure for mkl_postman_operation
-- ----------------------------
DROP TABLE IF EXISTS `mkl_postman_operation`;
CREATE TABLE `mkl_postman_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` char(32) DEFAULT NULL COMMENT '订单号',
  `postman_phone` char(11) DEFAULT NULL COMMENT '快递员电话',
  `order_state` int(2) DEFAULT NULL COMMENT '订单状态（0接受，1拒绝）',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `delete_flag` int(2) DEFAULT '0' COMMENT '数据状态（0，正常，1删除）',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='快递员操作记录';

-- ----------------------------
-- Table structure for mkl_store_demand
-- ----------------------------
DROP TABLE IF EXISTS `mkl_store_demand`;
CREATE TABLE `mkl_store_demand` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `store_name` varchar(255) DEFAULT NULL COMMENT '门店名称',
  `channel_code` varchar(255) DEFAULT NULL COMMENT '门店渠道编码',
  `store_shopowner_phone` varchar(255) DEFAULT NULL COMMENT '上报人电话',
  `store_shopowner_name` varchar(255) DEFAULT NULL COMMENT '上报人姓名',
  `materialContent` varchar(255) DEFAULT NULL COMMENT '物资名称',
  `materialNumber` varchar(255) DEFAULT NULL COMMENT '物资数量',
  `isdelete` int(1) DEFAULT '0' COMMENT '是否删除',
  `report_time` datetime DEFAULT NULL COMMENT '上报时间',
  `examine_one_time` datetime DEFAULT NULL COMMENT '一级审核时间',
  `examine_time` datetime DEFAULT NULL COMMENT '二级审核时间',
  `examine_one_name` varchar(255) DEFAULT NULL COMMENT '一级审核人姓名',
  `examine_one_phone` varchar(255) DEFAULT NULL COMMENT '一级审核人电话',
  `examine_two_name` varchar(255) DEFAULT NULL COMMENT '二级审核人姓名',
  `examine_two_phone` varchar(255) DEFAULT NULL COMMENT '二级审核人电话',
  `expanding_demand` varchar(255) DEFAULT NULL COMMENT '扩展需求',
  `examine` int(1) DEFAULT '0' COMMENT '审核标示，0未审核，1二级审核通过，2二级审核驳回,3一级审核通过，4一级审核驳回',
  `examine_reason` varchar(255) DEFAULT NULL COMMENT '二级审核驳回原因',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mkl_store_demand
-- ----------------------------
BEGIN;
INSERT INTO `mkl_store_demand` VALUES (73, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,号卡,海报,单页,桌卡', '10,10,10,10,10', 0, '2017-11-22 18:09:33', '2017-11-22 18:10:44', '2017-11-22 18:11:07', 'root', 'root', 'root', 'root', '', 1, '物资不够');
INSERT INTO `mkl_store_demand` VALUES (74, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '赠品,空卡', '50,50', 0, '2017-11-22 18:09:56', '2017-11-22 18:10:26', '2017-11-22 18:11:01', 'root', 'root', 'root', 'root', '', 2, '不符合需求');
INSERT INTO `mkl_store_demand` VALUES (75, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '海报,单页', '10,10', 0, '2018-04-09 13:55:17', NULL, NULL, NULL, NULL, NULL, NULL, '', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (76, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,号卡', '10,10', 0, '2018-04-19 14:53:19', NULL, NULL, NULL, NULL, NULL, NULL, '', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (77, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '号卡', '10', 0, '2018-04-19 14:58:38', NULL, NULL, NULL, NULL, NULL, NULL, '', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (79, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,号卡,海报', '10,10,10', 0, '2018-05-02 16:38:16', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (80, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,桌卡', '10,10', 0, '2018-05-04 10:57:39', NULL, NULL, NULL, NULL, NULL, NULL, '', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (81, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,摇摇,,实名制（过塑）,摇摇', '10,10,,10,10', 0, '2018-05-09 21:44:13', '2018-06-14 10:49:16', '2018-06-14 10:51:33', '雷婷', '15609208156', '殷瑜泰', '15229356823', '暂无', 1, '测试');
INSERT INTO `mkl_store_demand` VALUES (82, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,,实名制（过塑）', '3,,3', 0, '2018-05-11 21:58:50', '2018-06-14 10:49:06', '2018-06-14 10:51:26', '雷婷', '15609208156', '殷瑜泰', '15229356823', '', 1, NULL);
INSERT INTO `mkl_store_demand` VALUES (83, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,单页,,实名制（过塑）,单页', '10,10,,10,10', 0, '2018-06-14 10:31:25', NULL, '2018-06-14 10:47:56', NULL, NULL, '殷瑜泰', '15229356823', '测试', 1, NULL);
INSERT INTO `mkl_store_demand` VALUES (84, '马卡鲁测试', 'A00001', '18792462923', '王磊岐', '实名制（过塑）', '1', 0, '2018-06-14 10:44:45', NULL, '2018-06-14 10:48:07', NULL, NULL, '殷瑜泰', '15229356823', '测试', 2, '测试');
INSERT INTO `mkl_store_demand` VALUES (85, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '摇摇,,摇摇', '1,,1', 0, '2018-06-14 16:15:02', NULL, NULL, NULL, NULL, NULL, NULL, '测试', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (86, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,,实名制（过塑）', '12,,12', 0, '2018-06-19 14:35:03', NULL, NULL, NULL, NULL, NULL, NULL, '测试', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (87, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '海报,', '2,', 0, '2018-06-19 14:39:52', NULL, NULL, NULL, NULL, NULL, NULL, '测试', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (88, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '实名制（过塑）,', '12,', 0, '2018-06-19 14:40:42', NULL, NULL, NULL, NULL, NULL, NULL, '测试', 0, NULL);
INSERT INTO `mkl_store_demand` VALUES (89, '电子城OPPO直营店', 'A00001', '18792462923', '王磊岐', '海报,桌卡,工单,', '12,12,12,', 0, '2018-06-19 14:41:33', NULL, NULL, NULL, NULL, NULL, NULL, '测试', 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for mkl_store_info
-- ----------------------------
DROP TABLE IF EXISTS `mkl_store_info`;
CREATE TABLE `mkl_store_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(255) DEFAULT NULL COMMENT '门店名称',
  `store_address` varchar(255) DEFAULT NULL COMMENT '门店地址（收货地址）',
  `store_address_code` varchar(16) DEFAULT NULL COMMENT '门店地址马卡鲁内部编码（冗余）',
  `store_longitude` double(10,7) DEFAULT NULL COMMENT '门店经度',
  `store_latitude` double(10,7) DEFAULT NULL COMMENT '门店纬度',
  `store_img` varchar(255) DEFAULT NULL COMMENT '门店照片（门头）',
  `store_shopowner_phone` char(11) DEFAULT NULL COMMENT '门店店长电话（收货人电话）',
  `store_shopowner_name` varchar(255) DEFAULT NULL COMMENT '门店店长姓名（收货人姓名）',
  `store_number` varchar(16) DEFAULT NULL COMMENT '门店编码（马卡鲁内部编码冗余）',
  `channel_code` varchar(16) DEFAULT NULL COMMENT '门店渠道编码',
  `vendor_id` int(11) DEFAULT NULL COMMENT '门店信息',
  `type` int(2) DEFAULT '0' COMMENT '门店类型(0表示直营,1表示现金也就是社会门店)',
  `create_phone` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=264 DEFAULT CHARSET=utf8 COMMENT='门店表';

-- ----------------------------
-- Records of mkl_store_info
-- ----------------------------
BEGIN;
INSERT INTO `mkl_store_info` VALUES (41, '电子城OPPO直营店', '南三环', NULL, 108.9229960, 34.1902040, 'http://112.74.88.25/upload/store_title/149239706460301174b3b-e577-4aa9-af83-df3130dc4592.jpg', '18792462923', '王磊岐', NULL, 'A00001', 1, 0, NULL, NULL);
INSERT INTO `mkl_store_info` VALUES (123, '中铁尚都城VIVO社会门店', '中铁尚都城', NULL, 108.9229960, 34.1902040, 'http://112.74.88.25/upload/store_title/14924234138628e22cc79-fb52-4334-af67-b3233b182cfc.png', '15269863698', '殷瑜泰', NULL, 'AR7858', 2, 1, '15229356823', '2017-04-17 11:04:27');
INSERT INTO `mkl_store_info` VALUES (162, '大雁塔店', '大雁塔', NULL, 108.9530983, 34.2777999, 'http://112.74.88.25/upload/store_title/15113383418758016ee9a-16da-487a-9a57-c43bbc0967b6.jpg', '15236356988', '大雁塔店长', NULL, 'A00257', 2, 0, '15229356823', '2017-04-18 15:46:19');
INSERT INTO `mkl_store_info` VALUES (235, '钟楼店', '钟楼31号', NULL, 108.9530983, 34.2777999, NULL, '15236356991', '钟楼店长', NULL, 'A00262', 1, 1, '15229356823', '2017-04-26 14:11:59');
INSERT INTO `mkl_store_info` VALUES (237, '钟楼店', '钟楼26号', NULL, 108.9530983, 34.2777999, NULL, '15236356986', '钟楼店长', NULL, 'A00212', 2, 1, '15229356823', '2017-04-26 14:11:59');
INSERT INTO `mkl_store_info` VALUES (238, '钟楼店', '钟楼27号', NULL, 108.9530983, 34.2777999, NULL, '15236356987', '钟楼店长', NULL, 'A00258', 1, 1, '15229356823', '2017-04-26 14:11:59');
INSERT INTO `mkl_store_info` VALUES (239, '钟楼店', '钟楼29号', NULL, 108.9530983, 34.2777999, NULL, '15236356989', '钟楼店长', NULL, 'A00260', 1, 1, '15229356823', '2017-04-26 14:11:59');
INSERT INTO `mkl_store_info` VALUES (240, '钟楼店', '钟楼28号', NULL, 108.9530983, 34.2777999, NULL, '15236356988', '钟楼店长', NULL, 'A00259', 1, 1, '15229356823', '2017-04-26 14:11:59');
INSERT INTO `mkl_store_info` VALUES (241, '钟楼店', '钟楼25号', NULL, 108.9530983, 34.2777999, NULL, '15236356985', '钟楼店长', NULL, 'A00230', 1, 1, '15229356823', '2017-04-26 14:11:59');
INSERT INTO `mkl_store_info` VALUES (242, '联盟新城店', '雁塔区雁环中路联盟新城', NULL, NULL, NULL, NULL, '18792462923', '王磊岐', NULL, 'A00002', 2, 0, 'root', '2017-11-21 16:30:28');
INSERT INTO `mkl_store_info` VALUES (245, '测试门店', '雁塔区雁环中路中铁尚都城', NULL, NULL, NULL, NULL, '15091637103', '张芝', NULL, 'A00003', 1, 0, 'root', '2018-06-20 15:36:52');
INSERT INTO `mkl_store_info` VALUES (258, '新渠道拓展：西安品智通讯器材有限责任公司', '雁塔南路赛格电脑城一楼B1045', NULL, 108.9633030, 34.2305110, NULL, '18229056666', '薛明', NULL, 'A0ALP028', 1, 1, 'root', '2018-06-20 18:18:28');
INSERT INTO `mkl_store_info` VALUES (259, '新渠道拓展：西安宁威通讯设备有限公司', '新民街1号', NULL, 122.7478330, 41.6850890, NULL, '13609248866', '陈正午', NULL, 'A0ALHI46', 1, 1, 'root', '2018-06-20 18:18:28');
INSERT INTO `mkl_store_info` VALUES (260, '西咸新区沣东新城子骏通讯器材店', '西安市沣东新城建章路南段', NULL, 108.8123630, 34.3024600, NULL, '13468836295', '翟羽佳西二', NULL, 'A0ALP034', 1, 1, 'root', '2018-06-20 18:19:10');
INSERT INTO `mkl_store_info` VALUES (261, '西咸新区沣东新城子骏通讯器材店(vivo城西客运站店)', '西安市枣园东路城西客运站门口向西vivo专卖', NULL, 108.8744200, 34.2740780, NULL, '15332402642', '张倩西三', NULL, 'A0ALH078', 1, 1, 'root', '2018-06-20 18:19:21');
INSERT INTO `mkl_store_info` VALUES (262, '西咸新区沣东新城永信通讯服务部', '西安市未央区三桥镇新军寨村口', NULL, 108.8224720, 34.3050690, NULL, '13324512315', '肖康', NULL, 'A0ALP052', 1, 1, 'root', '2018-06-20 18:19:42');
INSERT INTO `mkl_store_info` VALUES (263, '西安三苹米电子通讯产品有限公司(案板街店)', '西安市新城区案板街21号', NULL, 108.9501450, 34.2613600, NULL, '18009269888', '贾红斌', NULL, 'A0ALR001', 1, 1, 'root', '2018-06-20 18:19:58');
COMMIT;

-- ----------------------------
-- Table structure for mkl_store_info_temp
-- ----------------------------
DROP TABLE IF EXISTS `mkl_store_info_temp`;
CREATE TABLE `mkl_store_info_temp` (
  `store_name` varchar(255) DEFAULT NULL COMMENT '门店名称',
  `store_address` varchar(255) DEFAULT NULL COMMENT '门店地址（收货地址）',
  `store_address_code` varchar(16) DEFAULT NULL COMMENT '门店地址马卡鲁内部编码（冗余）',
  `store_longitude` double(10,7) DEFAULT NULL COMMENT '门店经度',
  `store_latitude` double(10,7) DEFAULT NULL COMMENT '门店纬度',
  `store_img` varchar(255) DEFAULT NULL COMMENT '门店照片（门头）',
  `store_shopowner_phone` char(11) DEFAULT NULL COMMENT '门店店长电话（收货人电话）',
  `store_shopowner_name` varchar(255) DEFAULT NULL COMMENT '门店店长姓名（收货人姓名）',
  `store_number` varchar(16) DEFAULT NULL COMMENT '门店编码（马卡鲁内部编码冗余）',
  `channel_code` varchar(16) DEFAULT NULL COMMENT '门店渠道编码',
  `vendor_id` int(11) DEFAULT NULL COMMENT '门店信息',
  `type` int(2) DEFAULT '0' COMMENT '门店类型(0表示直营,1表示现金也就是社会门店)',
  `createUser` varchar(255) DEFAULT NULL COMMENT '导入账户',
  `create_phone` varchar(255) DEFAULT NULL COMMENT '创建人信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='门店表';

-- ----------------------------
-- Table structure for mkl_vendor
-- ----------------------------
DROP TABLE IF EXISTS `mkl_vendor`;
CREATE TABLE `mkl_vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vendor_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_phone` varchar(255) DEFAULT NULL COMMENT '创建人电话',
  `delete_flag` int(2) DEFAULT '0' COMMENT '数据状态（0正常，1删除）',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='品牌表';

-- ----------------------------
-- Records of mkl_vendor
-- ----------------------------
BEGIN;
INSERT INTO `mkl_vendor` VALUES (1, 'OPPO', '2017-03-16 11:24:51', 'root', 0);
INSERT INTO `mkl_vendor` VALUES (2, 'VIVO', '2017-03-16 11:25:22', '15229356823', 0);
COMMIT;

-- ----------------------------
-- Table structure for myd_activity_info
-- ----------------------------
DROP TABLE IF EXISTS `myd_activity_info`;
CREATE TABLE `myd_activity_info` (
  `id` char(36) NOT NULL COMMENT '政策编号（PK）',
  `title` varchar(500) DEFAULT NULL COMMENT '政策标题',
  `content` text COMMENT '政策内容',
  `Start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `End_time` datetime DEFAULT NULL COMMENT '结束时间',
  `Is_handle` char(1) DEFAULT NULL COMMENT '是否允许办理(1代表可以办理 2代表不能办理)',
  `is_delete` char(1) DEFAULT 'N' COMMENT '删除标示',
  `create_user` char(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user` char(36) DEFAULT NULL COMMENT '修改人',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_user` char(36) DEFAULT NULL COMMENT '删除人',
  `description` text COMMENT '描述',
  `counts` bigint(20) DEFAULT NULL COMMENT '阅读次数',
  `groupId` int(11) DEFAULT NULL,
  `activity_type` varchar(2) DEFAULT NULL COMMENT '活动分类(A:套餐B:充值C:流量D:购机E:宽带)',
  `flag` char(1) DEFAULT 'A',
  `mold` char(1) DEFAULT 'A',
  `priority` varchar(255) DEFAULT '0' COMMENT '是否置顶',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='政策信息表';

-- ----------------------------
-- Records of myd_activity_info
-- ----------------------------
BEGIN;
INSERT INTO `myd_activity_info` VALUES ('2f5bf264-8b47-408b-9646-efae8ceeb1f8', '知识库模块', '<html>\n<head>\n<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n<style type=\"text/css\">.b1{white-space-collapsing:preserve;}\n.b2{margin: 1.0in 1.25in 1.0in 1.25in;}\n.p1{text-align:justify;hyphenate:auto;font-family:Times New Roman;font-size:10pt;}\n.p2{text-indent:-0.25in;margin-left:0.5416667in;text-align:justify;hyphenate:auto;font-family:Times New Roman;font-size:9pt;}\n.p3{text-indent:-0.25in;margin-left:0.5416667in;text-align:justify;hyphenate:auto;font-family:Times New Roman;font-size:10pt;}\n.p4{margin-left:0.29166666in;text-align:justify;hyphenate:auto;font-family:Times New Roman;font-size:9pt;}\n.s1{font-weight:bold;}\n.s2{display: inline-block; text-indent: 0; min-width: 0.19444445in;}\n.s3{font-size:10pt;font-weight:bold;}\n.s4{font-size:9pt;}\n.s5{font-weight:bold;color:#4472c4;}\n</style>\n<meta content=\"Administrator\" name=\"author\">\n</head>\n<body class=\"b1 b2\">\n<p class=\"p1\"></p>\n<p class=\"p1\">\n<span class=\"s1\">这里是知识库模块</span>\n</p>\n<p class=\"p2\">\n<span class=\"s2\">1​&nbsp;</span><span>这里可以写知识库的内容</span><span class=\"s3\">。</span>\n</p>\n<p class=\"p3\">\n<span class=\"s2\">2​&nbsp;</span><span class=\"s1\">	</span><span class=\"s4\">这里可以写知识库的内容</span><span class=\"s1\">。</span>\n</p>\n<p class=\"p3\">\n<span class=\"s2\">3​&nbsp;</span><span class=\"s1\">	</span><span class=\"s4\">这里可以写知识库的内容</span><span class=\"s1\">。</span>\n</p>\n<p class=\"p1\">\n<span class=\"s1\">模块一</span>\n</p>\n<p class=\"p4\">\n<span class=\"s5\">1.特殊内容可设置自定义颜色</span>\n</p>\n<p class=\"p4\">\n<span class=\"s1\">2.重点内容可加黑</span>\n</p>\n<p class=\"p4\">\n<span>3.图片可以正常发布：</span>\n</p>\n<p class=\"p4\">\n<img src=\"http://112.74.88.25/Activity/upload//4c9dc652-bf57-4f7e-bdec-8407d5d8a4a00.jpg\" style=\"width:1.0375in;height:1.0375in;vertical-align:text-bottom;\"></p>\n<p class=\"p1\"></p>\n</body>\n</html>\n', '2017-03-15 00:00:00', '2050-01-01 00:00:00', '1', 'N', '1', NULL, NULL, NULL, NULL, NULL, '知识库模块', 12, 434, 'A', 'A', 'C', '0');
INSERT INTO `myd_activity_info` VALUES ('706e22fc-a7a1-4fb7-9450-00f0b836d12a', '渭南渠道营销支撑平台上线啦！', '<html>\n<head>\n<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n<style type=\"text/css\">.b1{white-space-collapsing:preserve;}\n.b2{margin: 1.0in 1.25in 1.0in 1.25in;}\n.p1{text-align:justify;hyphenate:auto;font-family:Times New Roman;font-size:10pt;}\n.s1{color:red;}\n</style>\n<meta content=\"Administrator\" name=\"author\">\n</head>\n<body class=\"b1 b2\">\n<p class=\"p1\"></p>\n<p class=\"p1\">\n<img src=\"http://112.74.88.25/Activity/upload//135d8ed7-0a88-4b52-a43c-915bf41a98860.jpg\" style=\"width:1.6680555in;height:1.99375in;vertical-align:text-bottom;\"></p>\n<p class=\"p1\">\n<span class=\"s1\">渭南渠道营销支撑平台上线啦！</span>\n</p>\n</body>\n</html>\n', '2017-03-15 00:00:00', '2050-01-01 00:00:00', '1', 'N', '1', NULL, NULL, NULL, NULL, NULL, '渭南渠道营销支撑平台上线啦！', 16, 435, 'A', 'A', 'A', '0');
INSERT INTO `myd_activity_info` VALUES ('801f1476-faa8-4b96-87e7-83368c53e42a', '测试标题', '', '2017-11-21 00:00:00', '2050-01-01 00:00:00', '1', 'N', '1', NULL, NULL, NULL, NULL, NULL, '测试政策', 12, 435, 'A', 'A', 'A', '0');
INSERT INTO `myd_activity_info` VALUES ('9ce1fef5-ce41-4c37-bd71-5d4219173ae2', '视屏类型政策', '<p>视屏发布：</p><p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\"\r\n preload=\"none\" width=\"420\" height=\"280\" src=\"http://112.74.88.25/Activity/upload/1489561978987041651.mp4\" data-setup=\"{}\"></video></p>', '2017-03-15 00:00:00', '2050-01-01 00:00:00', '1', 'N', '1', NULL, NULL, NULL, NULL, NULL, '视屏类型政策', 26, 436, 'A', 'A', 'B', '0');
INSERT INTO `myd_activity_info` VALUES ('dd33bd84-10a5-499e-bd55-734e30799cc6', '123', '', '2017-04-21 00:00:00', '2050-01-01 00:00:00', '1', 'N', '1', NULL, NULL, NULL, NULL, NULL, '21', 42, 112, 'A', 'A', 'A', '0');
COMMIT;

-- ----------------------------
-- Table structure for myd_activity_read
-- ----------------------------
DROP TABLE IF EXISTS `myd_activity_read`;
CREATE TABLE `myd_activity_read` (
  `id` char(36) NOT NULL,
  `Read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `Policy_id` char(36) DEFAULT NULL COMMENT '阅读政策(FK）',
  `Read_user` char(36) DEFAULT NULL COMMENT '阅读人',
  `is_delete` char(1) DEFAULT 'N' COMMENT '删除标示',
  `create_user` char(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user` char(36) DEFAULT NULL COMMENT '修改人',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_user` char(36) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='政策阅读表';

-- ----------------------------
-- Records of myd_activity_read
-- ----------------------------
BEGIN;
INSERT INTO `myd_activity_read` VALUES ('414e71c1-0950-11e7-bb7e-d89d672b4344', '2017-03-15 15:23:02', '9ce1fef5-ce41-4c37-bd71-5d4219173ae2', '1', 'N', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('43bfcd73-93ef-4c22-9671-c464c4375f02', '2017-11-22 18:10:04', 'dd33bd84-10a5-499e-bd55-734e30799cc6', '14780', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('44d7763f-6b66-40cb-a936-8a089820dfed', '2017-04-14 11:42:46', '2f5bf264-8b47-408b-9646-efae8ceeb1f8', '14588', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('522bbd11-c1e7-44b0-ae25-aa10dd6b637f', '2017-03-15 15:26:35', '9ce1fef5-ce41-4c37-bd71-5d4219173ae2', '14780', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('56cb89b0-1dad-44d2-883a-b16b8765cc9d', '2017-03-15 14:39:52', '2f5bf264-8b47-408b-9646-efae8ceeb1f8', '1', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('57119059-983b-4cef-892b-8e9f62a82ef6', '2017-03-15 15:34:18', '706e22fc-a7a1-4fb7-9450-00f0b836d12a', '14780', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('5e6d8463-d544-4a00-97ed-213b48f02558', '2017-11-21 15:52:36', 'dd33bd84-10a5-499e-bd55-734e30799cc6', '14985', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('879df880-038b-4fbc-984d-4b9235e2ab13', '2017-04-21 14:43:22', 'dd33bd84-10a5-499e-bd55-734e30799cc6', '14588', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('91441601-7064-49d0-87ea-7963e37e7f1e', '2017-04-14 11:43:07', '706e22fc-a7a1-4fb7-9450-00f0b836d12a', '14797', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('9588f494-9e53-4a82-ba79-d614a18a04b9', '2017-03-15 14:33:42', '706e22fc-a7a1-4fb7-9450-00f0b836d12a', '1', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('a053e8ca-9b7b-4a36-815f-e2499744f1c4', '2017-11-22 18:20:39', '2f5bf264-8b47-408b-9646-efae8ceeb1f8', '15083', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('a0aaa784-51f5-4973-bb12-9ee243caef88', '2017-03-15 15:34:27', '2f5bf264-8b47-408b-9646-efae8ceeb1f8', '14797', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('a4084e7d-f831-469b-aa54-b03184b37c18', '2017-11-22 18:19:36', '801f1476-faa8-4b96-87e7-83368c53e42a', '14797', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('ed8f4622-2ff9-4160-9f77-02b7f92c9634', '2017-04-21 14:40:07', 'dd33bd84-10a5-499e-bd55-734e30799cc6', '14797', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `myd_activity_read` VALUES ('f2bb9600-cd7f-432f-97e8-faec3a8e4fd3', '2017-11-22 17:01:01', 'dd33bd84-10a5-499e-bd55-734e30799cc6', '15083', 'N', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for server_info
-- ----------------------------
DROP TABLE IF EXISTS `server_info`;
CREATE TABLE `server_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `cpuUsage` varchar(255) DEFAULT NULL,
  `setCpuUsage` varchar(255) DEFAULT NULL,
  `jvmUsage` varchar(255) DEFAULT NULL,
  `setJvmUsage` varchar(255) DEFAULT NULL,
  `ramUsage` varchar(255) DEFAULT NULL,
  `setRamUsage` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `mark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_acc_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_acc_role`;
CREATE TABLE `sys_acc_role` (
  `acc_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`acc_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_acc_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_acc_role` VALUES (1, 1);
INSERT INTO `sys_acc_role` VALUES (14797, 17);
INSERT INTO `sys_acc_role` VALUES (14986, 18);
COMMIT;

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `manager_id` bigint(20) DEFAULT NULL COMMENT '归属者ID',
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `accountType` varchar(20) DEFAULT NULL COMMENT '用户类型（1门店，2快递员，3营销中心）',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `state` varchar(3) DEFAULT NULL COMMENT '是否可用',
  `createTime` datetime DEFAULT NULL,
  `deletestatus` int(1) DEFAULT '0' COMMENT '逻辑删除状态0:存在1:删除',
  `groupId` int(11) DEFAULT NULL COMMENT '组织架构ID',
  `token` char(36) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL COMMENT '片区',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `id_car` char(18) DEFAULT NULL COMMENT '身份证',
  `code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `section_name` varchar(100) DEFAULT NULL COMMENT '渠道名称',
  `employeesType` int(2) DEFAULT '0' COMMENT '0为内部店员，1为外部店员',
  `employeesClass` int(2) DEFAULT '0' COMMENT '必填（0：营业人员；1：客户经理；2：代理商；3：社区直销；4：校园直销；5：其他；6：客服人员；7：地市客服人员）',
  `employeesNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工编码',
  `dianyuanNum` varchar(20) DEFAULT NULL COMMENT '店员编码',
  `auth_flag` int(11) DEFAULT '1' COMMENT '审核标示(1 未审核 2 成功 3 失败)',
  `user_flag` int(11) DEFAULT '0' COMMENT '用户标示(1 普通用 ,2 代理商)注册用户默认为0',
  `longitude` varchar(20) DEFAULT '0' COMMENT '经度',
  `latitude` varchar(20) DEFAULT '0' COMMENT '纬度',
  `auth_reason` varchar(100) DEFAULT NULL COMMENT '审核通过提示',
  `city` varchar(255) DEFAULT NULL COMMENT '地市（冗余）',
  `company` char(32) DEFAULT NULL COMMENT '归属ID（当accountType为2时）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
BEGIN;
INSERT INTO `sys_account` VALUES (NULL, 1, 'root', '4QrcOUm6Wau+VuBX8g+IPg==', '0', '根账号', '1', '2014-04-15 16:17:03', 0, 1, 'nmeDqMq85OA7tyUsCwzKimUHJCSMPaMbV0Lm', '根账号', 'root', 'X', NULL, '根账号', 0, 0, NULL, NULL, 2, 0, '0', '0', NULL, '西安', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code` (
  `deviceId` varchar(32) NOT NULL,
  `code` varchar(4) NOT NULL,
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`deviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL COMMENT '父级',
  `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `remove` char(1) DEFAULT 'N' COMMENT '删除标示',
  `removeTime` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=utf8 COMMENT='组织架构';

-- ----------------------------
-- Records of sys_group
-- ----------------------------
BEGIN;
INSERT INTO `sys_group` VALUES (1, 0, '超级管理', '超级管理', '2015-10-23 15:26:51', '0000-00-00 00:00:00', 'N', '0000-00-00 00:00:00', 0);
INSERT INTO `sys_group` VALUES (106, 1, '陕西省', '陕西省', '2016-07-03 20:44:11', '0000-00-00 00:00:00', 'N', '0000-00-00 00:00:00', 1);
INSERT INTO `sys_group` VALUES (112, 106, '西安', '西安', '2016-07-03 20:45:36', '0000-00-00 00:00:00', 'N', '0000-00-00 00:00:00', 2);
INSERT INTO `sys_group` VALUES (434, 112, '快递员', '快递员', '2017-03-20 15:15:02', '0000-00-00 00:00:00', 'N', '0000-00-00 00:00:00', 6);
INSERT INTO `sys_group` VALUES (435, 112, '营销中心', '营销中心', '2017-03-23 10:29:35', NULL, 'N', '0000-00-00 00:00:00', 3);
INSERT INTO `sys_group` VALUES (436, 112, '门店', '门店', '2017-03-23 10:29:45', NULL, 'N', '0000-00-00 00:00:00', 3);
INSERT INTO `sys_group` VALUES (437, 112, '渠道经理', '渠道经理', '2017-04-19 14:07:06', NULL, 'N', '0000-00-00 00:00:00', 4);
COMMIT;

-- ----------------------------
-- Table structure for sys_group_resc
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_resc`;
CREATE TABLE `sys_group_resc` (
  `group_id` int(11) NOT NULL,
  `resc_id` int(11) NOT NULL,
  PRIMARY KEY (`resc_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_group_resc
-- ----------------------------
BEGIN;
INSERT INTO `sys_group_resc` VALUES (1, 1);
INSERT INTO `sys_group_resc` VALUES (1, 2);
INSERT INTO `sys_group_resc` VALUES (1, 3);
INSERT INTO `sys_group_resc` VALUES (1, 4);
INSERT INTO `sys_group_resc` VALUES (1, 5);
INSERT INTO `sys_group_resc` VALUES (1, 6);
INSERT INTO `sys_group_resc` VALUES (1, 7);
INSERT INTO `sys_group_resc` VALUES (1, 8);
INSERT INTO `sys_group_resc` VALUES (1, 9);
INSERT INTO `sys_group_resc` VALUES (1, 10);
INSERT INTO `sys_group_resc` VALUES (1, 11);
INSERT INTO `sys_group_resc` VALUES (1, 12);
INSERT INTO `sys_group_resc` VALUES (1, 13);
INSERT INTO `sys_group_resc` VALUES (1, 14);
INSERT INTO `sys_group_resc` VALUES (1, 15);
INSERT INTO `sys_group_resc` VALUES (1, 16);
INSERT INTO `sys_group_resc` VALUES (1, 17);
INSERT INTO `sys_group_resc` VALUES (1, 18);
INSERT INTO `sys_group_resc` VALUES (1, 19);
INSERT INTO `sys_group_resc` VALUES (1, 20);
INSERT INTO `sys_group_resc` VALUES (1, 21);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `module` varchar(30) DEFAULT NULL,
  `action` varchar(30) DEFAULT NULL,
  `actionTime` varchar(30) DEFAULT NULL,
  `userIP` varchar(30) DEFAULT NULL,
  `operTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_login`;
CREATE TABLE `sys_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(255) DEFAULT NULL COMMENT '登录用户(手机号)',
  `loginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `loginIP` varchar(255) DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for sys_res_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_res_roles`;
CREATE TABLE `sys_res_roles` (
  `role_id` int(11) NOT NULL,
  `resc_id` int(11) NOT NULL,
  PRIMARY KEY (`resc_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_res_roles
-- ----------------------------
BEGIN;
INSERT INTO `sys_res_roles` VALUES (1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `resKey` varchar(50) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `resUrl` varchar(200) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
BEGIN;
INSERT INTO `sys_resources` VALUES (1, '系统基础管理', 0, 'system', '0', 'system', 0, '系统基础管理');
INSERT INTO `sys_resources` VALUES (2, '营销中心管理', 1, 'account', '1', 'background/account/query.html', 1, '账号管理界面是');
INSERT INTO `sys_resources` VALUES (3, '角色管理', 1, 'role', '1', 'background/role/query.html', 5, '角色管理');
INSERT INTO `sys_resources` VALUES (4, '菜单管理', 1, 'resources', '1', 'background/resources/resources.html', 10, '菜单查询');
INSERT INTO `sys_resources` VALUES (5, '新增账号', 2, 'account_add', '2', 'background/account/addUI.html', 2, '新增账号');
INSERT INTO `sys_resources` VALUES (6, '修改账号', 2, 'account_edit', '2', 'background/account/editUI.html', 3, '修改账号');
INSERT INTO `sys_resources` VALUES (7, '删除账号', 2, 'account_delete', '2', 'background/account/deleteById.html', NULL, '删除账号');
INSERT INTO `sys_resources` VALUES (8, '新增角色', 3, 'role_add', '2', 'background/role/addUI.html', 6, '部门/科组的新增');
INSERT INTO `sys_resources` VALUES (9, '修改角色', 3, 'role_edit', '2', 'background/role/editUI.html', 7, '部门/科组的修改');
INSERT INTO `sys_resources` VALUES (10, '删除角色', 3, 'role_delete', '2', 'background/role/delete.html', 8, '部门/科组的删除');
INSERT INTO `sys_resources` VALUES (11, '角色授权', 3, 'role_perss', '2', 'background/menu/permissions.html', 9, '菜单授权');
INSERT INTO `sys_resources` VALUES (12, '新增菜单资源', 4, 'resources_add', '2', 'background/resources/addUI.html', 11, '新增菜单资源');
INSERT INTO `sys_resources` VALUES (13, '修改菜单资源', 4, 'resources_edit', '2', '/background/resources/editUI.html', 12, '修改菜单资源');
INSERT INTO `sys_resources` VALUES (14, '删除菜单资源', 4, 'resources_delete', '2', '/background/resources/delete.html', 13, '删除菜单资源');
INSERT INTO `sys_resources` VALUES (16, '新增组织架构', 44, 'group_addgroup', '2', '/background/group/addGroup.html', 15, '新增组织架构');
INSERT INTO `sys_resources` VALUES (17, '添加组织机构', 44, 'group_insert', '2', '/background/group/add.html', 15, '添加组织机构');
INSERT INTO `sys_resources` VALUES (18, '跳转修改组织机构', 44, 'group_edit', '2', '/background/group/editUI.html', 16, '跳转修改组织机构');
INSERT INTO `sys_resources` VALUES (19, '修改组织机构', 44, 'group_editadd', '2', '/background/group/edit.html', 17, '修改组织机构');
INSERT INTO `sys_resources` VALUES (20, '删除组织架构', 44, 'group_delete', '1', '/background/group/delete.html', 18, '删除组织架构');
INSERT INTO `sys_resources` VALUES (21, '修改密码', 2, 'account_modifypassword', '2', '/background/account/modifyPassword.html', 5, '修改密码');
INSERT INTO `sys_resources` VALUES (44, '组织架构管理', 1, 'commany', '1', 'background/group/list.html', 14, '组织架构管理');
INSERT INTO `sys_resources` VALUES (109, '物流管理', 0, '物流管理', '0', '物流管理', 1, '物流管理');
INSERT INTO `sys_resources` VALUES (111, '微信端管理', 0, 'wx', '0', 'wx', 3, '微信端管理');
INSERT INTO `sys_resources` VALUES (112, '微信菜单管理', 111, 'wxmenu', '1', '/app/wxmenu.html', 1, '微信菜单管理');
INSERT INTO `sys_resources` VALUES (113, '微信端菜单权限管理', 111, 'wxmenushare', '1', '/app/wxmenu/jumpWXmenuShior.html', 2, '微信端菜单权限管理');
INSERT INTO `sys_resources` VALUES (114, '政策发布', 1, 'zhengcefabu', '1', '/background/activity/list.html', 1, 'zhengcefabu');
INSERT INTO `sys_resources` VALUES (115, '政策阅读', 1, 'read', '1', '/background/activity/read.html', 1, 'read');
INSERT INTO `sys_resources` VALUES (116, '物流订单列表', 109, 'Order_list', '2', '/background/order/list.html', 2, '订单列表');
INSERT INTO `sys_resources` VALUES (117, '门店管理', 109, 'store', '2', '/background/Stroe/storeList.html', 1, 'store');
INSERT INTO `sys_resources` VALUES (118, '快递公司管理', 109, 'company', '1', '/background/Express/companyList.html', 2, '快递公司管理');
INSERT INTO `sys_resources` VALUES (119, '快递员管理', 109, 'courier', '2', '/background/Express/courierList.html', NULL, 'courier');
INSERT INTO `sys_resources` VALUES (120, '订单配送统计', 109, 'reportOrder', '1', '/background/report/toReportTotal.html', 3, '订单配送统计');
INSERT INTO `sys_resources` VALUES (121, '物资管理', 109, 'materialList', '2', '/background/Material/materialList.html', NULL, NULL);
INSERT INTO `sys_resources` VALUES (122, '需求上报审核', 109, 'DemandAudit', '1', 'background/DemandAudit/getList.html', 2, '需求上报审核');
INSERT INTO `sys_resources` VALUES (123, '需求上报审核(渠道经理)', 109, 'DemandAuditOne', '1', '/background/DemandAuditOne/getList.html', 2, '需求上报审核(渠道经理)');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `enable` int(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roleKey` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 1, '根账号', 'root', '拥有所有权限1', 1);
INSERT INTO `sys_role` VALUES (17, 1, '营销中心', 'Marketing', '营销中心', 112);
INSERT INTO `sys_role` VALUES (18, 1, '渠道经理', 'Market', '渠道经理', 437);
COMMIT;

-- ----------------------------
-- Table structure for sys_smslog
-- ----------------------------
DROP TABLE IF EXISTS `sys_smslog`;
CREATE TABLE `sys_smslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(32) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `msg` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_smslog
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_smssendlog
-- ----------------------------
DROP TABLE IF EXISTS `sys_smssendlog`;
CREATE TABLE `sys_smssendlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `success` char(1) DEFAULT NULL,
  `remoteAddr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14851 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_smssendlog
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_tradelog
-- ----------------------------
DROP TABLE IF EXISTS `sys_tradelog`;
CREATE TABLE `sys_tradelog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(100) DEFAULT NULL,
  `trade_no` varchar(100) DEFAULT NULL,
  `trade_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_info`;
CREATE TABLE `sys_user_login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loginIP` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_userloginlist` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2142 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_login_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_weixin_binding
-- ----------------------------
DROP TABLE IF EXISTS `sys_weixin_binding`;
CREATE TABLE `sys_weixin_binding` (
  `payId` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `openid` varchar(60) DEFAULT NULL COMMENT '微信号',
  `nickname` varchar(60) DEFAULT NULL COMMENT '微信昵称',
  `userPhone` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `cardid` varchar(50) DEFAULT NULL COMMENT '身份证',
  `create_time` datetime DEFAULT NULL COMMENT '绑定时间',
  PRIMARY KEY (`payId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信绑定';

-- ----------------------------
-- Table structure for sys_wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_wx_menu`;
CREATE TABLE `sys_wx_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `img` varchar(255) DEFAULT NULL COMMENT '菜单图片地址',
  `address` varchar(255) NOT NULL COMMENT '菜单地址',
  `isDel` int(11) DEFAULT '0' COMMENT '是否删除（0正常，1删除）',
  `createTime` datetime DEFAULT NULL COMMENT '添加时间',
  `delTime` datetime DEFAULT NULL COMMENT '删除时间',
  `ioc` varchar(20) DEFAULT NULL COMMENT '展示图标',
  `phrase` varchar(16) DEFAULT NULL COMMENT '短语（主要用于底部菜单展示）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信端菜单';

-- ----------------------------
-- Records of sys_wx_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_wx_menu` VALUES (1, '个人设置', NULL, '/app/setup.html', 0, '2017-04-01 14:51:20', NULL, 'leftDaohang13', '个人设置');
INSERT INTO `sys_wx_menu` VALUES (60, '物资派送', '', '/app/fillingSingle/toStoreInformation.html', 0, '2017-03-22 15:28:00', NULL, 'leftDaohang33', '营销中心-物资派送');
INSERT INTO `sys_wx_menu` VALUES (62, '需求上报', '', '/app/storeSupplies/tostoreSupplies.html', 1, '2017-03-24 16:45:40', NULL, 'leftDaohang13', '门店-需求上报');
INSERT INTO `sys_wx_menu` VALUES (63, '上报统计', '', '/app/storeSupplies/goStoreTotal.html', 1, '2017-03-28 15:36:08', NULL, 'leftDaohang33', '门店-上报统计');
INSERT INTO `sys_wx_menu` VALUES (64, '订单统计', '', '/app/coordinate/goCourierTotal.html', 0, '2017-03-29 10:31:27', NULL, 'leftDaohang2', '快递员-订单统计');
INSERT INTO `sys_wx_menu` VALUES (65, '派送列表', '', '/app/fillingSingle/orderList.html', 0, '2017-03-29 15:23:21', NULL, 'leftDaohang2', '营销中心-物资派送列表');
INSERT INTO `sys_wx_menu` VALUES (66, '派送统计', '', '/app/fillingSingle/toMarketingOrderTotalView.html', 0, '2017-03-30 11:09:15', NULL, 'leftDaohang11', '营销中心-派送统计');
INSERT INTO `sys_wx_menu` VALUES (67, '订单列表', NULL, '/app/coordinate/tocourierList.html', 0, '2017-03-31 09:55:27', NULL, 'leftDaohang11', '快递员-订单派送');
INSERT INTO `sys_wx_menu` VALUES (69, '订单列表', NULL, '/app/storeSupplies/toStoreList.html', 0, '2017-04-05 11:01:22', NULL, 'leftDaohang11', '门店-订单列表');
INSERT INTO `sys_wx_menu` VALUES (70, '上报列表', NULL, '/app/storeSupplies/toStoreAllTotal.html', 1, '2017-04-06 14:27:50', NULL, 'leftDaohang11', '门店-上报列表');
COMMIT;

-- ----------------------------
-- Table structure for sys_wx_menu_shior
-- ----------------------------
DROP TABLE IF EXISTS `sys_wx_menu_shior`;
CREATE TABLE `sys_wx_menu_shior` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) DEFAULT NULL COMMENT '组织Id',
  `vendorId` int(11) NOT NULL COMMENT '厂商id',
  `brandId` int(11) DEFAULT '0' COMMENT '品牌id',
  `index` int(11) DEFAULT '0' COMMENT '主页对应页面id',
  `left_menu` varchar(255) NOT NULL DEFAULT '0' COMMENT '左侧菜单id，以逗号分隔',
  `bottom_menu` varchar(255) DEFAULT '0' COMMENT '底部导航菜单（逗号分隔）',
  `isDel` int(11) DEFAULT '0' COMMENT '是否删除（0正常，1删除）',
  `createTime` datetime DEFAULT NULL COMMENT '添加时间',
  `delTime` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信端菜单权限控制';

-- ----------------------------
-- Records of sys_wx_menu_shior
-- ----------------------------
BEGIN;
INSERT INTO `sys_wx_menu_shior` VALUES (47, 436, 0, 0, 69, '1,69', '0', 0, '2017-03-28 15:38:01', NULL);
INSERT INTO `sys_wx_menu_shior` VALUES (48, 435, 0, 0, 65, '1,60,65,66', '0', 0, '2017-03-24 17:00:18', NULL);
INSERT INTO `sys_wx_menu_shior` VALUES (50, 434, 0, 0, 67, '1,64,67', '0', 0, '2017-03-24 17:08:18', NULL);
COMMIT;

-- ----------------------------
-- Function structure for getChildrenId
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildrenId`;
delimiter ;;
CREATE FUNCTION `logistics`.`getChildrenId`(groupId int(11))
 RETURNS varchar(4000) CHARSET utf8
  COMMENT '向下查找所有的子级组织Id'
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = groupId;
SET sTempChd = cast(groupId as char);

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(id) INTO sTempChd FROM sys_group where FIND_IN_SET(parentId,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for getParentId
-- ----------------------------
DROP FUNCTION IF EXISTS `getParentId`;
delimiter ;;
CREATE FUNCTION `logistics`.`getParentId`(`groupId` int)
 RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE sTemp VARCHAR(4000);
	DECLARE sTempChd VARCHAR(4000);

	SET sTemp = groupId;
	SET sTempChd = cast(groupId as char);
	
	WHILE sTempChd is not NULL DO
	
		SET sTemp = CONCAT(sTemp,',',sTempChd);
		SELECT group_concat(parentId) INTO sTempChd FROM sys_group where FIND_IN_SET(id,sTempChd)>0;
		
	END WHILE;

	return sTemp;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for queryChildrenAreaInfo
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenAreaInfo`;
delimiter ;;
CREATE FUNCTION `logistics`.`queryChildrenAreaInfo`(`areaId` int)
 RETURNS varchar(4000) CHARSET utf8
  SQL SECURITY INVOKER
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp = areaId;
SET sTempChd = cast(areaId as char);

WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(id) INTO sTempChd FROM sys_group where FIND_IN_SET(parentId,sTempChd)>0;
END WHILE;
return sTemp;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
