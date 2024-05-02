ALTER TABLE `form_apiconfig`
ADD COLUMN `xls_template_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导入xls模板url' AFTER `is_system`,
ADD COLUMN `xls_template_row_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导入xls 开始行数' AFTER `xls_template_url`,
ADD COLUMN `jsonparam` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数格式JSON' AFTER `xls_template_row_num`;

ALTER TABLE `form_apiconfig_back`
ADD COLUMN `xls_template_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导入xls模板url' AFTER `is_system`,
ADD COLUMN `xls_template_row_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导入xls 开始行数' AFTER `xls_template_url`,
ADD COLUMN `jsonparam` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数格式JSON' AFTER `xls_template_row_num`;