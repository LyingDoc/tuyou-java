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

 Date: 04/11/2023 22:48:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for form_question_link
-- ----------------------------
DROP TABLE IF EXISTS `form_question_link`;
CREATE TABLE `form_question_link`  (
  `question_link_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问卷关联ID',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `form_data_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问卷表单Id',
  `form_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表单ID',
  PRIMARY KEY (`question_link_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户提交的问卷' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
