/*
Navicat MySQL Data Transfer

Source Server         : 4444
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : jeesite

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-15 20:38:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `msa_hutong_project`
-- ----------------------------
DROP TABLE IF EXISTS `msa_hutong_project`;
CREATE TABLE `msa_hutong_project` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `content` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `test_data_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='沪通大桥水工动态表';

-- ----------------------------
-- Records of msa_hutong_project
-- ----------------------------
INSERT INTO `msa_hutong_project` VALUES ('33a41b5d494744139ed6089acfb123ca', '1', '2018-01-12 21:42:16', '1', '2018-01-12 21:51:23', null, '0', '沪通长江大桥28#墩主塔施工；沪通长江大桥29#墩主塔施工；26#、27#、30#、31#墩施工结束。123');
INSERT INTO `msa_hutong_project` VALUES ('371071f7300c4bbda668142ff7d0c611', '1', '2018-01-12 21:58:35', '1', '2018-01-12 21:58:35', null, '0', '沪通长江大桥28#墩主塔施工；沪通长江大桥29#墩主塔施工；26#、27#、30#、31#墩施工结束。');

-- ----------------------------
-- Table structure for `msa_tonghang`
-- ----------------------------
DROP TABLE IF EXISTS `msa_tonghang`;
CREATE TABLE `msa_tonghang` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `content` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `test_data_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通航环境表';

-- ----------------------------
-- Records of msa_tonghang
-- ----------------------------
INSERT INTO `msa_tonghang` VALUES ('641466927256468cb720434138dedd9e', '1', '2018-01-12 22:31:20', '1', '2018-01-12 23:13:59', null, '0', '据长江上海航道处18号电： \r\n12月20日，浏海沙水道，根据12月19日测图显示，#42红浮下游1560米至下游1700米航标连线附近水域存在不满足10.5米（理论最低潮面下，下同）的浅点，最浅水深位于#42红浮下游1700米航标连线上为10.4米，请过往船舶注意避浅，以策安全。本通告自发布之日起30天内有效。\r\n12月21日，福姜沙水道（南水道），根据12月20日测图显示，（#49）黑浮至（#52）黑浮航标连线至航道内（20）米水域存在不满足（10.5）米（理论最低潮面下，下同）的浅点，最浅水深位于（#50）黑浮上游（470）米航标连线上为（9.2）米。 \r\n请过往船舶注意避浅，以策安全。');
