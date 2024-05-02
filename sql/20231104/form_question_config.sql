/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : kylin2.0

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 04/11/2023 22:48:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for form_question_config
-- ----------------------------
DROP TABLE IF EXISTS `form_question_config`;
CREATE TABLE `form_question_config`  (
  `question_config_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问卷配置ID',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `collect_start_time` datetime NULL DEFAULT NULL COMMENT '采集开始时间',
  `collect_end_time` datetime NULL DEFAULT NULL COMMENT '采集结束时间',
  `is_password` bit(1) NULL DEFAULT NULL COMMENT '是否填写密码',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '填写密码',
  `is_login` bit(1) NULL DEFAULT NULL COMMENT '是否登录',
  `is_power` bit(1) NULL DEFAULT NULL COMMENT '是否权限',
  `is_update` bit(1) NULL DEFAULT NULL COMMENT '是否修改',
  `fill_num` int NULL DEFAULT NULL COMMENT '填写次数',
  `form_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问卷表单ID',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态(0草稿，1 发起)',
  PRIMARY KEY (`question_config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问卷调查配置信息' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
