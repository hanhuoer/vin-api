/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50727
Source Host           : 127.0.0.1:3306
Source Database       : vin

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-10-10 15:58:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for v_config
-- ----------------------------
DROP TABLE IF EXISTS `v_config`;
CREATE TABLE `v_config` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `key` varchar(256) COLLATE utf8mb4_german2_ci NOT NULL,
  `value` varchar(256) COLLATE utf8mb4_german2_ci NOT NULL,
  `description` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `status` int(32) NOT NULL DEFAULT '1' COMMENT '0: 冻结, 1: 启用',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of v_config
-- ----------------------------
INSERT INTO `v_config` VALUES ('1', 'OAUTH_GITHUB_CLIENT_ID', '', 'Github Oauth Apps of Client ID', '1', '2019-09-15 17:45:46', null);
INSERT INTO `v_config` VALUES ('2', 'OAUTH_GITHUB_CLIENT_SECRET', '', 'Github Oauth Apps of Client Secret', '1', '2019-09-15 17:46:28', null);
INSERT INTO `v_config` VALUES ('3', 'SECURITY_JWT_TOKEN_EXPIRED_SECONDS', '86400', 'JWT token expired seconds', '1', '2019-09-15 18:40:46', null);
INSERT INTO `v_config` VALUES ('4', 'VIN_ABOUT', '1570461042640', 'vin about note id', '1', '2019-10-08 00:00:25', null);
INSERT INTO `v_config` VALUES ('5', 'VIN_LINK', '1570531050405', 'vin link note id', '1', '2019-10-08 18:58:14', null);
INSERT INTO `v_config` VALUES ('6', 'VIN_MASTER', '1', 'vin master user id', '1', '2019-10-08 22:11:12', null);
INSERT INTO `v_config` VALUES ('7', 'environment_dev_oauth_github_client_id', '', '开发环境id', '1', '2019-10-10 13:37:52', null);
INSERT INTO `v_config` VALUES ('8', 'environment_dev_oauth_github_client_secret', '', '开发环境 secret', '1', '2019-10-10 13:37:52', null);
INSERT INTO `v_config` VALUES ('9', 'environment_online_oauth_github_client_id', '', '线上环境id', '1', '2019-10-10 13:37:52', null);
INSERT INTO `v_config` VALUES ('10', 'environment_online_oauth_github_client_secret', '', '线上环境 secret', '1', '2019-10-10 13:37:52', null);

-- ----------------------------
-- Table structure for v_note
-- ----------------------------
DROP TABLE IF EXISTS `v_note`;
CREATE TABLE `v_note` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) NOT NULL,
  `notebook_id` bigint(64) NOT NULL COMMENT '文集关联',
  `note_id` bigint(64) NOT NULL COMMENT '文章随机数, 一般通过这个确定文章',
  `draft_version` int(32) DEFAULT NULL COMMENT '文章草稿版本号',
  `publish_version` int(32) DEFAULT NULL COMMENT '文章发布版本号',
  `is_comment` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否评论, 默认允许, (0: 关闭/不允许, 1: 打开/允许)',
  `is_show` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否展示, 默认展示, (0: 关闭/不展示, 1: 打开/展示)',
  `is_public` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否公开, 默认公开, (0: 关闭/非公开, 1: 打开/公开)',
  `page_visitor` bigint(64) DEFAULT '0' COMMENT 'pv 访问量',
  `unique_visitor` bigint(64) DEFAULT '0' COMMENT 'uv 唯一访问量',
  `status` int(12) NOT NULL DEFAULT '1' COMMENT '预留状态字段, 默认为 1, enable: 1, disable: 0',
  `first_publish_time` datetime DEFAULT NULL COMMENT '首次发布时间',
  `latest_edit_time` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='笔记';

-- ----------------------------
-- Records of v_note
-- ----------------------------
INSERT INTO `v_note` VALUES ('28', '1', '22', '1570461042640', '7', '4', '', '', '', '0', '0', '1', '2019-10-07 23:20:25', '2019-10-08 00:17:30', '2019-10-07 23:10:43', '2019-10-08 00:17:31');
INSERT INTO `v_note` VALUES ('29', '1', '22', '1570531050405', '7', '6', '', '', '', '0', '0', '1', '2019-10-08 18:39:39', '2019-10-08 18:41:14', '2019-10-08 18:37:30', '2019-10-08 18:41:15');

-- ----------------------------
-- Table structure for v_note_draft
-- ----------------------------
DROP TABLE IF EXISTS `v_note_draft`;
CREATE TABLE `v_note_draft` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) NOT NULL,
  `notebook_id` bigint(64) DEFAULT NULL,
  `note_id` bigint(64) NOT NULL COMMENT '笔记关联id',
  `title` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '文章标题, 指源标题',
  `note_abstract` varchar(1024) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '文章摘要, 指源摘要',
  `content` mediumtext COLLATE utf8mb4_german2_ci COMMENT '文章内容, 指源内容',
  `content_html` mediumtext COLLATE utf8mb4_german2_ci COMMENT '文章内容, 指渲染过的文章内容, HTML',
  `version` int(32) NOT NULL COMMENT '草稿版本, 用作版本控制',
  `origin` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '来源, web/phone/else',
  `ip` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT 'ip 地址',
  `first_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='笔记的草稿箱, 用来保存未发布的内容(包含自动保存, 手动保存)';

-- ----------------------------
-- Records of v_note_draft
-- ----------------------------
INSERT INTO `v_note_draft` VALUES ('52', '1', '22', '1570461042640', '2019-10-07 23:10:42', null, '', null, '1', null, null, null, '2019-10-07 23:10:43', null);
INSERT INTO `v_note_draft` VALUES ('53', '1', '22', '1570461042640', 'About', null, '## Hello\n', '<h2 id=\"hello\">Hello</h2>', '2', null, null, null, '2019-10-07 23:11:58', null);
INSERT INTO `v_note_draft` VALUES ('54', '1', '22', '1570461042640', 'About', null, '## Hello', '<h2 id=\"hello\">Hello</h2>', '3', null, null, null, '2019-10-07 23:20:01', null);
INSERT INTO `v_note_draft` VALUES ('55', '1', '22', '1570461042640', 'About', null, '## Hello\n', '<h2 id=\"hello\">Hello</h2>', '4', null, null, null, '2019-10-08 00:16:32', null);
INSERT INTO `v_note_draft` VALUES ('56', '1', '22', '1570461042640', 'About', null, '## Hello\n', '<h2 id=\"hello\">Hello</h2>', '5', null, null, null, '2019-10-08 00:17:04', null);
INSERT INTO `v_note_draft` VALUES ('57', '1', '22', '1570461042640', 'About', null, '## Hello\n', '<h2 id=\"hello\">Hello</h2>', '6', null, null, null, '2019-10-08 00:17:27', null);
INSERT INTO `v_note_draft` VALUES ('58', '1', '22', '1570461042640', 'About', null, '## Hello\n\n', '<h2 id=\"hello\">Hello</h2>', '7', null, null, null, '2019-10-08 00:17:30', null);
INSERT INTO `v_note_draft` VALUES ('59', '1', '22', '1570531050405', '2019-10-08 18:37:30', null, '', null, '1', null, null, null, '2019-10-08 18:37:31', null);
INSERT INTO `v_note_draft` VALUES ('60', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n\n', '<h2 id=\"友情链接\">友情链接</h2>', '2', null, null, null, '2019-10-08 18:39:36', null);
INSERT INTO `v_note_draft` VALUES ('61', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '3', null, null, null, '2019-10-08 18:40:12', null);
INSERT INTO `v_note_draft` VALUES ('62', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '4', null, null, null, '2019-10-08 18:40:23', null);
INSERT INTO `v_note_draft` VALUES ('63', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '5', null, null, null, '2019-10-08 18:40:48', null);
INSERT INTO `v_note_draft` VALUES ('64', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '6', null, null, null, '2019-10-08 18:41:00', null);
INSERT INTO `v_note_draft` VALUES ('65', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '7', null, null, null, '2019-10-08 18:41:14', null);

-- ----------------------------
-- Table structure for v_note_publish
-- ----------------------------
DROP TABLE IF EXISTS `v_note_publish`;
CREATE TABLE `v_note_publish` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) NOT NULL,
  `notebook_id` bigint(64) DEFAULT NULL,
  `note_id` bigint(64) NOT NULL COMMENT '笔记关联id',
  `title` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '文章标题, 指源标题',
  `note_abstract` varchar(1024) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '文章摘要, 指源摘要',
  `content` mediumtext COLLATE utf8mb4_german2_ci COMMENT '文章内容, 指源内容',
  `content_html` mediumtext COLLATE utf8mb4_german2_ci COMMENT '文章内容, 指渲染过的文章内容, HTML',
  `version` int(32) NOT NULL COMMENT '发布版本, 用作版本控制',
  `origin` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT '来源, web/phone/else',
  `ip` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL COMMENT 'ip 地址',
  `first_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of v_note_publish
-- ----------------------------
INSERT INTO `v_note_publish` VALUES ('54', '1', '22', '1570461042640', 'About', null, '## Hello', '<h2 id=\"hello\">Hello</h2>', '1', null, null, null, '2019-10-07 23:20:24', null);
INSERT INTO `v_note_publish` VALUES ('55', '1', '22', '1570461042640', 'About', null, '## Hello', '<h2 id=\"hello\">Hello</h2>', '2', null, null, null, '2019-10-08 00:16:35', null);
INSERT INTO `v_note_publish` VALUES ('56', '1', '22', '1570461042640', 'About', null, '## Hello', '<h2 id=\"hello\">Hello</h2>', '3', null, null, null, '2019-10-08 00:17:05', null);
INSERT INTO `v_note_publish` VALUES ('58', '1', '22', '1570461042640', 'About', null, '## Hello', '<h2 id=\"hello\">Hello</h2>', '4', null, null, null, '2019-10-08 00:17:31', null);
INSERT INTO `v_note_publish` VALUES ('60', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '1', null, null, null, '2019-10-08 18:39:38', null);
INSERT INTO `v_note_publish` VALUES ('61', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '2', null, null, null, '2019-10-08 18:40:13', null);
INSERT INTO `v_note_publish` VALUES ('62', '1', '22', '1570531050405', 'Links', null, '## 友情链接\n', '<h2 id=\"友情链接\">友情链接</h2>', '3', null, null, null, '2019-10-08 18:40:24', null);
INSERT INTO `v_note_publish` VALUES ('63', '1', '22', '1570531050405', 'Links', null, '## 友情链接', '<h2 id=\"友情链接\">友情链接</h2>', '4', null, null, null, '2019-10-08 18:40:49', null);
INSERT INTO `v_note_publish` VALUES ('64', '1', '22', '1570531050405', 'Links', null, '## 友情链接', '<h2 id=\"友情链接\">友情链接</h2>', '5', null, null, null, '2019-10-08 18:41:01', null);
INSERT INTO `v_note_publish` VALUES ('65', '1', '22', '1570531050405', 'Links', null, '## 友情链接', '<h2 id=\"友情链接\">友情链接</h2>', '6', null, null, null, '2019-10-08 18:41:15', null);

-- ----------------------------
-- Table structure for v_note_tag
-- ----------------------------
DROP TABLE IF EXISTS `v_note_tag`;
CREATE TABLE `v_note_tag` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(64) NOT NULL COMMENT '标签编号',
  `note_id` bigint(64) NOT NULL COMMENT '笔记编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of v_note_tag
-- ----------------------------

-- ----------------------------
-- Table structure for v_notebook
-- ----------------------------
DROP TABLE IF EXISTS `v_notebook`;
CREATE TABLE `v_notebook` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) NOT NULL COMMENT '关联用户',
  `name` varchar(256) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '文集名',
  `status` int(12) NOT NULL DEFAULT '1' COMMENT '文集状态(默认为0): 0: disable, 1: enable, 2: 未知',
  `type` int(32) NOT NULL DEFAULT '1' COMMENT '类型，1：默认，2：系统（不对外开放）',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci COMMENT='笔记本, 文集';

-- ----------------------------
-- Records of v_notebook
-- ----------------------------
INSERT INTO `v_notebook` VALUES ('22', '1', 'Vin', '1', '2', '2019-10-07 23:10:40', null);

-- ----------------------------
-- Table structure for v_role
-- ----------------------------
DROP TABLE IF EXISTS `v_role`;
CREATE TABLE `v_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `description` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `status` int(32) DEFAULT '1' COMMENT '0: 冻结, 1: 启用',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of v_role
-- ----------------------------
INSERT INTO `v_role` VALUES ('1', 'admin', 'admin', '1', '2019-09-15 17:30:17', null);

-- ----------------------------
-- Table structure for v_tag
-- ----------------------------
DROP TABLE IF EXISTS `v_tag`;
CREATE TABLE `v_tag` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8mb4_german2_ci NOT NULL COMMENT '标签名',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of v_tag
-- ----------------------------
INSERT INTO `v_tag` VALUES ('1', 'Java', '2019-10-07 16:46:28', null);
INSERT INTO `v_tag` VALUES ('2', 'Python', '2019-10-07 16:47:06', null);
INSERT INTO `v_tag` VALUES ('3', 'C', '2019-10-07 16:47:30', null);
INSERT INTO `v_tag` VALUES ('4', 'C++', '2019-10-07 16:47:37', null);
INSERT INTO `v_tag` VALUES ('5', 'Go', '2019-10-07 16:47:44', null);
INSERT INTO `v_tag` VALUES ('6', 'CSS', '2019-10-07 16:47:54', null);
INSERT INTO `v_tag` VALUES ('7', '大数据', '2019-10-07 16:48:13', null);
INSERT INTO `v_tag` VALUES ('8', 'SpringBoot', '2019-10-07 16:48:40', null);
INSERT INTO `v_tag` VALUES ('9', 'SpringCloud', '2019-10-07 16:48:53', null);
INSERT INTO `v_tag` VALUES ('10', 'JavaScript', '2019-10-07 16:49:10', null);
INSERT INTO `v_tag` VALUES ('11', 'Html', '2019-10-07 16:49:18', null);
INSERT INTO `v_tag` VALUES ('12', '测试', '2019-10-07 16:51:21', null);

-- ----------------------------
-- Table structure for v_user
-- ----------------------------
DROP TABLE IF EXISTS `v_user`;
CREATE TABLE `v_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `password` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `avatar` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `name` varchar(256) COLLATE utf8mb4_german2_ci DEFAULT NULL,
  `role_id` bigint(64) DEFAULT NULL,
  `github_id` bigint(64) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

-- ----------------------------
-- Records of v_user
-- ----------------------------
