/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50548
Source Host           : 127.0.0.1:3306
Source Database       : eng

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2017-04-07 10:14:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `role_ids` varchar(500) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_auth_user` (`user_id`),
  KEY `idx_sys_auth_group` (`group_id`),
  KEY `idx_sys_auth_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES ('1', '1', '0', '1', 'user');
INSERT INTO `sys_auth` VALUES ('2', '2', '0', '2', 'user');
INSERT INTO `sys_auth` VALUES ('3', '3', '0', '3', 'user');
INSERT INTO `sys_auth` VALUES ('4', '4', '0', '4', 'user');
INSERT INTO `sys_auth` VALUES ('5', '5', '0', '5', 'user');
INSERT INTO `sys_auth` VALUES ('6', '6', '0', '6', 'user');
INSERT INTO `sys_auth` VALUES ('7', '7', '0', '7', 'user');
INSERT INTO `sys_auth` VALUES ('8', '8', '0', '8', 'user');
INSERT INTO `sys_auth` VALUES ('9', '9', '0', '9', 'user');
INSERT INTO `sys_auth` VALUES ('10', '10', '0', '10', 'user');

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `is_show` tinyint(1) DEFAULT NULL,
  `default_group` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_group_type` (`type`),
  KEY `idx_sys_group_show` (`is_show`),
  KEY `idx_sys_group_default_group` (`default_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_group
-- ----------------------------

-- ----------------------------
-- Table structure for sys_group_resource_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_resource_permission`;
CREATE TABLE `sys_group_resource_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `permission_ids` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_role_resource_permission` (`group_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_group_resource_permission
-- ----------------------------
INSERT INTO `sys_group_resource_permission` VALUES ('1', '1', '2', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('2', '1', '16', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('3', '1', '33', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('4', '1', '39', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('5', '2', '2', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('6', '3', '16', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('7', '4', '33', '1');
INSERT INTO `sys_group_resource_permission` VALUES ('8', '5', '2', '2,5');
INSERT INTO `sys_group_resource_permission` VALUES ('9', '5', '16', '2,5');
INSERT INTO `sys_group_resource_permission` VALUES ('10', '5', '33', '2,5');
INSERT INTO `sys_group_resource_permission` VALUES ('11', '5', '39', '2,5');
INSERT INTO `sys_group_resource_permission` VALUES ('12', '6', '2', '3,5');
INSERT INTO `sys_group_resource_permission` VALUES ('13', '6', '16', '3,5');
INSERT INTO `sys_group_resource_permission` VALUES ('14', '6', '33', '3,5');
INSERT INTO `sys_group_resource_permission` VALUES ('15', '6', '39', '3,5');
INSERT INTO `sys_group_resource_permission` VALUES ('16', '7', '2', '4,5');
INSERT INTO `sys_group_resource_permission` VALUES ('17', '7', '16', '4,5');
INSERT INTO `sys_group_resource_permission` VALUES ('18', '7', '33', '4,5');
INSERT INTO `sys_group_resource_permission` VALUES ('19', '7', '39', '4,5');
INSERT INTO `sys_group_resource_permission` VALUES ('20', '8', '2', '5');
INSERT INTO `sys_group_resource_permission` VALUES ('21', '8', '16', '5');
INSERT INTO `sys_group_resource_permission` VALUES ('22', '8', '33', '5');
INSERT INTO `sys_group_resource_permission` VALUES ('23', '8', '39', '5');
INSERT INTO `sys_group_resource_permission` VALUES ('24', '9', '7', '5,6');
INSERT INTO `sys_group_resource_permission` VALUES ('25', '9', '14', '5,6');
INSERT INTO `sys_group_resource_permission` VALUES ('26', '9', '15', '5,6');
INSERT INTO `sys_group_resource_permission` VALUES ('27', '10', '39', '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `is_show` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_permission_name` (`name`),
  KEY `idx_sys_permission_permission` (`permission`),
  KEY `idx_sys_permission_show` (`is_show`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '所有', '*', '所有数据操作的权限', '1');
INSERT INTO `sys_permission` VALUES ('2', '新增', 'create', '新增数据操作的权限', '1');
INSERT INTO `sys_permission` VALUES ('3', '修改', 'update', '修改数据操作的权限', '1');
INSERT INTO `sys_permission` VALUES ('4', '删除', 'delete', '删除数据操作的权限', '1');
INSERT INTO `sys_permission` VALUES ('5', '查看', 'view', '查看数据操作的权限', '1');
INSERT INTO `sys_permission` VALUES ('6', '审核', 'audit', '审核数据操作的权限', '1');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'type:hidden;valid:required',
  `name` varchar(100) NOT NULL COMMENT 'label:资源名称;type:input;valid:required',
  `type` varchar(50) NOT NULL COMMENT 'label:资源类型;type:select;valid:required',
  `url` varchar(200) DEFAULT NULL COMMENT 'label:跳转链接;type:input',
  `weight` int(11) NOT NULL DEFAULT '0' COMMENT 'label:权重;type:input',
  `parent_id` bigint(20) NOT NULL COMMENT 'label:父节点;type:input',
  `parent_ids` varchar(100) DEFAULT NULL COMMENT 'label:路径;type:input',
  `permission` varchar(100) NOT NULL COMMENT 'label:权限;type:select;valid:required',
  `available` bit(1) NOT NULL DEFAULT b'0' COMMENT 'label:是否可用;type:radio;valid:required',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=849912024056999942 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '资源', 'menu', '', '0', '0', '0/', '', '', null, null);
INSERT INTO `sys_resource` VALUES ('11', '组织机构管理', 'menu', '/organization', '0', '1', '0/1/', 'organization:*', '', null, null);
INSERT INTO `sys_resource` VALUES ('12', '组织机构新增', 'button', '', '0', '11', '0/1/11/', 'organization:create', '', null, null);
INSERT INTO `sys_resource` VALUES ('13', '组织机构修改', 'button', '', '0', '11', '0/1/11/', 'organization:update', '', null, null);
INSERT INTO `sys_resource` VALUES ('14', '组织机构删除', 'button', '', '0', '11', '0/1/11/', 'organization:delete', '', null, null);
INSERT INTO `sys_resource` VALUES ('15', '组织机构查看', 'button', '', '0', '11', '0/1/11/', 'organization:view', '', null, null);
INSERT INTO `sys_resource` VALUES ('21', '用户管理', 'menu', '/user', '0', '1', '0/1/', 'user:*', '', null, null);
INSERT INTO `sys_resource` VALUES ('22', '用户新增', 'button', '', '0', '21', '0/1/21/', 'user:create', '', null, null);
INSERT INTO `sys_resource` VALUES ('23', '用户修改', 'button', '', '0', '21', '0/1/21/', 'user:update', '', null, null);
INSERT INTO `sys_resource` VALUES ('24', '用户删除', 'button', '', '0', '21', '0/1/21/', 'user:delete', '', null, null);
INSERT INTO `sys_resource` VALUES ('25', '用户查看', 'button', '', '0', '21', '0/1/21/', 'user:view', '', null, null);
INSERT INTO `sys_resource` VALUES ('31', '资源管理', 'menu', '/resource', '0', '1', '0/1/', 'resource:*', '', null, null);
INSERT INTO `sys_resource` VALUES ('32', '资源新增', 'button', '', '0', '31', '0/1/31/', 'resource:create', '', null, null);
INSERT INTO `sys_resource` VALUES ('33', '资源修改', 'button', '', '0', '31', '0/1/31/', 'resource:update', '', null, null);
INSERT INTO `sys_resource` VALUES ('34', '资源删除', 'button', '', '0', '31', '0/1/31/', 'resource:delete', '', null, null);
INSERT INTO `sys_resource` VALUES ('35', '资源查看', 'button', '', '0', '31', '0/1/31/', 'resource:view', '', null, null);
INSERT INTO `sys_resource` VALUES ('41', '角色管理', 'menu', '/role', '0', '1', '0/1/', 'role:*', '', null, null);
INSERT INTO `sys_resource` VALUES ('42', '角色新增', 'button', '', '0', '41', '0/1/41/', 'role:create', '', null, null);
INSERT INTO `sys_resource` VALUES ('43', '角色修改', 'button', '', '0', '41', '0/1/41/', 'role:update', '', null, null);
INSERT INTO `sys_resource` VALUES ('44', '角色删除', 'button', '', '0', '41', '0/1/41/', 'role:delete', '', null, null);
INSERT INTO `sys_resource` VALUES ('45', '角色查看', 'button', '', '0', '41', '0/1/41/', 'role:view', '', null, null);
INSERT INTO `sys_resource` VALUES ('46', 'URL管理', 'menu', '/urlFilter', '0', '1', '0/1/', 'urlFilter:*', '', null, null);
INSERT INTO `sys_resource` VALUES ('47', 'URL新增', 'button', '', '0', '46', '0/1/46/', 'urlFilter:create', '', null, null);
INSERT INTO `sys_resource` VALUES ('48', 'URL修改', 'button', '', '0', '46', '0/1/46/', 'urlFilter:update', '', null, null);
INSERT INTO `sys_resource` VALUES ('49', 'URL删除', 'button', '', '0', '46', '0/1/46/', 'urlFilter:delete', '', null, null);
INSERT INTO `sys_resource` VALUES ('50', 'URL查看', 'button', '', '0', '46', '0/1/46/', 'urlFilter:view', '', null, null);
INSERT INTO `sys_resource` VALUES ('849912024056999938', '新节点', '菜单', null, '4', '46', '0/1/46/', '', '', '2017-04-06 17:31:34', '2017-04-06 17:31:34');
INSERT INTO `sys_resource` VALUES ('849912024056999939', '新节点', '菜单', null, '5', '46', '0/1/46/', '', '', '2017-04-06 17:38:31', '2017-04-06 17:38:31');
INSERT INTO `sys_resource` VALUES ('849912024056999940', '新节点', '菜单', null, '6', '46', '0/1/46/', '', '', '2017-04-06 17:54:46', '2017-04-06 17:54:46');
INSERT INTO `sys_resource` VALUES ('849912024056999941', '新节点', '菜单', null, '7', '46', '0/1/46/', '', '', '2017-04-06 18:02:04', '2017-04-06 18:02:04');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'type:hidden;valid:required',
  `role` varchar(100) NOT NULL COMMENT 'label:角色;type:input;valid:required',
  `description` varchar(100) DEFAULT NULL COMMENT 'label:描述;type:textarea',
  `resource_ids` varchar(100) DEFAULT NULL COMMENT 'label:资源;type:tree',
  `available` tinyint(1) DEFAULT '0' COMMENT 'label:是否可用;type:radio;valid:required',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '11,21,31,41,46', '1', null, null);
INSERT INTO `sys_role` VALUES ('2', 'a', 'a', '46,', '0', null, null);

-- ----------------------------
-- Table structure for sys_role_resource_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource_permission`;
CREATE TABLE `sys_role_resource_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `permission_ids` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_sys_role_resource_permission` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource_permission
-- ----------------------------
INSERT INTO `sys_role_resource_permission` VALUES ('1', '1', '2', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('2', '1', '16', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('3', '1', '33', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('4', '1', '39', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('5', '2', '2', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('6', '3', '16', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('7', '4', '33', '1');
INSERT INTO `sys_role_resource_permission` VALUES ('8', '5', '2', '2,5');
INSERT INTO `sys_role_resource_permission` VALUES ('9', '5', '16', '2,5');
INSERT INTO `sys_role_resource_permission` VALUES ('10', '5', '33', '2,5');
INSERT INTO `sys_role_resource_permission` VALUES ('11', '5', '39', '2,5');
INSERT INTO `sys_role_resource_permission` VALUES ('12', '6', '2', '3,5');
INSERT INTO `sys_role_resource_permission` VALUES ('13', '6', '16', '3,5');
INSERT INTO `sys_role_resource_permission` VALUES ('14', '6', '33', '3,5');
INSERT INTO `sys_role_resource_permission` VALUES ('15', '6', '39', '3,5');
INSERT INTO `sys_role_resource_permission` VALUES ('16', '7', '2', '4,5');
INSERT INTO `sys_role_resource_permission` VALUES ('17', '7', '16', '4,5');
INSERT INTO `sys_role_resource_permission` VALUES ('18', '7', '33', '4,5');
INSERT INTO `sys_role_resource_permission` VALUES ('19', '7', '39', '4,5');
INSERT INTO `sys_role_resource_permission` VALUES ('20', '8', '2', '5');
INSERT INTO `sys_role_resource_permission` VALUES ('21', '8', '16', '5');
INSERT INTO `sys_role_resource_permission` VALUES ('22', '8', '33', '5');
INSERT INTO `sys_role_resource_permission` VALUES ('23', '8', '39', '5');
INSERT INTO `sys_role_resource_permission` VALUES ('24', '9', '7', '5,6');
INSERT INTO `sys_role_resource_permission` VALUES ('25', '9', '14', '5,6');
INSERT INTO `sys_role_resource_permission` VALUES ('26', '9', '15', '5,6');
INSERT INTO `sys_role_resource_permission` VALUES ('27', '10', '39', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'type:hidden;valid:required',
  `username` varchar(100) NOT NULL COMMENT 'label:用户名;type:input;valid:username',
  `password` varchar(100) NOT NULL COMMENT 'label:密码;type:password;valid:pwd',
  `salt` varchar(100) NOT NULL COMMENT 'type:hidden',
  `role_ids` varchar(100) NOT NULL COMMENT 'label:角色;type:checkbox;valid:required',
  `locked` bit(1) NOT NULL DEFAULT b'0' COMMENT 'label:是否锁定;type:radio;valid:required',
  `nickname` varchar(100) DEFAULT NULL COMMENT 'label:昵称;type:input',
  `avatar` varchar(255) DEFAULT NULL COMMENT 'label:头像;type:image;valid:required',
  `email` varchar(255) DEFAULT NULL COMMENT 'label:邮箱;type:input;valid:email',
  `mobile` varchar(20) DEFAULT NULL COMMENT 'label:手机;type:input;valid:phone',
  `qq` varchar(20) DEFAULT NULL COMMENT 'label:QQ;type:input;valid:qq',
  `wechat` varchar(100) DEFAULT NULL COMMENT 'label:微信;type:input',
  `create_time` datetime NOT NULL COMMENT 'type:hidden',
  `modified_time` datetime NOT NULL COMMENT 'type:hidden',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT 'label:是否删除;type:radio;valid:required',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=849463493936562177 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('2', 'aa', '66be3f270c3e89068a91ab5c27e3172f', '723386cab2b93c03f48a796d0bab0d54', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '');
INSERT INTO `sys_user` VALUES ('3', 'bb', '66be3f270c3e89068a91ab5c27e3172f', '723386cab2b93c03f48a796d0bab0d54', '1', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '');
INSERT INTO `sys_user` VALUES ('4', '123456', '6104d36380ae6f464826508978a91e27', '43d3a98184a94d1f66da2f7523f61345', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('5', '6666', '096fcfb26e007f2feecce34adb8b8304', 'e7d7fec5dfe807a540386c0b3b218b14', '1,2', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('6', 'admin2', '1547df4992ed26198b82dc900fdfcb0d', '25ec10dd040506b31ff1096d300d43a5', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('7', 'admin1', '8d4ebe9f782b98382950b7b4a26cb76b', 'f6a8fc9ddc8d67557a24938afd27251d', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('8', '222', '0072b1e8cc28dd0cd60d5d341f4afc53', '9ceb72c13ec95a63f658be131b7c5c16', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('9', '', '66399a12e2dbd947968b43534e06e7bd', 'db38d271f6222e89708cdbd82933b80e', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('10', 'xxx', 'cb0ca29d462995927743185ddbddff35', '10dacf91983f075e888d643fbe7d45d0', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('11', '18381063344', '1a5563c42b0be7498754743099a62c5d', '1fc723a7c7f22b2c544e39471b87e5cf', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('12', '18888888888', 'd7d2c05b989bf30d320a078e99ec6a3f', '018afb5226538d523b9a55820feadc92', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('13', '18888888887', '1f7543d5a125da6c4dd8ae0a4df0710f', '82fd98188c0f86d3c0885cc0ff845e3a', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('14', '18381063340', '4cbd55b7b80c620db51ae2d63fde27fc', '9a4af5a0bca50b07364e27d13a4b56d6', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('15', '15172506128', '8e9d8ee783d63a1d7a0875f48b9a64b1', 'f27c1bfcae4e29ceae195bb953f5579b', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('16', '15927475617', '611a9b0cdeb8ecd63399d4e01e2c69a9', '3f102eded13602e7034d822f62fb4194', '', '\0', null, null, null, null, null, null, '2017-03-31 11:26:55', '2017-03-31 11:26:55', '\0');
INSERT INTO `sys_user` VALUES ('849463493936562176', '_rrr1112344', '', '', 'TRUE', '', 'dd', '', '', '', '', '', '2017-04-05 11:27:33', '2017-04-05 11:27:33', '');

-- ----------------------------
-- Table structure for tb_exam
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam`;
CREATE TABLE `tb_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'column:id,label:id,type:hidden,value:null;',
  `type` varchar(50) NOT NULL DEFAULT '0' COMMENT 'column:type,label:试卷类型,type:select,value:null;',
  `name` varchar(255) NOT NULL COMMENT 'column:name,label:试卷名称,type:input,value:null;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam
-- ----------------------------
INSERT INTO `tb_exam` VALUES ('1', '1', '2017年英语一真题');
INSERT INTO `tb_exam` VALUES ('2', 'EnglishFirst', '2016年英语一真题');

-- ----------------------------
-- Table structure for tb_exam_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_content`;
CREATE TABLE `tb_exam_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'column:id,label:id,type:hidden,value:null;',
  `exam_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'column:exam_id,label:试卷ID,type:select,value:null;',
  `type` varchar(20) NOT NULL DEFAULT '0' COMMENT 'column:type,label:题型,type:input,value:null;',
  `article` text NOT NULL COMMENT 'column:article,label:原文,type:textarea,value:null;',
  `translation` text COMMENT 'column:translation,label:翻译,type:textarea,value:null;',
  `listening` varchar(200) DEFAULT NULL COMMENT 'column:translation,label:听力,type:textarea,value:null;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam_content
-- ----------------------------
INSERT INTO `tb_exam_content` VALUES ('18', '1', '完型填空', '<p style=\"margin-top:9px;margin-right:13px;margin-left:10px;text-indent:28px;text-align:justify;text-justify:inter-ideograph;line-height:128%\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">In Cambodia, the choice of a spouse is a complex one for the young male. It may involve not only his parents and his friends, &nbsp;&nbsp;&nbsp;1 &nbsp;&nbsp;&nbsp;those of the young woman, but also a matchmaker. A young man can &nbsp;&nbsp;&nbsp;2 &nbsp;&nbsp;&nbsp;a likely spouse &nbsp;on his own and then ask his parents to 3 &nbsp;the marriage negotiations, or the young man’s parents may take the &nbsp;choice of a spouse, giving the child little to say in the selection. &nbsp;&nbsp;&nbsp;4 &nbsp;&nbsp;&nbsp;, a girl may veto the spouse her parents &nbsp;&nbsp;&nbsp;have chosen. &nbsp;5 &nbsp;a spouse has been selected, each family &nbsp;investigates &nbsp;the other &nbsp;to make &nbsp;sure its &nbsp;child is &nbsp;marrying &nbsp;&nbsp;&nbsp;&nbsp;6 &nbsp;&nbsp;&nbsp;&nbsp;a good</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:-1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">family.</span></p><p style=\"margin-top:0;margin-right:13px;margin-left:10px;text-indent:28px;text-align:left;line-height:128%\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">The traditional wedding is a long and colorful affair. Formerly it lasted three</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">days, &nbsp;&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:3px;font-size:14px\">&nbsp;</span><span style=\"text-decoration:underline;\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;text-underline:single;font-size:14px\">7</span></span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">1980s it</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:-0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">more</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:-0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">comm</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">only lasted a day and a half. Buddhist priests offer a short sermon</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:3px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">and &nbsp;&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:3px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">8</span><span style=\"text-decoration:underline;\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;text-underline:single;font-size:14px\">&nbsp;</span></span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">prayers of blessing. Par--ts of</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">the</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">cer</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">emony involve ritual hair cutting, &nbsp;9 &nbsp;&nbsp;cotton threads soaked in holy water around the bride&#39;s and groom&#39;s wrists, and 10 a candle around a circle of happily married and respected couples to bless the </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:-0;font-size:14px\">11 </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">. &nbsp;Newlyweds t &nbsp;raditionally move in with the wife&#39;s parents and</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">may &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:3px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">12</span><span style=\"text-decoration:underline;\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;text-underline:single;font-size:14px\">&nbsp;</span></span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">with them up to a year, &nbsp;&nbsp;&nbsp;&nbsp;13 &nbsp;&nbsp;&nbsp;they can build a</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">new</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">h</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">ouse</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:-1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">nearby.</span></p><p style=\"margin-top:5px;margin-right:14px;margin-bottom:1px;margin-left:10px;text-indent:0;text-align:left;line-height:128%\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">Divorce is legal and easy to &nbsp;&nbsp;&nbsp;&nbsp;14 &nbsp;&nbsp;&nbsp;, but not common. Divorced persons &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">are &nbsp;&nbsp;&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">15</span><span style=\"text-decoration:underline;\"><span style=\";font-family:&#39;Times New Roman&#39;;text-underline:single;font-size:14px\">&nbsp;</span></span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">with some</span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">disapproval.Each&nbsp; </span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:1px;font-size:14px\"></span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">spouse &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:1px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">retains</span><span style=\"text-decoration:underline;\"><span style=\";font-family:&#39;Times New Roman&#39;;text-underline:single;font-size:14px\">&nbsp;</span></span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">16_property &nbsp;&nbsp;he &nbsp;&nbsp;or&nbsp; she</span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:0;font-size:14px\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">17</span><span style=\"text-decoration:underline;\"><span style=\";font-family:&#39;Times New Roman&#39;;text-underline:single;font-size:14px\">&nbsp;</span></span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">into &nbsp;&nbsp;the &nbsp;&nbsp;marriage, &nbsp;&nbsp;and &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:3px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">jointly-acquired property</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:2px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">is&nbsp; </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">18</span><span style=\"text-decoration:underline;\"> </span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">equally. &nbsp;Divorced &nbsp;persons &nbsp;may &nbsp;remarry, but &nbsp;a &nbsp;gender &nbsp;prejudice &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:0;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">19 &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:3px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">up.</span> <span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">The</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:2px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">divorced</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">male doesn&#39;t have a waiting period before he can remarry &nbsp;&nbsp;&nbsp;&nbsp;20 &nbsp;&nbsp;&nbsp;the woman must wait ten</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;letter-spacing:-2px;font-size:14px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">months.</span></p><p><br/></p>', '<p>无<br/></p>', null);
INSERT INTO `tb_exam_content` VALUES ('19', '1', 'WritingPartA', '<p style=\"margin-top:11px;margin-right:0;margin-left:7px;text-indent:0;text-align:left\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">Directions:</span></p><p style=\"margin-top:5px;margin-right:0;margin-left:7px;text-indent:0;text-align:left;line-height:128%\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">Suppose you are a librarian in your university. Write a notice of about 100 words, providing the newly-enrolled international students with relevant information about the library.</span></p><p style=\"margin-top:0;margin-right:0;margin-left:7px;text-indent:0;text-align:left\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:14px\">You should write neatly on the ANWSER SHEET.</span></p><p style=\"margin-top: 5px; margin-right: 227px; margin-left: 7px; text-indent: 0px; text-align: left; line-height: 128%;\"><span style=\";font-family:&#39;Times New Roman&#39;;line-height:128%;font-size:14px\">Do not sign you own name at the end of the letter, use “Li Ming ” instead. Do not write the address .(10 points)</span></p>', '<h3 style=\"margin-top:2px\"><strong><span style=\";font-family:宋体;font-weight:bold;font-size:16px\"><span style=\"font-family:宋体\">范文：</span></span></strong></h3><p style=\"margin-top:1px;margin-left:0\"><br/></p><p style=\"margin-top:0;margin-right:0;margin-left:7px;text-indent:0;text-align:left\"><strong><span style=\";font-family:&#39;Times New Roman&#39;;font-weight:bold;font-size:16px\">Notice</span></strong></p><p><span style=\";font-family:&#39;Times New Roman&#39;;font-weight:bold;font-size:15px\"><br style=\"page-break-before:always\" clear=\"all\"/></span> </p><p style=\"margin-top:0;margin-left:0\"><strong><span style=\";font-family:&#39;Times New Roman&#39;;font-weight:bold;font-size:15px\">&nbsp;</span></strong></p><p style=\"margin-top:6px;margin-right:7px;margin-left:39px;text-indent:504px;line-height:112%\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">August 20, 2015 </span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:-1px;font-size:16px\">To &nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">ensure &nbsp;students &nbsp;from &nbsp;oversea &nbsp;to &nbsp;be &nbsp;acquainted &nbsp;with &nbsp;the &nbsp;service &nbsp;of &nbsp;library &nbsp;in </span><span style=\";font-family:&#39;Times New Roman&#39;;letter-spacing:4px;font-size:16px\">&nbsp;</span><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">Beijing</span></p><p style=\"margin-top:0;text-align:justify;text-justify:inter-ideograph\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">University, we write this notice to inform you of some relevant information about our library.</span></p><p style=\"margin-top:2px;margin-right:13px;text-align:justify;text-justify:inter-ideograph;line-height:112%\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">To begin with, our library provides a large amount of books and materials covering not only most majors and subjects, but also many extra-curricular reading materials, thus satisfying all &nbsp;your reading requirements. Furthermore, the library opens during the week time, each day from 9:00 am to10:00pm.Last but not least, only students enrolled in this university and with a special Library Car d are allowed to enter into our library.</span></p><p style=\"margin-top:0;text-indent:31px;line-height:112%\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">Anyone interested in studying or reading in our library should sign up their name with their monitor before August 31, and the Library Card will be issued within a week.</span></p><p style=\"margin-top:0;margin-left:455px\"><span style=\";font-family:&#39;Times New Roman&#39;;font-size:16px\">Libraryof Beijing University</span></p><p><br/></p>', '-');
INSERT INTO `tb_exam_content` VALUES ('20', '1', '阅读理解 PartA Text1', '<p>ces<br/></p>', '<p>13<br/></p>', '');

-- ----------------------------
-- Table structure for tb_exam_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_question`;
CREATE TABLE `tb_exam_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'column:id,label:id,type:hidden,value:null;',
  `content_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'column:contentId,label:所属内容,type:select,value:null;',
  `question` varchar(255) NOT NULL COMMENT 'column:question,label:题目,type:input,value:null;',
  `a` varchar(255) DEFAULT NULL COMMENT 'column:a,label:选项A,type:input,value:null;',
  `b` varchar(255) DEFAULT NULL COMMENT 'column:b,label:选项B,type:input,value:null;',
  `c` varchar(255) DEFAULT NULL COMMENT 'column:c,label:选项C,type:input,value:null;',
  `d` varchar(255) DEFAULT NULL COMMENT 'column:d,label:选项D,type:input,value:null;',
  `answer` char(1) DEFAULT NULL COMMENT 'column:answer,label:答案,type:input,value:null;',
  `resolve` varchar(1000) DEFAULT NULL COMMENT 'column:resolve,label:解析,type:textarea,value:null;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam_question
-- ----------------------------
INSERT INTO `tb_exam_question` VALUES ('1', '18', '1', 'A. by way of', 'B. with regard to', 'C. on behalf of', 'D. as well as', 'A', 'A');
INSERT INTO `tb_exam_question` VALUES ('2', '19', '1', 'a', 'b', 'c', 'd', 'c', 'e');

-- ----------------------------
-- Table structure for tb_exam_question_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_question_type`;
CREATE TABLE `tb_exam_question_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'column:id,label:id,type:hidden,value:null;',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT 'column:name,label:题型,type:input,value:null;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam_question_type
-- ----------------------------
INSERT INTO `tb_exam_question_type` VALUES ('1', '完型填空');
INSERT INTO `tb_exam_question_type` VALUES ('2', '阅读理解A');
INSERT INTO `tb_exam_question_type` VALUES ('3', '阅读理解B');
INSERT INTO `tb_exam_question_type` VALUES ('4', '阅读理解C');
INSERT INTO `tb_exam_question_type` VALUES ('5', '阅读理解D');
INSERT INTO `tb_exam_question_type` VALUES ('6', '新题型');
INSERT INTO `tb_exam_question_type` VALUES ('7', '翻译');
INSERT INTO `tb_exam_question_type` VALUES ('8', '小作文');
INSERT INTO `tb_exam_question_type` VALUES ('9', '大作文');

-- ----------------------------
-- Table structure for tb_exam_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_type`;
CREATE TABLE `tb_exam_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'column:id,label:id,type:hidden,value:null;',
  `name` varchar(255) NOT NULL COMMENT 'column:name,label:题型,type:input,value:null;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exam_type
-- ----------------------------
INSERT INTO `tb_exam_type` VALUES ('1', '英语一');
INSERT INTO `tb_exam_type` VALUES ('2', '英语二');
INSERT INTO `tb_exam_type` VALUES ('3', '全部');
