/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : formdata

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 21/09/2023 14:49:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for form_18dp
-- ----------------------------
DROP TABLE IF EXISTS `form_18dp`;
CREATE TABLE `form_18dp`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `ffz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分组',
  `fkhbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户编码',
  `fgsdm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司代码',
  `fxsfw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售范围',
  `fxszz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售组织',
  `ffxqd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分销渠道',
  `fcpz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产品组',
  `fxskhz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售客户组',
  `fxsbsc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售办事处',
  `fxsz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售组',
  `fbz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '币种',
  `fhllx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '汇率类型',
  `fkhdjgc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户定价过程',
  `fddzh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单组合',
  `fzytj1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '装运条件',
  `fpodxg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'POD相关',
  `fgjmytk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '国际贸易条款',
  `fgjmytkwz1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '国际贸易条款位置1',
  `fxsfktj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售付款条件',
  `fkhkmfpz1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户科目分配组',
  `fkhsflCN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户税分类（中国）',
  `fkhsflVN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户税分类（越南）',
  `fhzhbgn1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '合作伙伴功能',
  `khda_xsst` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所有销售区域冻结',
  `fromlable_h3ge` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所有销售区域冻结',
  `fxsdddj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售订单冻结',
  `fjhdj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '交货冻结',
  `fkpdj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开票冻结',
  `fdjxszc2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '冻结销售支持',
  `fromlable_oo15` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '选定的销售区域',
  `fxsdddj1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售订单冻结',
  `fjhdj1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '交货冻结',
  `fdjxszc3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '冻结销售支持',
  `fkpdj1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开票冻结',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案_销售视图' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_18dp
-- ----------------------------

-- ----------------------------
-- Table structure for form_34za
-- ----------------------------
DROP TABLE IF EXISTS `form_34za`;
CREATE TABLE `form_34za`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fbfzt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '拜访主题',
  `fbfr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '拜访人',
  `fbfsj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '拜访时间',
  `fbz` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `ffj` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_34za
-- ----------------------------

-- ----------------------------
-- Table structure for form_5tan
-- ----------------------------
DROP TABLE IF EXISTS `form_5tan`;
CREATE TABLE `form_5tan`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_rr61` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  `input_q0yu` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户编码',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户基础数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_5tan
-- ----------------------------

-- ----------------------------
-- Table structure for form_81pj
-- ----------------------------
DROP TABLE IF EXISTS `form_81pj`;
CREATE TABLE `form_81pj`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_berq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段1',
  `input_2kwr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段2',
  `input_auhe` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段3',
  `input_988v` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_h47w` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试表单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_81pj
-- ----------------------------
INSERT INTO `form_81pj` VALUES ('1624674530730360832', '2023-02-12 15:39:42', 'admin', '简搭云管理', '2023-02-12 15:45:40', 'admin', '简搭云管理', '1624674592692813826', '1624675667252572162', '测试流程', 'HR202302120007', '101', '121', '3432', '32432', '432434', NULL);
INSERT INTO `form_81pj` VALUES ('1624677015234772992', '2023-02-12 16:44:09', 'admin', '简搭云管理', '2023-03-22 10:40:23', 'admin', '简搭云管理', '1624690771020881921', '1624691296030302210', '测试', 'HR202302120008', '101', 'test1', 'test1', 'test1', 'test1', NULL);
INSERT INTO `form_81pj` VALUES ('1624692866591629312', '2023-02-12 16:52:32', 'admin', '简搭云管理', '2023-02-12 16:54:58', 'admin', '简搭云管理', '1624692920278720515', '1624692920303886338', '测试哈哈哈', 'HR202302120009', '101', '测试哈哈哈', '测试哈哈哈', '测试哈哈哈', '测试哈哈哈', NULL);

-- ----------------------------
-- Table structure for form_82ct
-- ----------------------------
DROP TABLE IF EXISTS `form_82ct`;
CREATE TABLE `form_82ct`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pl_cut` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机号',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '生产线' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_82ct
-- ----------------------------

-- ----------------------------
-- Table structure for form_8a8m
-- ----------------------------
DROP TABLE IF EXISTS `form_8a8m`;
CREATE TABLE `form_8a8m`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `radio_b7va` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假类型',
  `textarea_chzo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假事由',
  `date_a9r3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假时间',
  `inputnumber_nt5h` int NULL DEFAULT NULL COMMENT '总共(天)',
  `input_xrku` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系手机',
  `user_awyv` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '委托人',
  `procinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ImageUpload_bg3z` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片上传',
  `FileUpload_1r6g` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件上传',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假表单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_8a8m
-- ----------------------------
INSERT INTO `form_8a8m` VALUES ('1623300325023666176', '2023-02-08 20:39:19', 'admin', '简搭云管理', '2023-02-08 20:40:13', 'admin', '简搭云管理', '1623300442157993985', '测试请假流程', 'HR202302080001', '101', '病假', '测试请假流程', '[\"2023-02-07T16:00:00.000Z\",\"2023-02-09T16:00:00.000Z\"]', 2, '13410086061', '{\"text\":\"测试用户2\",\"value\":[\"101\"]}', '', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1623301253185392640', '2023-02-08 20:43:00', 'admin', '简搭云管理', '2023-02-08 20:43:00', 'admin', '简搭云管理', NULL, '测试请假', 'HR202302080002', '101', '病假', '测试请假测试请假测试请假', '[\"2023-02-07T16:00:00.000Z\",\"2023-02-09T16:00:00.000Z\"]', 2, '13410086061', '{\"text\":\"简搭云管理\",\"value\":[\"1\"]}', '', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1624663606799855616', '2023-02-12 14:56:36', 'admin', '简搭云管理', '2023-02-12 14:56:36', 'admin', '简搭云管理', NULL, '测试请假三天以上', 'HR202302120003', '101', '病假', '测试请假三天以上', '[\"2023-02-12T16:00:00.000Z\",\"2023-02-16T16:00:00.000Z\"]', 5, '13410086061', '{\"text\":\"test1\",\"value\":[\"100\"]}', '1624663744733736963', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1624667270608277504', '2023-02-12 15:11:07', 'admin', '简搭云管理', '2023-02-12 15:15:32', 'ry', '简搭云普通员工', '1624668312427253762', '范德萨发范德萨', 'HR202302120005', '101', '病假', '测试流程', '[\"2023-02-11T16:00:00.000Z\",\"2023-02-15T16:00:00.000Z\"]', 5, '13410086061', '{\"text\":\"简搭云普通员工\",\"value\":[\"ry\"]}', '1624667397691494403', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1624668632918216704', '2023-02-12 15:16:33', 'ry', '简搭云普通员工', '2023-02-12 15:16:33', 'ry', '简搭云普通员工', NULL, '测试请假流程2', 'HR202302120006', '101', '病假', '测试请假流程2', '[\"2023-02-11T16:00:00.000Z\",\"2023-02-15T16:00:00.000Z\"]', 5, '13410086061', '{\"text\":\"测试用户2\",\"value\":[\"test2\"]}', '1624668767953833987', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1665245659232346000', '2023-06-04 14:35:05', 'admin', '系统管理员', '2023-06-04 14:35:05', 'admin', '系统管理员', NULL, '发送到发送到', 'HR202306040002', '101', '病假', '范德萨范德萨但是', '[\"2023-06-03T16:00:00.000Z\",\"2023-06-29T16:00:00.000Z\"]', 27, '13410086061', '{\"text\":\"系统管理员\",\"value\":[\"admin\"]}', '1665245773014044675', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1677929795545968600', '2023-07-09 14:37:50', 'admin', '系统管理员', '2023-07-09 14:37:50', 'admin', '系统管理员', NULL, '大萨达所', 'HR202307090002', '103', '病假', '范德萨发是的发送', '[\"2023-07-08T16:00:00.000Z\",\"2023-07-13T16:00:00.000Z\"]', 6, '13410086061', '{\"text\":\"zongbu\",\"value\":[\"zongbu\"]}', '1677930039497768962', 'http://localhost:8080/profile/upload/2023/07/09/f5b54a02-201c-4596-99b2-2f4296fd1da7.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1677968904658346000', '2023-07-09 17:12:44', 'admin', '系统管理员', '2023-07-09 17:12:44', 'admin', '系统管理员', NULL, '撒搭搭搭但是撒', 'HR202307090003', '103', '病假', '防守打法', '[\"2023-06-30T16:00:00.000Z\",\"2023-07-14T16:00:00.000Z\"]', 15, '13410086061', '{\"text\":\"zongbu\",\"value\":[\"zongbu\"]}', '1677969019345891331', 'http://localhost:8080/profile/upload/2023/07/09/7faf4c7b-5b34-4c3d-990a-55816fa4855d.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1677970146793054200', '2023-07-09 17:17:41', 'admin', '系统管理员', '2023-07-09 17:17:41', 'admin', '系统管理员', NULL, '第三方都是', 'HR202307090004', '103', '病假', '大萨达撒', '[\"2023-07-08T16:00:00.000Z\",\"2023-07-13T16:00:00.000Z\"]', 6, '13410086061', '{\"text\":\"zongbu\",\"value\":[\"zongbu\"]}', '1677970266769608707', 'http://localhost:8080/profile/upload/2023/07/09/bbf48316-5466-4061-90ed-98045d0ac533.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1680939582395236352', '2023-07-17 21:57:08', 'admin', '系统管理员', '2023-07-17 21:57:08', 'admin', '系统管理员', NULL, '发范德萨范德萨范德萨', 'HR202307170002', '103', '病假', '范德萨发撒分的', '[\"2023-07-16T16:00:00.000Z\",\"2023-07-21T16:00:00.000Z\"]', 6, '13410086061', '{\"text\":\"zongbu\",\"value\":[\"zongbu\"]}', '1680939696738848770', NULL, NULL);
INSERT INTO `form_8a8m` VALUES ('1690245695783501824', '2023-08-12 14:16:26', 'admin', '系统管理员', '2023-08-12 14:16:26', 'admin', '系统管理员', NULL, '第三方大撒范德萨', 'HR202308120001', '103', '病假', '范德萨发但是', '[\"2023-08-11T16:00:00.000Z\",\"2023-09-13T16:00:00.000Z\"]', 34, '13410086061', '{\"text\":\"罗泽恒\",\"value\":[\"19118890436\"]}', '1690245839114072067', 'http://localhost:8080/profile/upload/2023/08/12/849cf2e0-4690-4ffa-b03b-aa62de8d19da.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1692475391774531584', '2023-08-18 17:57:01', 'admin', '系统管理员', '2023-08-18 17:57:01', 'admin', '系统管理员', NULL, '防守打法范德萨范德萨', 'HR202308180013', '103', '病假', '发达发', '[\"2023-08-16T16:00:00.000Z\",\"2023-09-14T16:00:00.000Z\"]', 30, '13410086061', '{\"text\":\"zongbu\",\"value\":[\"zongbu\"]}', '1692475680781545474', 'http://localhost:8080/profile/upload/2023/08/18/3a808086-3831-43d4-8da1-f39d859729eb.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1692475696079675392', '2023-08-18 17:57:35', 'admin', '系统管理员', '2023-08-18 17:57:35', 'admin', '系统管理员', NULL, 'dfsafsa', 'HR202308180014', '103', '病假', 'fdsafdsafdsf', '[\"2023-08-08T16:00:00.000Z\",\"2023-09-13T16:00:00.000Z\"]', 37, '13410086021', '{\"text\":\"zongbu\",\"value\":[\"zongbu\"]}', '1692475823639539715', 'http://localhost:8080/profile/upload/2023/08/18/e918918c-7849-460a-9a81-902e676abbe4.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1701944127056232448', '2023-09-13 21:02:00', 'admin', '系统管理员', '2023-09-13 21:02:00', 'admin', '系统管理员', NULL, '撒范德萨', 'HR202309130007', '103', '病假', '防守打法但是', '[\"2023-10-09T16:00:00.000Z\",\"2023-10-09T16:00:00.000Z\"]', 1, '13410056021', '{\"text\":\"张佳付\",\"value\":[\"16670286621\"]}', '1701944315795226627', 'http://localhost:8080/profile/upload/2023/09/13/15540b27-ed66-4b43-bcd6-9c312026e69b.png', NULL);
INSERT INTO `form_8a8m` VALUES ('1701944622055407616', '2023-09-13 21:03:55', 'admin', '系统管理员', '2023-09-13 21:03:55', 'admin', '系统管理员', NULL, '发生大幅度撒', 'HR202309130008', '103', '病假', '范德萨发是的分的', '[\"2023-11-10T16:00:00.000Z\",\"2023-12-20T16:00:00.000Z\"]', 41, '13410086061', '{\"text\":\"test1\",\"value\":[\"test1\"]}', '1701944801499824130', 'http://localhost:8080/profile/upload/2023/09/13/2a126616-5522-4348-8c96-382440400818.png', NULL);

-- ----------------------------
-- Table structure for form_8jwj
-- ----------------------------
DROP TABLE IF EXISTS `form_8jwj`;
CREATE TABLE `form_8jwj`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `textarea_zk9e` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多行文本',
  `input_59k5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `editor_pb58` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '富文本',
  `ImageUpload_j8fz` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片上传',
  `FileUpload_am7n` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件上传',
  `select_nfaj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下拉列表',
  `checkbox_q2ta` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多选项',
  `radio_ykeg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单选项',
  `date_hsaa` datetime NULL DEFAULT NULL COMMENT '日期控件',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '防守打法' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_8jwj
-- ----------------------------
INSERT INTO `form_8jwj` VALUES ('1668264833751912400', '2023-06-12 22:32:02', 'admin', '系统管理员', '2023-06-12 22:32:02', 'admin', '系统管理员', '101', '发生的范德萨范德萨范德萨', '范德萨范德萨', 'PHA+5Y+R55Sf5aSn5bmF5bqmczwvcD4=', 'http://localhost:8080/profile/upload/2023/06/12/70b5a0fb-023f-4bf1-ac19-0360e1489db6.png', 'http://localhost:8080/profile/upload/2023/06/12/279c648b-08d0-47d5-807b-8c79dec03145.png', NULL, NULL, NULL, NULL);
INSERT INTO `form_8jwj` VALUES ('1671137496442835000', '2023-06-20 20:46:48', 'admin', '系统管理员', '2023-06-20 20:46:48', 'admin', '系统管理员', '101', '防守打法', '防守打法', 'PHA+5Y+R55Sf55qEZuiMg+W+t+iQqOiMg+W+t+iQqOiMg+W+t+iQqDwvcD4=', NULL, NULL, '1', NULL, NULL, NULL);
INSERT INTO `form_8jwj` VALUES ('1672281210330517500', '2023-06-24 00:31:38', 'admin', '系统管理员', '2023-06-24 00:31:38', 'admin', '系统管理员', '101', '萨达', '搭是撒', 'PHA+6IyD5b636JCo6IyD5b636JCoZuiMg+W+t+iQqGbms5XkvKQ8L3A+', NULL, NULL, '0', '[\"正常\",\"暂停\"]', '通知', NULL);
INSERT INTO `form_8jwj` VALUES ('1672283761046163500', '2023-06-24 00:42:17', 'admin', '系统管理员', '2023-06-24 00:42:17', 'admin', '系统管理员', '101', '范德萨发萨达', '搭范德萨', 'PHA+6IyD5b636JCoZuiMg+W+t+iQqOWPkeiMg+W+t+iQqDwvcD4=', NULL, NULL, '1', '[\"正常\",\"暂停\"]', '通知', NULL);
INSERT INTO `form_8jwj` VALUES ('1672292760390963200', '2023-06-24 01:17:29', 'admin', '系统管理员', '2023-06-24 01:17:29', 'admin', '系统管理员', '101', '神鼎飞丹砂富士达', '的个梵蒂冈', 'PHA+6IKh5Lu956ys5LiJ5Liq6IyD5b636JCoZ+WJsuWPkeS7o+mmljwvcD4=', NULL, NULL, '0', '[\"0\"]', '1', '2023-06-06 00:00:00');

-- ----------------------------
-- Table structure for form_9c48
-- ----------------------------
DROP TABLE IF EXISTS `form_9c48`;
CREATE TABLE `form_9c48`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_td0d` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色选择',
  `select_pmaz` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉列表',
  `user_4fov` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选人控件',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_9c48
-- ----------------------------

-- ----------------------------
-- Table structure for form_9ggk
-- ----------------------------
DROP TABLE IF EXISTS `form_9ggk`;
CREATE TABLE `form_9ggk`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `码 fcgrp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'CGrp',
  `fmc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `fcgrp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'CGrp',
  `fyydm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '语言代码',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '销售客户组' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_9ggk
-- ----------------------------

-- ----------------------------
-- Table structure for form_9xbb
-- ----------------------------
DROP TABLE IF EXISTS `form_9xbb`;
CREATE TABLE `form_9xbb`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `input_oeeu` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_230z` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `textarea_u6vm` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '范德萨发' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_9xbb
-- ----------------------------

-- ----------------------------
-- Table structure for form_a2aq
-- ----------------------------
DROP TABLE IF EXISTS `form_a2aq`;
CREATE TABLE `form_a2aq`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_ou4j` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `input_eh9k` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `select_qmv6` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `inputnumber_7wko` int NULL DEFAULT NULL COMMENT '成本价',
  `inputnumber_4n5a` int NULL DEFAULT NULL COMMENT '低价',
  `inputnumber_0vxe` int NULL DEFAULT NULL COMMENT '市场价',
  `editor_neym` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品描述',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_a2aq
-- ----------------------------
INSERT INTO `form_a2aq` VALUES ('1623228065013256192', '2023-02-08 15:52:45', 'admin', '简搭云管理', '2023-02-08 15:52:45', 'admin', '简搭云管理', '101', '手机', 'HIP20150512001', '电子产品', 1200, 1500, 2000, 'PHA+5paw5qy+5omL5py65pyA5aW9PC9wPg==');
INSERT INTO `form_a2aq` VALUES ('1623234439059296256', '2023-02-08 16:18:07', 'admin', '简搭云管理', '2023-02-08 16:18:07', 'admin', '简搭云管理', '101', '联想电脑', 'PC20230208001', '电子产品', 2500, 3000, 5000, 'PHA+6IGU5oOz55S16ISR6Z2e5bi45aW9PC9wPg==');
INSERT INTO `form_a2aq` VALUES ('1623234727639994368', '2023-02-08 16:19:22', 'admin', '简搭云管理', '2023-02-08 16:19:22', 'admin', '简搭云管理', '101', '漫步者音箱', 'YCH2022130111', '气体检测类', 300, 500, 620, 'PHA+5ryr5q2l6ICF6Z+z566x5ryr5q2l6ICF6Z+z566x5ryr5q2l6ICF6Z+z566xPC9wPg==');

-- ----------------------------
-- Table structure for form_aaog
-- ----------------------------
DROP TABLE IF EXISTS `form_aaog`;
CREATE TABLE `form_aaog`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_aaog
-- ----------------------------

-- ----------------------------
-- Table structure for form_ahy6
-- ----------------------------
DROP TABLE IF EXISTS `form_ahy6`;
CREATE TABLE `form_ahy6`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_a1hy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  `input_q5gx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_ahy6
-- ----------------------------

-- ----------------------------
-- Table structure for form_amel
-- ----------------------------
DROP TABLE IF EXISTS `form_amel`;
CREATE TABLE `form_amel`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_e2vq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `input_tjm6` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `select_4p7u` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `inputnumber_zd5u` int NULL DEFAULT NULL COMMENT '数量',
  `inputnumber_0woj` int NULL DEFAULT NULL COMMENT '价格',
  `inputnumber_9aak` int NULL DEFAULT NULL COMMENT '成本',
  `editor_0s1f` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `user_q135` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选人控件',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试1314520' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_amel
-- ----------------------------

-- ----------------------------
-- Table structure for form_awyp
-- ----------------------------
DROP TABLE IF EXISTS `form_awyp`;
CREATE TABLE `form_awyp`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `input_snar` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `input_snv5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号',
  `user_t9m3` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选人控件',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'bd1' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_awyp
-- ----------------------------

-- ----------------------------
-- Table structure for form_b1l8
-- ----------------------------
DROP TABLE IF EXISTS `form_b1l8`;
CREATE TABLE `form_b1l8`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fbt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `fjjcd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '紧急程度',
  `fsqr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '申请人',
  `fsqbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '申请部门',
  `fsyb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '事业部',
  `fsybpg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '事业部品管',
  `fcp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产品',
  `fhsdw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '核算单位',
  `fdjrq` date NULL DEFAULT NULL COMMENT '单据日期',
  `fkhcxxt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户查询系统',
  `fkhjc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户简称（SAP）',
  `fkhxxcx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户信息查询（SAP）',
  `fkhmcsap` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称（SAP）',
  `fscjd` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生产基地',
  `ftssy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '投诉事由',
  `fbz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `FileUpload_8hb6` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件上传',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户售后' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_b1l8
-- ----------------------------

-- ----------------------------
-- Table structure for form_bvae
-- ----------------------------
DROP TABLE IF EXISTS `form_bvae`;
CREATE TABLE `form_bvae`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fkhmc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  `fkhbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户编码',
  `fkhlx` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户类型',
  `flkhglgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '老客户关联公司',
  `fsfmygs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否贸易公司',
  `fhydw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '行业地位',
  `fsfssgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否上市公司',
  `fjynx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '经营年限',
  `fssjfqk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '诉讼纠纷情况',
  `fwfwgqk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '违法违规情况',
  `fkhjj` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户简介',
  `FileUpload_m5px` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件上传',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_bvae
-- ----------------------------

-- ----------------------------
-- Table structure for form_d0f2
-- ----------------------------
DROP TABLE IF EXISTS `form_d0f2`;
CREATE TABLE `form_d0f2`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fkhmc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  `fkhbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户编码',
  `radio_z78a` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户类型',
  `flkhglgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '老客户关联公司',
  `fsfmygs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否贸易公司',
  `fhydw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '行业地位',
  `fsfssgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否上市公司',
  `fjynx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '经营年限',
  `fssjfqk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '诉讼纠纷情况',
  `fwfwgqk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '违法违规情况',
  `fkhjj` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户简介',
  `FileUpload_tbz5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件上传',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_d0f2
-- ----------------------------

-- ----------------------------
-- Table structure for form_d4ty
-- ----------------------------
DROP TABLE IF EXISTS `form_d4ty`;
CREATE TABLE `form_d4ty`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `input_x7v9` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_jtsa` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `textarea_s8nu` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多行文本',
  `select_4ngk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下拉列表',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_d4ty
-- ----------------------------
INSERT INTO `form_d4ty` VALUES ('1674430325336187000', '2023-06-29 22:51:17', 'admin', '系统管理员', '2023-06-29 22:51:17', 'admin', '系统管理员', '101', 'sdfdsf', 'sdfdsaf', 'fdsafds', NULL);

-- ----------------------------
-- Table structure for form_e3db
-- ----------------------------
DROP TABLE IF EXISTS `form_e3db`;
CREATE TABLE `form_e3db`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `input_2qjc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方案名称',
  `input_xsm9` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'YL名称',
  `input_g7rt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'YL代号',
  `input_asgf` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'YL时间',
  `textarea_tbjr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设计目标',
  `textarea_k274` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设计问题',
  `select_qr8q` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实时统计',
  `select_090g` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考核评估',
  `select_36lk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多维展现',
  `select_005s` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '复盘讲评',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'f_a' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_e3db
-- ----------------------------

-- ----------------------------
-- Table structure for form_e71x
-- ----------------------------
DROP TABLE IF EXISTS `form_e71x`;
CREATE TABLE `form_e71x`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_gken` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '测试流程表单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_e71x
-- ----------------------------
INSERT INTO `form_e71x` VALUES ('1673974643121766400', '2023-06-28 08:40:36', 'admin', '简搭云管理', '2023-08-22 20:26:56', 'admin', '简搭云管理', '1673974666712940546', '1673974666746494978', '11', 'HR202306280002', '101', '111');
INSERT INTO `form_e71x` VALUES ('1673980427121508352', '2023-06-28 09:03:33', 'admin', '简搭云管理', '2023-08-22 20:26:58', 'admin', '简搭云管理', '1673980443339096067', '1673980443414593537', '21312', 'XS202306280002', '101', '1231');

-- ----------------------------
-- Table structure for form_ec57
-- ----------------------------
DROP TABLE IF EXISTS `form_ec57`;
CREATE TABLE `form_ec57`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fbfzt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '拜访主题',
  `fbfkh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '拜访客户',
  `fbz` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `fbfrq` date NULL DEFAULT NULL COMMENT '拜访日期',
  `fspr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '审批人',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户拜访' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_ec57
-- ----------------------------
INSERT INTO `form_ec57` VALUES ('1674597238573629440', '2023-06-30 01:54:55', 'admin', '简搭云管理', '2023-06-30 02:04:34', 'admin', '简搭云管理', '1674597350643679235', '1674599393299132417', 'cs1', 'XS202306300001', '101', NULL, NULL, '111', NULL, '{\"text\":\"简搭云管理\",\"value\":[\"admin\"]}');
INSERT INTO `form_ec57` VALUES ('1680942660007735296', '2023-07-17 22:09:32', 'admin', '简搭云管理', '2023-07-17 22:09:32', 'admin', '简搭云管理', '1680942816449576962', NULL, '第三方都是', 'XS202307170002', '103', '范德萨发大', '法师范德萨', '电饭锅第三方但是', '2023-07-01', '{\"text\":\"test1\",\"value\":[\"test1\"]}');
INSERT INTO `form_ec57` VALUES ('1693938847287549952', '2023-08-22 20:01:53', 'admin', '简搭云管理', '2023-08-22 20:23:46', 'ry', '简搭云普通员工', '1693938921743323139', '1693961382354604034', '多福多寿', 'XS202308220001', '103', '范德萨范德萨', '范德萨范德萨', '范德萨范德萨范德萨但是', '2023-08-02', '{\"text\":\"简搭云普通员工\",\"value\":[\"ry\"]}');
INSERT INTO `form_ec57` VALUES ('1693939819464302592', '2023-08-22 18:55:11', 'admin', '简搭云管理', '2023-08-22 18:55:11', 'admin', '简搭云管理', '1693939871572824067', NULL, '范德萨范德萨', 'XS202308220004', '103', '范德萨范德萨', '富士达范德萨', '范德萨范德萨', '2023-08-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}');
INSERT INTO `form_ec57` VALUES ('1693939887114231808', '2023-08-22 20:01:36', 'admin', '简搭云管理', '2023-08-22 20:20:53', 'ggx', '顾国兴', '1693939945170276355', '1693956586096603138', '大范德萨范德萨', 'XS202308220005', '103', '是否第三方但是', '萨达范德萨', '四方达范德萨', '2023-08-02', '{\"text\":\"顾国兴\",\"value\":[\"ggx\"]}');
INSERT INTO `form_ec57` VALUES ('1693940125325549568', '2023-08-22 18:56:52', 'admin', '简搭云管理', '2023-08-22 18:56:52', 'admin', '简搭云管理', '1693940293578551298', NULL, '范德萨发大范德萨', 'XS202308220006', '103', '地方撒范德萨', '富士达范德萨', '富士达范德萨范德萨', '2023-08-02', '{\"text\":\"阮青青\",\"value\":[\"rqq\"]}');
INSERT INTO `form_ec57` VALUES ('1693944649410928640', '2023-08-22 20:00:55', 'admin', '简搭云管理', '2023-08-22 20:01:17', 'admin', '简搭云管理', '1693944729872953346', NULL, '刚发的广泛地', 'XS202308220007', '103', '第三方都是', '防守打法萨达', '防守打法萨达范德萨', '2023-08-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}');

-- ----------------------------
-- Table structure for form_eevd
-- ----------------------------
DROP TABLE IF EXISTS `form_eevd`;
CREATE TABLE `form_eevd`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_b27q` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段1',
  `input_l8uh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段2',
  `input_amxs` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试字段3',
  `input_27tn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_pcjj` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试123' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_eevd
-- ----------------------------
INSERT INTO `form_eevd` VALUES ('1640597167977902080', '2023-03-28 14:10:33', 'admin', '简搭云管理', '2023-03-28 14:10:33', 'admin', '简搭云管理', '1640597221505609730', NULL, '测试', NULL, '101', '撒大声地', '阿发', '按时发生', '发发', NULL);
INSERT INTO `form_eevd` VALUES ('1674028950848770048', '2023-06-28 20:16:29', 'admin', '简搭云管理', '2023-06-28 20:16:29', 'admin', '简搭云管理', '1674028996707786755', NULL, '测试房第三范德萨发的范德萨', 'WMP202306280001', '101', '范德萨范德萨', '富士达范德萨', '范德萨范德萨发生的', '士大夫大', '范德萨范德萨');

-- ----------------------------
-- Table structure for form_gmc5
-- ----------------------------
DROP TABLE IF EXISTS `form_gmc5`;
CREATE TABLE `form_gmc5`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autonumber_at5n` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自动编号',
  `date_yolx` datetime NULL DEFAULT NULL COMMENT '日期控件',
  `select_huy9` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉列表',
  `editor_r7db` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '富文本',
  `input_86ta` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_gue6` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_ttaa` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '54' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_gmc5
-- ----------------------------

-- ----------------------------
-- Table structure for form_hqfr
-- ----------------------------
DROP TABLE IF EXISTS `form_hqfr`;
CREATE TABLE `form_hqfr`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fyydm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '语言代码',
  `fcgrp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'CGrp',
  `fmc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '销售客户组' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_hqfr
-- ----------------------------

-- ----------------------------
-- Table structure for form_kulv
-- ----------------------------
DROP TABLE IF EXISTS `form_kulv`;
CREATE TABLE `form_kulv`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fkhmc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  `fhysj` date NULL DEFAULT NULL COMMENT '会议时间',
  `fkhchz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户参会者',
  `ffggchz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'FGG参会者',
  `ffwlx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '服务类型',
  `fhymd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会议目的',
  `fhyyc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会议议程',
  `fxdjh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '行动计划',
  `fhynr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会议内容',
  `fqtnr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '其他内容',
  `FileUpload_lbaz` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件上传',
  `fromlable_89qz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级',
  `fkhxxcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(产能)',
  `fkhdjyqxy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(逾期信用)',
  `fromlable_ax1e` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '收入情况',
  `fnf` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年份',
  `fxssr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售收入',
  `fromlable_axk0` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  `fnf2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年份',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  `fromlable_0w84` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产业链',
  `fcy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产业',
  `fcn2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  `fromlable_gkam` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '主要生产工艺',
  `fgylx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '工艺类型',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  `fromlable_43sc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '竞争概况',
  `fgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司',
  `fzb2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  `fromlable_4o23` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系人信息',
  `fjd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '基地',
  `fbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门',
  `fzw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `fxm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `fsjh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `fsr` date NULL DEFAULT NULL COMMENT '生日',
  `fjwgzjl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '既往工作经历',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户服务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_kulv
-- ----------------------------

-- ----------------------------
-- Table structure for form_l2ws
-- ----------------------------
DROP TABLE IF EXISTS `form_l2ws`;
CREATE TABLE `form_l2ws`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_kbnl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  `input_pmov` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  `input_2cyn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '12131' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_l2ws
-- ----------------------------

-- ----------------------------
-- Table structure for form_l6f8
-- ----------------------------
DROP TABLE IF EXISTS `form_l6f8`;
CREATE TABLE `form_l6f8`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_yena` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选人控件',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ceshi99999' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_l6f8
-- ----------------------------
INSERT INTO `form_l6f8` VALUES ('1638812548878151680', '2023-03-23 15:59:02', 'admin', '简搭云管理', '2023-03-23 15:59:02', 'admin', '简搭云管理', '101', '{\"text\":\"未未\",\"value\":[\"111\"]}');

-- ----------------------------
-- Table structure for form_n9n6
-- ----------------------------
DROP TABLE IF EXISTS `form_n9n6`;
CREATE TABLE `form_n9n6`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '新产品推广状态' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_n9n6
-- ----------------------------

-- ----------------------------
-- Table structure for form_ntrh
-- ----------------------------
DROP TABLE IF EXISTS `form_ntrh`;
CREATE TABLE `form_ntrh`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fkhmc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  `fkhbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户编码',
  `radio_spo4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户类型',
  `flkhglgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '老客户关联公司',
  `fsfmygs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否贸易公司',
  `fhydw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '行业地位',
  `fsfssgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否上市公司',
  `fjynx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '经营年限',
  `fssjfqk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '诉讼纠纷情况',
  `fwfwgqk` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '违法违规情况',
  `fkhjj` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户简介',
  `FileUpload_41vj` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件上传',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_ntrh
-- ----------------------------
INSERT INTO `form_ntrh` VALUES ('1674239276726403072', '2023-06-29 02:21:24', 'rqq', '阮青青', '2023-06-29 02:31:47', 'rqq', '阮青青', '101', '隆基', 'longji', '全新开发客户', NULL, '是', '牛逼2', NULL, '20', NULL, NULL, NULL, NULL);
INSERT INTO `form_ntrh` VALUES ('1674259968763179008', '2023-06-29 03:34:37', 'admin', '简搭云管理', '2023-06-29 03:34:37', 'admin', '简搭云管理', '101', NULL, NULL, NULL, NULL, '选项1', NULL, NULL, NULL, NULL, NULL, NULL, '/dev-api//file/statics/2023/06/29/未命名文件_20230629113425A001.png');
INSERT INTO `form_ntrh` VALUES ('1674593078356738048', '2023-06-30 01:38:05', 'admin', '简搭云管理', '2023-06-30 01:38:05', 'admin', '简搭云管理', '101', NULL, NULL, NULL, NULL, '选项1', NULL, NULL, NULL, NULL, NULL, NULL, '/dev-api//file/statics/2023/06/30/未命名文件_20230630093804A001.png');
INSERT INTO `form_ntrh` VALUES ('1674602953233215488', '2023-06-30 02:17:22', 'admin', '简搭云管理', '2023-07-03 02:30:57', 'admin', '简搭云管理', '101', NULL, NULL, NULL, NULL, '选项1', NULL, NULL, NULL, NULL, NULL, NULL, '/dev-api//file/statics/2023/06/30/未命名文件_20230630101719A001.png');
INSERT INTO `form_ntrh` VALUES ('1693963559975706624', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '是否第三方', '范德萨', '全新开发客户', '富士达', '是', '富士达', '是', '富士达范德萨', '萨达范德萨', '萨达范德萨啊', '萨达范德萨', 'http://localhost:8080/profile/upload/2023/08/22/dbf98e6a-dd1c-4339-9241-07aa0ed55f00.png');
INSERT INTO `form_ntrh` VALUES ('1693963958824656896', '2023-08-22 20:31:05', 'admin', '简搭云管理', '2023-08-22 20:31:05', 'admin', '简搭云管理', '103', '的撒范德萨', '大范德萨', '全新开发客户', '萨达范德萨', '是', '范德萨范德萨但是', '是', '富士达范德萨', NULL, NULL, NULL, NULL);
INSERT INTO `form_ntrh` VALUES ('1693964073643728896', '2023-08-22 20:32:26', 'admin', '简搭云管理', '2023-08-22 20:32:26', 'admin', '简搭云管理', '103', '富士达范德萨', '范德萨范德萨', '全新开发客户', '富士达范德萨', '是', '富士达范德萨', '是', '富士达范德萨', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for form_o382
-- ----------------------------
DROP TABLE IF EXISTS `form_o382`;
CREATE TABLE `form_o382`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datarelevance_a504_text` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datarelevance_a504` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `fromlable_adcx` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `fromlable_szr1` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `fromlable_sr22` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `input_6avg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成交金额',
  `input_lc98` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '利润',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售列表粉丝打发大水' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_o382
-- ----------------------------
INSERT INTO `form_o382` VALUES ('1623235444404604928', '2023-02-08 16:22:04', 'admin', '简搭云管理', '2023-02-12 20:56:24', 'admin', '简搭云管理', '101', '简搭云科技园有限公司', '1623227308339843072', '简搭云', '13410086061', '简搭云', '15200', '7200');
INSERT INTO `form_o382` VALUES ('1623293520226766848', '2023-02-08 20:12:53', 'admin', '简搭云管理', '2023-02-08 20:12:53', 'admin', '简搭云管理', '101', '科创恒电子科技有限公司', '1623280313021976576', '李玉', '13410086061', '13410086061', '22000', '13400');
INSERT INTO `form_o382` VALUES ('1626874647742951424', '2023-02-18 17:22:47', 'admin', '简搭云管理', '2023-02-18 17:22:47', 'admin', '简搭云管理', '101', '测试客户', '1623292757383532544', '小刘', '13410056061', NULL, '1200', '600');
INSERT INTO `form_o382` VALUES ('1635811392652423168', '2023-03-15 09:13:41', 'admin', '简搭云管理', '2023-03-15 09:13:41', 'admin', '简搭云管理', '101', '测试客户', '1623292757383532544', '小刘', '13410056061', '2222', '0', '0');
INSERT INTO `form_o382` VALUES ('1638730640764809216', '2023-03-23 10:33:44', 'admin', '简搭云管理', '2023-03-23 10:34:41', 'admin', '简搭云管理', '101', '测试客户', '1623292757383532544', '小刘', '13410056061', '2222', '26715', '14215');
INSERT INTO `form_o382` VALUES ('1640168916327444480', '2023-03-27 09:49:17', 'admin', '简搭云管理', '2023-03-27 09:49:17', 'admin', '简搭云管理', '101', '测试客户1', '1623292757383532544', '小刘', '13410056061', '2222', '0', '-300');
INSERT INTO `form_o382` VALUES ('1640597541619085312', '2023-03-28 14:12:13', 'admin', '简搭云管理', '2023-03-30 23:04:17', 'admin', '简搭云管理', '101', '测试客户1', '1623292757383532544', '小刘', '13410056061', '2222', '600', '-18005400');

-- ----------------------------
-- Table structure for form_q5a3
-- ----------------------------
DROP TABLE IF EXISTS `form_q5a3`;
CREATE TABLE `form_q5a3`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_xy1y` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `select_ucq9` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `input_zh5m` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `input_uajs` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `input_1dho` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话发生大幅度撒',
  `input_palk` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `textarea_a8w5` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_q5a3
-- ----------------------------
INSERT INTO `form_q5a3` VALUES ('1623227308339843072', '2023-02-08 15:49:34', 'admin', '简搭云管理', '2023-02-08 15:49:34', 'admin', '简搭云管理', '101', '简搭云科技园有限公司', '官网', 'www.kyform.cn', '简搭云', '13410086061', '简搭云', NULL);
INSERT INTO `form_q5a3` VALUES ('1623280313021976576', '2023-02-08 19:19:59', 'admin', '简搭云管理', '2023-02-08 19:19:59', 'admin', '简搭云管理', '101', '科创恒电子科技有限公司', '朋友推荐', 'www.kechuangheng.com', '李玉', '13410086061', '13410086061', '13410086061');
INSERT INTO `form_q5a3` VALUES ('1623292757383532544', '2023-02-08 20:09:20', 'admin', '简搭云管理', '2023-03-24 09:27:55', 'admin', '简搭云管理', '101', '测试客户1', '官网', 'www.baidu.com', '小刘', '13410056061', '2222', NULL);
INSERT INTO `form_q5a3` VALUES ('1675399951322960000', '2023-07-02 15:04:24', 'ry', '测试账号', '2023-07-02 15:04:24', 'ry', '测试账号', '101', '范德萨发', '官网', '范德萨', '范德萨', '13410086061', '水电费的', '第三方范德萨');
INSERT INTO `form_q5a3` VALUES ('1675401370075541500', '2023-07-02 15:10:20', 'ry', '测试账号', '2023-07-02 15:10:20', 'ry', '测试账号', '105', '新增测试 本部门数据权限', '官网', '新增测试 本部门数据权限', '刘行', '13410086061', '第三方都是', '13410086061');
INSERT INTO `form_q5a3` VALUES ('1675401747999109000', '2023-07-02 15:11:41', '15802568331', '吴虹均', '2023-07-02 15:11:41', '15802568331', '吴虹均', '104', 'OK', '官网', '测试', '刘飞', '13410052031', NULL, '第三方都是但是范德萨');
INSERT INTO `form_q5a3` VALUES ('1675402099997683700', '2023-07-02 15:13:02', '16670286621', '张佳付', '2023-07-02 15:13:02', '16670286621', '张佳付', '104', '16670286621', '互联网', '16670286621', '16670286621', '13410056031', '16670286621', '16670286621');

-- ----------------------------
-- Table structure for form_sk7e
-- ----------------------------
DROP TABLE IF EXISTS `form_sk7e`;
CREATE TABLE `form_sk7e`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_sk7e
-- ----------------------------

-- ----------------------------
-- Table structure for form_toe4
-- ----------------------------
DROP TABLE IF EXISTS `form_toe4`;
CREATE TABLE `form_toe4`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fromlable_svmg` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '这是一个表单',
  `input_aq6n` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `date_zmfs` date NULL DEFAULT NULL COMMENT '日期控件',
  `user_2afp` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选人控件',
  `autonumber_dm1c` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '自动编号',
  `select_972x` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下拉列表',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '这是一个普通表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_toe4
-- ----------------------------
INSERT INTO `form_toe4` VALUES ('1698152559519166464', '2023-09-03 09:55:09', 'admin', '系统管理员', '2023-09-03 09:55:09', 'admin', '系统管理员', '103', NULL, '444', '2023-09-03', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030013', '1');
INSERT INTO `form_toe4` VALUES ('1698157954992136192', '2023-09-03 10:16:34', 'admin', '系统管理员', '2023-09-03 10:17:21', 'admin', '系统管理员', '103', NULL, '234与涂鸦', '2023-09-01', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030016', '0');
INSERT INTO `form_toe4` VALUES ('1698159885097594880', '2023-09-03 10:24:48', 'admin', '系统管理员', '2023-09-03 10:24:48', 'admin', '系统管理员', '103', NULL, '234', '2023-09-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030017', '1');
INSERT INTO `form_toe4` VALUES ('1698165459583463424', '2023-09-03 10:46:27', 'admin', '系统管理员', '2023-09-03 10:46:58', 'admin', '系统管理员', '103', NULL, 'sfdsf', '2023-09-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030024', '1');
INSERT INTO `form_toe4` VALUES ('1698165748176744448', '2023-09-03 10:47:55', 'admin', '系统管理员', '2023-09-03 10:47:55', 'admin', '系统管理员', '103', NULL, '234', NULL, '{\"text\":\"\",\"value\":[]}', 'WMP202309030025', '选项2');
INSERT INTO `form_toe4` VALUES ('1698166377020354560', '2023-09-03 10:50:27', 'admin', '系统管理员', '2023-09-03 10:50:27', 'admin', '系统管理员', '103', NULL, '234', '2023-09-01', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030027', '0');
INSERT INTO `form_toe4` VALUES ('1698302559423242240', '2023-09-03 19:51:31', 'admin', '系统管理员', '2023-09-03 19:51:31', 'admin', '系统管理员', '103', NULL, '234', '2023-09-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030030', '0');
INSERT INTO `form_toe4` VALUES ('1698307581603160064', '2023-09-03 20:11:05', 'admin', '系统管理员', '2023-09-03 20:11:22', 'admin', '系统管理员', '103', NULL, '的观点', '2023-09-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030037', '0');
INSERT INTO `form_toe4` VALUES ('1698307881370066944', '2023-09-03 20:12:16', 'admin', '系统管理员', '2023-09-03 20:12:16', 'admin', '系统管理员', '103', NULL, '234是大范德萨', '2023-09-03', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030039', '0');
INSERT INTO `form_toe4` VALUES ('1698308110924324864', '2023-09-03 20:13:13', 'admin', '系统管理员', '2023-09-03 20:13:13', 'admin', '系统管理员', '103', NULL, '234发生大幅度撒', '2023-09-02', '{\"text\":\"test1\",\"value\":[\"test1\"]}', 'WMP202309030040', '0');

-- ----------------------------
-- Table structure for form_tvaz
-- ----------------------------
DROP TABLE IF EXISTS `form_tvaz`;
CREATE TABLE `form_tvaz`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_yeyq` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  `input_1a93` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_tvaz
-- ----------------------------

-- ----------------------------
-- Table structure for form_twto
-- ----------------------------
DROP TABLE IF EXISTS `form_twto`;
CREATE TABLE `form_twto`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_zlnv` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `select_sj1g` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户来源',
  `input_a700` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `input_bag7` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `input_upa7` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `input_g72q` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `datarelevance_ft22_text` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datarelevance_ft22` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联数据',
  `textarea_s82r` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `role_pqb2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选角色',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '11' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_twto
-- ----------------------------

-- ----------------------------
-- Table structure for form_uyz0
-- ----------------------------
DROP TABLE IF EXISTS `form_uyz0`;
CREATE TABLE `form_uyz0`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_ed5f` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `input_koaa` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `cityselector_3e3j` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区选择',
  `select_u5ak` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作物品种',
  `input_vhjh` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种植面积',
  `input_oqxb` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种子用量',
  `input_oezk` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '播种成本',
  `FileUpload_o4s8` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件上传',
  `ImageUpload_8qnm` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片上传',
  `radio_pjyj` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单选项',
  `checkbox_z11j` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '多选项',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '1' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_uyz0
-- ----------------------------
INSERT INTO `form_uyz0` VALUES ('1641429917588885504', '2023-03-30 21:19:26', 'admin', '简搭云管理', '2023-03-30 21:19:26', 'admin', '简搭云管理', '101', NULL, NULL, NULL, '小麦', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `form_uyz0` VALUES ('1668267457402310656', '2023-06-12 22:42:37', 'admin', '简搭云管理', '2023-06-12 22:42:37', 'admin', '简搭云管理', '101', '萨达', '13410086061', '[\"130000\",\"130200\",\"130202\"]', '小麦', '1221', '1222', '12214', 'http://localhost:8080/file/statics/2023/06/12/head-bg_20230612224213A001.png', 'http://localhost:8080/file/statics/2023/06/12/head-bg_20230612224216A002.png', NULL, NULL);
INSERT INTO `form_uyz0` VALUES ('1668267667096539136', '2023-06-12 22:43:24', 'admin', '简搭云管理', '2023-06-12 22:43:24', 'admin', '简搭云管理', '101', '萨达', '13410086061', '[\"120000\",\"120100\",\"120101\"]', '小麦', '113', '是的撒', '大', 'http://localhost:8080/file/statics/2023/06/12/head-bg_20230612224322A004.png', 'http://localhost:8080/file/statics/2023/06/12/head-bg_20230612224320A003.png', NULL, NULL);

-- ----------------------------
-- Table structure for form_wvk2
-- ----------------------------
DROP TABLE IF EXISTS `form_wvk2`;
CREATE TABLE `form_wvk2`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_wvk2
-- ----------------------------
INSERT INTO `form_wvk2` VALUES ('1673571060073115648', '2023-06-27 05:56:52', 'admin', '简搭云管理', '2023-06-27 05:56:52', 'admin', '简搭云管理', '101', '测试');
INSERT INTO `form_wvk2` VALUES ('1673572407484887040', '2023-06-27 06:02:14', 'ry', '简搭云普通员工', '2023-06-27 06:02:14', 'ry', '简搭云普通员工', '101', '233232');
INSERT INTO `form_wvk2` VALUES ('1693963907473793024', '2023-08-22 20:30:44', 'admin', '简搭云管理', '2023-08-22 20:30:44', 'admin', '简搭云管理', '103', '出生地地方撒萨达');
INSERT INTO `form_wvk2` VALUES ('1693963926771785728', '2023-08-22 20:30:48', 'admin', '简搭云管理', '2023-08-22 20:30:48', 'admin', '简搭云管理', '103', '富士达范德萨');

-- ----------------------------
-- Table structure for form_x0we
-- ----------------------------
DROP TABLE IF EXISTS `form_x0we`;
CREATE TABLE `form_x0we`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_x0we
-- ----------------------------

-- ----------------------------
-- Table structure for form_x672
-- ----------------------------
DROP TABLE IF EXISTS `form_x672`;
CREATE TABLE `form_x672`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_9ffq` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  `input_yr7d` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  `input_2sa6` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_x672
-- ----------------------------

-- ----------------------------
-- Table structure for form_xb22
-- ----------------------------
DROP TABLE IF EXISTS `form_xb22`;
CREATE TABLE `form_xb22`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fromlable_xdoe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Lable标签',
  `input_ppq4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `editor_3cot` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '富文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'fdsf' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_xb22
-- ----------------------------

-- ----------------------------
-- Table structure for form_xtam
-- ----------------------------
DROP TABLE IF EXISTS `form_xtam`;
CREATE TABLE `form_xtam`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `input_09ae` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_857r` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `input_p1zz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单行文本',
  `textarea_styp` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多行文本',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'fdsaf' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_xtam
-- ----------------------------

-- ----------------------------
-- Table structure for form_xz0z
-- ----------------------------
DROP TABLE IF EXISTS `form_xz0z`;
CREATE TABLE `form_xz0z`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `textarea_galw` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `input_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `input_user_passwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `input_folder` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户目录',
  `switch_upload` tinyint(1) NULL DEFAULT NULL COMMENT '允许上传',
  `select_g233` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉列表',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_xz0z
-- ----------------------------
INSERT INTO `form_xz0z` VALUES ('1636261435603791872', '2023-03-16 15:01:52', 'admin', '简搭云管理', '2023-03-16 15:01:52', 'admin', '简搭云管理', '101', NULL, 'li.dg', '123456', '/home/li.dg', 0, NULL);

-- ----------------------------
-- Table structure for form_z86w
-- ----------------------------
DROP TABLE IF EXISTS `form_z86w`;
CREATE TABLE `form_z86w`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fkh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户',
  `fksnr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客诉内容',
  `fcljy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '处理建议',
  `fcljg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '处理结果',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '知识档案' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_z86w
-- ----------------------------

-- ----------------------------
-- Table structure for form_zmha
-- ----------------------------
DROP TABLE IF EXISTS `form_zmha`;
CREATE TABLE `form_zmha`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `input_nzeo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户名称',
  `select_ck5x` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户来源',
  `input_p1up` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司网址',
  `input_a3vt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系人',
  `input_wt6b` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `input_nlac` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系地址',
  `textarea_fh7x` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_zmha
-- ----------------------------

-- ----------------------------
-- Table structure for listview_29zc
-- ----------------------------
DROP TABLE IF EXISTS `listview_29zc`;
CREATE TABLE `listview_29zc`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fjd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '基地',
  `fbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门',
  `fzw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `fxm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `fsjh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `码 fsr` datetime NULL DEFAULT NULL COMMENT '生日',
  `fjwgzjl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '既往工作经历',
  `fsr` datetime NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_29zc
-- ----------------------------

-- ----------------------------
-- Table structure for listview_4nlf
-- ----------------------------
DROP TABLE IF EXISTS `listview_4nlf`;
CREATE TABLE `listview_4nlf`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `select_1zdb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(产能)',
  `select_g17g` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(逾期信用)',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-客户等级' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_4nlf
-- ----------------------------

-- ----------------------------
-- Table structure for listview_4s0y
-- ----------------------------
DROP TABLE IF EXISTS `listview_4s0y`;
CREATE TABLE `listview_4s0y`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fgylx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '工艺类型',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-主要生产工艺' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_4s0y
-- ----------------------------
INSERT INTO `listview_4s0y` VALUES ('1674244240637263873', '2023-06-29 02:31:47', 'rqq', '阮青青', '2023-06-29 02:31:47', 'rqq', '阮青青', '101', '1674239276726403072', '选项1', '12');
INSERT INTO `listview_4s0y` VALUES ('1693963804938457090', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '选项2', '樊登读书');

-- ----------------------------
-- Table structure for listview_5etj
-- ----------------------------
DROP TABLE IF EXISTS `listview_5etj`;
CREATE TABLE `listview_5etj`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fyf` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '月份',
  `fcl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产量',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-产量' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_5etj
-- ----------------------------
INSERT INTO `listview_5etj` VALUES ('1693963804896514050', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '富士达', '大');

-- ----------------------------
-- Table structure for listview_6wnj
-- ----------------------------
DROP TABLE IF EXISTS `listview_6wnj`;
CREATE TABLE `listview_6wnj`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fyf` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '月份',
  `fcl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产量',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-产量' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_6wnj
-- ----------------------------

-- ----------------------------
-- Table structure for listview_70g3
-- ----------------------------
DROP TABLE IF EXISTS `listview_70g3`;
CREATE TABLE `listview_70g3`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datarelevance_x42k_text` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datarelevance_x42k` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联数据',
  `fromlable_ddkp` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `fromlable_mfky` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成本价',
  `fromlable_jay0` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '低价',
  `fromlable_3axl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市场价',
  `inputnumber_ae4b` int NULL DEFAULT NULL COMMENT '成交数量',
  `input_aule` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成交金额',
  `input_kmnl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合计',
  `input_xw5e` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成本合计',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售列表粉丝打发大水-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_70g3
-- ----------------------------
INSERT INTO `listview_70g3` VALUES ('1623235703440625666', '2023-02-08 16:22:04', 'admin', '简搭云管理', '2023-02-12 20:56:24', 'admin', '简搭云管理', '101', '1623235444404604928', '漫步者音箱', '1623234727639994368', 'YCH2022130111', '300', '500', '620', 10, '720', '7200', '3000');
INSERT INTO `listview_70g3` VALUES ('1623235703507734530', '2023-02-08 16:22:04', 'admin', '简搭云管理', '2023-02-12 20:56:24', 'admin', '简搭云管理', '101', '1623235444404604928', '联想电脑', '1623234439059296256', 'PC20230208001', '2500', '3000', '5000', 2, '4000', '8000', '5000');
INSERT INTO `listview_70g3` VALUES ('1623293791531126786', '2023-02-08 20:12:53', 'admin', '简搭云管理', '2023-02-08 20:12:53', 'admin', '简搭云管理', '101', '1623293520226766848', '联想电脑', '1623234439059296256', 'PC20230208001', '2500', '3000', '5000', 2, '3500', '7000', '5000');
INSERT INTO `listview_70g3` VALUES ('1623293791531126788', '2023-02-08 20:12:53', 'admin', '简搭云管理', '2023-02-08 20:12:53', 'admin', '简搭云管理', '101', '1623293520226766848', '手机', '1623228065013256192', 'HIP20150512001', '1200', '1500', '2000', 3, '5000', '15000', '3600');
INSERT INTO `listview_70g3` VALUES ('1626874862080274433', '2023-02-18 17:22:47', 'admin', '简搭云管理', '2023-02-18 17:22:47', 'admin', '简搭云管理', '101', '1626874647742951424', '漫步者音箱', '1623234727639994368', 'YCH2022130111', '300', '500', '620', 2, '600', '1200', '600');
INSERT INTO `listview_70g3` VALUES ('1635811473036259329', '2023-03-15 09:13:41', 'admin', '简搭云管理', '2023-03-15 09:13:41', 'admin', '简搭云管理', '101', '1635811392652423168', '漫步者音箱', '1623234727639994368', 'YCH2022130111', '300', '500', '620', 0, NULL, '0', '0');
INSERT INTO `listview_70g3` VALUES ('1638730721152839681', '2023-03-23 10:33:44', 'admin', '简搭云管理', '2023-03-23 10:34:41', 'admin', '简搭云管理', '101', '1638730640764809216', '联想电脑', '1623234439059296256', 'PC20230208001', '2500', '3000', '5000', 5, '5343', '26715', '12500');
INSERT INTO `listview_70g3` VALUES ('1640169085504696322', '2023-03-27 09:49:17', 'admin', '简搭云管理', '2023-03-27 09:49:17', 'admin', '简搭云管理', '101', '1640168916327444480', '漫步者音箱', '1623234727639994368', 'YCH2022130111', '300', '500', '620', 1, NULL, '0', '300');
INSERT INTO `listview_70g3` VALUES ('1640597644673134594', '2023-03-28 14:12:14', 'admin', '简搭云管理', '2023-03-30 23:04:17', 'admin', '简搭云管理', '101', '1640597541619085312', '漫步者音箱', '1623234727639994368', 'YCH2022130111', '300', '500', '620', 6, '10', '60', '1800');
INSERT INTO `listview_70g3` VALUES ('1640597644887044098', '2023-03-28 14:12:14', 'admin', '简搭云管理', '2023-03-30 23:04:17', 'admin', '简搭云管理', '101', '1640597541619085312', '手机', '1623228065013256192', 'HIP20150512001', '1200', '1500', '2000', 5, NULL, '0', '6000');

-- ----------------------------
-- Table structure for listview_7ar3
-- ----------------------------
DROP TABLE IF EXISTS `listview_7ar3`;
CREATE TABLE `listview_7ar3`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fjd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '基地',
  `fbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门',
  `fzw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `fxm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `fsjh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `fsr` date NULL DEFAULT NULL COMMENT '生日',
  `fjwgzjl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '既往工作经历',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_7ar3
-- ----------------------------
INSERT INTO `listview_7ar3` VALUES ('1693963804980400129', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '发范德萨', '萨达范德萨', '萨达范德萨', '水电费但是', '但是萨达', '2023-08-02', '范德萨但是电风扇');

-- ----------------------------
-- Table structure for listview_96jx
-- ----------------------------
DROP TABLE IF EXISTS `listview_96jx`;
CREATE TABLE `listview_96jx`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fkh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户',
  `fggc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格	(长)',
  `fggk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格	(宽)',
  `fggh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格	(厚)',
  `fscyq` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生产要求',
  `fxqsl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '需求数量',
  `fypyt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '样品用途',
  `fxqsj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '需求时间',
  `fscsj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生产时间',
  `fsczt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生产状态',
  `fjd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '基地',
  `ffhsj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '发货时间',
  `fkhczt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户处状态',
  `fbz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注(失败原因等其他)',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '新产品推广状态-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_96jx
-- ----------------------------

-- ----------------------------
-- Table structure for listview_9ruv
-- ----------------------------
DROP TABLE IF EXISTS `listview_9ruv`;
CREATE TABLE `listview_9ruv`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_9ruv
-- ----------------------------

-- ----------------------------
-- Table structure for listview_a2ac
-- ----------------------------
DROP TABLE IF EXISTS `listview_a2ac`;
CREATE TABLE `listview_a2ac`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fgylx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '工艺类型',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-主要生产工艺' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_a2ac
-- ----------------------------

-- ----------------------------
-- Table structure for listview_bssb
-- ----------------------------
DROP TABLE IF EXISTS `listview_bssb`;
CREATE TABLE `listview_bssb`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fyf` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '月份',
  `fcl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产量',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-产量' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_bssb
-- ----------------------------

-- ----------------------------
-- Table structure for listview_dczm
-- ----------------------------
DROP TABLE IF EXISTS `listview_dczm`;
CREATE TABLE `listview_dczm`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `input_f9ou` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年份',
  `input_uzjo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售收入',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-收入情况' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_dczm
-- ----------------------------
INSERT INTO `listview_dczm` VALUES ('1674241625622937601', '2023-06-29 02:21:24', 'rqq', '阮青青', '2023-06-29 02:31:47', 'rqq', '阮青青', '101', '1674239276726403072', '2022', '1000');
INSERT INTO `listview_dczm` VALUES ('1674241625639714817', '2023-06-29 02:21:24', 'rqq', '阮青青', '2023-06-29 02:31:47', 'rqq', '阮青青', '101', '1674239276726403072', '2023', '1400');
INSERT INTO `listview_dczm` VALUES ('1693963804879736835', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '范德萨', '萨达范德萨');

-- ----------------------------
-- Table structure for listview_fd0q
-- ----------------------------
DROP TABLE IF EXISTS `listview_fd0q`;
CREATE TABLE `listview_fd0q`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产业',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-产业链' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_fd0q
-- ----------------------------

-- ----------------------------
-- Table structure for listview_fw2n
-- ----------------------------
DROP TABLE IF EXISTS `listview_fw2n`;
CREATE TABLE `listview_fw2n`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `input_xxkg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年份',
  `input_c28f` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售收入',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-收入情况' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_fw2n
-- ----------------------------

-- ----------------------------
-- Table structure for listview_j0hs
-- ----------------------------
DROP TABLE IF EXISTS `listview_j0hs`;
CREATE TABLE `listview_j0hs`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `select_wfc9` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(产能)',
  `select_zch2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(逾期信用)',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-客户等级' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_j0hs
-- ----------------------------
INSERT INTO `listview_j0hs` VALUES ('1674241625610354689', '2023-06-29 02:21:24', 'rqq', '阮青青', '2023-06-29 02:31:47', 'rqq', '阮青青', '101', '1674239276726403072', 'B', '3');
INSERT INTO `listview_j0hs` VALUES ('1693963804862959617', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', 'A', '2');

-- ----------------------------
-- Table structure for listview_j4xz
-- ----------------------------
DROP TABLE IF EXISTS `listview_j4xz`;
CREATE TABLE `listview_j4xz`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fnf` datetime NULL DEFAULT NULL COMMENT '年份',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-产能' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_j4xz
-- ----------------------------

-- ----------------------------
-- Table structure for listview_k4br
-- ----------------------------
DROP TABLE IF EXISTS `listview_k4br`;
CREATE TABLE `listview_k4br`  (
  `fid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `actinsid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `procinsno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请假表单-子表控件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of listview_k4br
-- ----------------------------

-- ----------------------------
-- Table structure for listview_l68b
-- ----------------------------
DROP TABLE IF EXISTS `listview_l68b`;
CREATE TABLE `listview_l68b`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `select_0965` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(产能)',
  `select_957v` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客户等级(逾期信用)',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-客户等级' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_l68b
-- ----------------------------

-- ----------------------------
-- Table structure for listview_lh42
-- ----------------------------
DROP TABLE IF EXISTS `listview_lh42`;
CREATE TABLE `listview_lh42`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fjd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '基地',
  `fbm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门',
  `fzw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `fxm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `fsjh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `fsr` datetime NULL DEFAULT NULL COMMENT '生日',
  `fjwgzjl` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '既往工作经历',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_lh42
-- ----------------------------

-- ----------------------------
-- Table structure for listview_m15m
-- ----------------------------
DROP TABLE IF EXISTS `listview_m15m`;
CREATE TABLE `listview_m15m`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fxh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '序号',
  `fbhgwl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '不合格物料',
  `flx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型',
  `select_hs5r` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件类型',
  `fjjlx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '降级类型',
  `fc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '长',
  `fk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '宽',
  `fh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '厚',
  `fqxlx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '缺陷类型',
  `fsl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数量',
  `fsldw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数量单位',
  `fdj` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单价',
  `fdjbz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单价币种',
  `fje` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '金额',
  `fhl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '汇率',
  `frmbje` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '人民币金额',
  `fsfwgsjjy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否为公司间交易',
  `fnbgsjrmbje` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内部公司间人民币金额',
  `fsapdh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'SAP单号',
  `fbz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户售后-物料明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_m15m
-- ----------------------------

-- ----------------------------
-- Table structure for listview_mfal
-- ----------------------------
DROP TABLE IF EXISTS `listview_mfal`;
CREATE TABLE `listview_mfal`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fpr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'PR',
  `fhzhbgn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '合作伙伴功能',
  `fbh` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '编号',
  `fms` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `fhzhbms` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '合作伙伴描述',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案_销售视图-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_mfal
-- ----------------------------

-- ----------------------------
-- Table structure for listview_mg28
-- ----------------------------
DROP TABLE IF EXISTS `listview_mg28`;
CREATE TABLE `listview_mg28`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产业',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-产业链' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_mg28
-- ----------------------------
INSERT INTO `listview_mg28` VALUES ('1693963804930068482', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '选项1', '富士达');

-- ----------------------------
-- Table structure for listview_mr1c
-- ----------------------------
DROP TABLE IF EXISTS `listview_mr1c`;
CREATE TABLE `listview_mr1c`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `input_9f7n` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年份',
  `input_eja5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '销售收入',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-收入情况' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_mr1c
-- ----------------------------

-- ----------------------------
-- Table structure for listview_p4sw
-- ----------------------------
DROP TABLE IF EXISTS `listview_p4sw`;
CREATE TABLE `listview_p4sw`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产业',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-产业链' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_p4sw
-- ----------------------------

-- ----------------------------
-- Table structure for listview_qjz1
-- ----------------------------
DROP TABLE IF EXISTS `listview_qjz1`;
CREATE TABLE `listview_qjz1`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fnf` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '年份',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-产能' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_qjz1
-- ----------------------------

-- ----------------------------
-- Table structure for listview_ru5o
-- ----------------------------
DROP TABLE IF EXISTS `listview_ru5o`;
CREATE TABLE `listview_ru5o`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fgylx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '工艺类型',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '-子表控件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_ru5o
-- ----------------------------

-- ----------------------------
-- Table structure for listview_uaq1
-- ----------------------------
DROP TABLE IF EXISTS `listview_uaq1`;
CREATE TABLE `listview_uaq1`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售列表粉丝打发大水-呃454 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of listview_uaq1
-- ----------------------------

-- ----------------------------
-- Table structure for listview_uq8x
-- ----------------------------
DROP TABLE IF EXISTS `listview_uq8x`;
CREATE TABLE `listview_uq8x`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fnf` date NULL DEFAULT NULL COMMENT '年份',
  `fcn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产能',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-产能' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_uq8x
-- ----------------------------
INSERT INTO `listview_uq8x` VALUES ('1693963804904902658', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '2023-08-02', '范德萨');

-- ----------------------------
-- Table structure for listview_wo8s
-- ----------------------------
DROP TABLE IF EXISTS `listview_wo8s`;
CREATE TABLE `listview_wo8s`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-竞争概况' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_wo8s
-- ----------------------------
INSERT INTO `listview_wo8s` VALUES ('1693963804955234306', '2023-08-22 20:30:18', 'admin', '简搭云管理', '2023-08-22 20:30:18', 'admin', '简搭云管理', '103', '1693963559975706624', '选项1', '富士达');

-- ----------------------------
-- Table structure for listview_y1d2
-- ----------------------------
DROP TABLE IF EXISTS `listview_y1d2`;
CREATE TABLE `listview_y1d2`  (
  `fid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fcreationdate` datetime NULL DEFAULT NULL,
  `fcreateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fcreatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatedate` datetime NULL DEFAULT NULL,
  `flastupdateby` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `flastupdatebyname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `organcode` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fparentid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `fgs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司',
  `fzb` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '占比',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户档案扩充-竞争概况' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of listview_y1d2
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
