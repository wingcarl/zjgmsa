/*
Navicat MySQL Data Transfer

Source Server         : zhzx
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : jeesite

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-01-26 16:51:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `spring_data`
-- ----------------------------
DROP TABLE IF EXISTS `spring_data`;
CREATE TABLE `spring_data` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `ship_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '船舶数',
  `ship_person_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '客位数',
  `ship_num_per` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '船舶数同期比',
  `ship_person_num_per` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '客位数同期比',
  `person_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '完成客运量',
  `person_num_per` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '客运量与去年同期比',
  `car_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '运送车辆',
  `car_num_per` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '车运量与去年同期比',
  `accident_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '重特大安全事故次数',
  `injury_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '伤',
  `dead_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '亡',
  `missing_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '失踪',
  `zfry_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '参加执法人员数',
  `zfcar_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '出动执法车辆',
  `zfship_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '出动执法船艇',
  `xhsj` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '海巡艇巡航时间',
  `xhlc` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '海巡艇巡航里程',
  `illegal_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '查处违章行为',
  `xq_num` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '辖区事故险情',
  `fbzl` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '发放宣传材料',
  `aqxx` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '发布安全信息',
  `input_date` date DEFAULT NULL COMMENT '录入时间',
  `create_office` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建部门',
  `summary` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '当日春运主要工作情况简介 ',
  PRIMARY KEY (`id`),
  KEY `test_data_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='春运数据信息表';

-- ----------------------------
-- Records of spring_data
-- ----------------------------
INSERT INTO `spring_data` VALUES ('dc19a424781d4938aea47f252b831ae4', '1', '2018-01-26 15:17:35', '1', '2018-01-26 15:17:35', null, '0', '0', '0', null, null, '0', null, '0', null, '0', '0', '0', '0', '2', '1', '3', '5.5', '16.9', '3', '1', '15', '26', '2018-01-26', '2b32fcdb102447d29def506378ed8f8b', '000');
