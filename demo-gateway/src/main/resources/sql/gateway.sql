/*
 Navicat Premium Data Transfer

 Source Server         : kid626
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : spring-security

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 07/02/2022 15:21:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由id',
  `route_order` int(0) NULL DEFAULT 0 COMMENT '路由顺序',
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由路径',
  `is_enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否启用',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'N' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网关-路由' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES (1, 'demo-server', 0, 'lb://demo-server', 'Y', 'admin', '2022-01-26 17:23:42', NULL, NULL, 'N');

-- ----------------------------
-- Table structure for gateway_route_param
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route_param`;
CREATE TABLE `gateway_route_param`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由id',
  `param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `param_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数key',
  `param_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数value',
  `type` int(0) NOT NULL COMMENT '1 为predicate，2为 filter',
  `is_enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否生效',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'N' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网关-路由参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_route_param
-- ----------------------------
INSERT INTO `gateway_route_param` VALUES (1, 'demo-server', 'Path', 'pattern', '/api/hi/**', 1, 'Y', 'admin', '2022-01-26 17:26:07', 'admin', '2022-01-26 17:26:07', 'N');
INSERT INTO `gateway_route_param` VALUES (2, 'demo-server', 'RewritePath', 'regexp', '/api/(?<segment>.*)', 2, 'Y', 'admin', '2022-01-26 17:26:32', 'admin', '2022-01-26 17:26:32', 'N');
INSERT INTO `gateway_route_param` VALUES (3, 'demo-server', 'RewritePath', 'replacement', '/$\\{segment}', 2, 'Y', 'admin', '2022-02-07 11:10:53', 'admin', '2022-02-07 11:10:53', 'N');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源编码',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `parent_code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父编码',
  `method` tinyint(0) NULL DEFAULT NULL COMMENT '请求方法(0:全匹配,1:POST,2:DELETE,3:PUT,4:GET)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型标记(0:菜单,1:按钮)',
  `is_enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `create_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (2, 'kungfu', '功夫', '', 4, '/kungfu', 'icon-kongfu', 1, 0, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (3, 'menu-leve1', '第一层', 'kungfu', 4, '/menu-leve1', '', 1, 0, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (4, 'menu-leve2', '第二层', 'kungfu', 4, '/menu-leve2', '', 2, 0, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (5, 'test', '测试', '', 4, '/test', 'icon-test', 2, 0, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (6, 'level4', 'level4', 'kungfu', 4, '/level4/*', '', 4, 1, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (7, 'level2', 'level2', 'kungfu', 4, '/level2/*', '', 2, 1, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (8, 'level1', 'level1', 'kungfu', 4, '/level1/*', '', 1, 1, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');
INSERT INTO `resource` VALUES (9, 'level3', 'level3', 'kungfu', 4, '/level3/*', '', 3, 1, 'Y', 'admin', '2021-12-28 16:32:27', 'admin', '2021-12-28 16:32:27', 'N');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin');
INSERT INTO `role` VALUES (2, '普通用户', 'user');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NOT NULL,
  `resource_id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES (1, 1, 6);
INSERT INTO `role_resource` VALUES (2, 1, 7);
INSERT INTO `role_resource` VALUES (3, 1, 8);
INSERT INTO `role_resource` VALUES (4, 1, 9);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `salt` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `is_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'kPpSDM', 'qBuMUQ6icP7aARdFq+yzOw==', 'Y', 'N', '2021-12-27 16:47:35', 'admin', '2021-12-27 16:47:35', 'admin');
INSERT INTO `user` VALUES (2, 'user', 'vSPaYk', 'w9pVTSFdIhr5G6eozo2osA==', 'Y', 'N', '2021-12-27 16:48:35', 'admin', '2021-12-27 16:48:35', 'admin');
INSERT INTO `user` VALUES (3, 'anon', 'z6M2Pj', 'smRk+K6rkIcm/j8JZ8sNKQ==', 'Y', 'N', '2021-12-27 16:49:26', 'admin', '2021-12-27 16:49:26', 'admin');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `role_id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
