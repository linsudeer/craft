/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.10.59
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : smartpark_cloud_uac

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 09/04/2020 09:32:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_uac_client_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_client_details`;
CREATE TABLE `tb_uac_client_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端id',
  `client_secret` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端密钥',
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源id',
  `scope` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作用域',
  `authorized_grant_types` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `authorities` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'token有限时间，单位秒',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '刷新token有效时间，单位秒',
  `additional_information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '额外的信息',
  `autoapprove` bit(1) NULL DEFAULT NULL COMMENT '自动审核',
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_client_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_uac_client_details
-- ----------------------------
INSERT INTO `tb_uac_client_details` VALUES (1, 'smartpark-provider-mpc', '$2a$10$1uCu4CrN.zXYZpw2RGwVxOTCyTcCirvGVftC9Cs/497jhZZBxVw96', NULL, 'server', 'client_credentials,refresh_token', NULL, NULL, NULL, NULL, '{}', b'0', NULL, NULL, NULL);
INSERT INTO `tb_uac_client_details` VALUES (2, 'smartpark-provider-uac', '$2a$10$1uCu4CrN.zXYZpw2RGwVxOTCyTcCirvGVftC9Cs/497jhZZBxVw96', '', 'ui', 'password,refresh_token', NULL, '', 1800, 2400, '{}', b'0', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_uac_client_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_client_resource`;
CREATE TABLE `tb_uac_client_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL COMMENT '客户端id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_resource_id_r`(`resource_id`) USING BTREE,
  INDEX `f_client_id`(`client_id`) USING BTREE,
  CONSTRAINT `f_resource_id_r` FOREIGN KEY (`resource_id`) REFERENCES `tb_uac_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_client_id` FOREIGN KEY (`client_id`) REFERENCES `tb_uac_client_details` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户端资源表，每个申请的客户端都有对应的资源权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_uac_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_menu`;
CREATE TABLE `tb_uac_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `app_id` int(11) NULL DEFAULT NULL COMMENT '对应的业务子系统id',
  `menu_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `menu_icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `seq` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_menu_code`(`menu_code`) USING BTREE COMMENT '菜单编码唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_uac_organization
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_organization`;
CREATE TABLE `tb_uac_organization`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `level` int(2) NULL DEFAULT NULL COMMENT '组织层级',
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `org_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门电话',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径,部门id已 点 链接 1.2.3.',
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  `seq` int(11) NULL DEFAULT 0 COMMENT '序号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_uac_organization
-- ----------------------------
INSERT INTO `tb_uac_organization` VALUES (1, NULL, 1, '北京诚志重科技', NULL, '', b'1', 1, NULL, NULL);

-- ----------------------------
-- Table structure for tb_uac_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_resource`;
CREATE TABLE `tb_uac_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源地址',
  `client_id` int(11) NULL DEFAULT NULL COMMENT '资源所属系统',
  `resource_class` int(11) NULL DEFAULT NULL COMMENT '资源分类',
  `resource_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源所属编码',
  `seq` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_client_id_2`(`client_id`) USING BTREE,
  CONSTRAINT `f_client_id_2` FOREIGN KEY (`client_id`) REFERENCES `tb_uac_client_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_uac_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_role`;
CREATE TABLE `tb_uac_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seq` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_role_key`(`role_key`) USING BTREE COMMENT '角色key唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_uac_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_role_menu`;
CREATE TABLE `tb_uac_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_role_id`(`role_id`) USING BTREE,
  INDEX `f_menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `f_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `tb_uac_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `f_role_id` FOREIGN KEY (`role_id`) REFERENCES `tb_uac_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_uac_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_role_resource`;
CREATE TABLE `tb_uac_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_role_id_r`(`role_id`) USING BTREE,
  INDEX `f_resource_id_r_2`(`resource_id`) USING BTREE,
  CONSTRAINT `f_resource_id_r_2` FOREIGN KEY (`resource_id`) REFERENCES `tb_uac_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `f_role_id_r` FOREIGN KEY (`role_id`) REFERENCES `tb_uac_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_uac_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_user`;
CREATE TABLE `tb_uac_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名，唯一',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号，根据规则生成的唯一字符串',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示的名字',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '所属组织id',
  `user_mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `user_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `account_non_expired` bit(1) NULL DEFAULT NULL COMMENT '账号过期',
  `account_non_locked` bit(1) NULL DEFAULT NULL COMMENT '账号锁',
  `credentials_non_expired` bit(1) NULL DEFAULT NULL COMMENT '凭证过期',
  `enabled` bit(1) NOT NULL COMMENT '启用',
  `seq` int(11) NULL DEFAULT 0 COMMENT '0',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE COMMENT '用户名，登录账号',
  INDEX `f_org_id_r`(`org_id`) USING BTREE,
  CONSTRAINT `f_org_id_r` FOREIGN KEY (`org_id`) REFERENCES `tb_uac_organization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_uac_user
-- ----------------------------
INSERT INTO `tb_uac_user` VALUES (1, 'admin', 'admin', '管理员', '$2a$10$qhOlaEC5uToaVKwHQ0rO3ORmYLoDI8wfm4eW/eceXf.G9jbOlfKNe', 1, NULL, NULL, b'1', b'1', b'1', b'1', 0, NULL, '2020-04-07 17:53:40.993000');

-- ----------------------------
-- Table structure for tb_uac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_uac_user_role`;
CREATE TABLE `tb_uac_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_user_id_r`(`user_id`) USING BTREE,
  INDEX `f_role_id_r_2`(`role_id`) USING BTREE,
  CONSTRAINT `f_role_id_r_2` FOREIGN KEY (`role_id`) REFERENCES `tb_uac_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `f_user_id_r` FOREIGN KEY (`user_id`) REFERENCES `tb_uac_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
