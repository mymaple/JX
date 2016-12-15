/*
Navicat MySQL Data Transfer

Source Server         : jeefwtest
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : jiangxiangdb

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-12-14 00:59:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgConfig
-- ----------------------------
DROP TABLE IF EXISTS `bgConfig`;
CREATE TABLE `bgConfig` (
 	`configId` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统配置id' ,
	`configType` varchar(50)
		 DEFAULT NULL  COMMENT '配置类型' ,	
	`configName` varchar(50)
		 DEFAULT NULL  COMMENT '配置名称' ,	
	`param1` varchar(100)
		 DEFAULT NULL  COMMENT '接入网址' ,	
	`param2` int(10)
		 DEFAULT NULL  COMMENT '端口号/数字' ,	
	`param3` varchar(100)
		 DEFAULT NULL  COMMENT '账号/X' ,	
	`param4` varchar(100)
		 DEFAULT NULL  COMMENT '密码/Y' ,	
	`isOpen` varchar(10)
		 DEFAULT NULL  COMMENT '是否启动' ,	
	`modifyTime` date
		 DEFAULT NULL  COMMENT '修改时间' ,	
  
  PRIMARY KEY (`configId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '系统配置' ;

-- ----------------------------
-- Records of bgConfig
-- ----------------------------
INSERT INTO `bgConfig` VALUES ('1', 'configBgSystem', '系统配置', 'JX1', '11', 'admin', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('2', 'configBgEmailServer', '邮箱服务器配置', 'smt1p.qq.com', '21', 'it1@126.com', '1231', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('3', 'configBgMessage', '短信账户配置', 'http://www.dx1ton.com/', null, 'username1', 'ppp1', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('4', 'configBgWordWaterMark', '文字水印配置', 'JX1', '21', '11', '12', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('5', 'configBgImageWaterMark', '图片水印配置', 'watermark.png', NULL, '14', '13', '0', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('6', 'configBgWeiXin', ' 微信接口配置', '/weixin/index ', NULL, 'token1', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('7', 'configBgInstantChat', '即时聊天服务器配置', '127.0.0.2', '6021', '', '', '1', '2015-06-03 22:09:13');
INSERT INTO `bgConfig` VALUES ('8', 'configBgOnlineManage', '在线管理服务器配置', '127.0.0.3', '6022', '', '', '1', '2015-06-03 22:09:13');

-- ----------------------------
-- Table structure for bgmenu
-- ----------------------------
DROP TABLE IF EXISTS `bgmenu`;
CREATE TABLE `bgmenu` (
  `menuId` int(11) NOT NULL,
  `menuName` varchar(255) DEFAULT NULL,
  `menuUrl` varchar(255) DEFAULT NULL,
  `parentId` varchar(100) DEFAULT NULL,
  `menuOrder` varchar(100) DEFAULT NULL,
  `menuIcon` varchar(30) DEFAULT NULL,
  `menuType` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bgmenu
-- ----------------------------
INSERT INTO `bgmenu` VALUES ('1', '系统管理', '#', '0', '1', 'icon-desktop', '1');
INSERT INTO `bgmenu` VALUES ('2', '组织管理', 'role.do', '1', '2', null, '1');
INSERT INTO `bgmenu` VALUES ('4', '会员管理', 'happuser/listUsers.do', '1', '4', null, '1');
INSERT INTO `bgmenu` VALUES ('5', '系统用户', 'user/listUsers.do', '1', '3', null, '1');
INSERT INTO `bgmenu` VALUES ('6', '信息管理', '#', '0', '2', 'icon-list', '2');
INSERT INTO `bgmenu` VALUES ('7', '图片管理', 'pictures/list.do', '6', '1', null, '2');
INSERT INTO `bgmenu` VALUES ('8', '性能监控', 'druid/index.html', '9', '1', null, '1');
INSERT INTO `bgmenu` VALUES ('9', '系统工具', '#', '0', '3', 'icon-th', '1');
INSERT INTO `bgmenu` VALUES ('10', '接口测试', 'tool/interfaceTest.do', '9', '2', null, '1');
INSERT INTO `bgmenu` VALUES ('11', '发送邮件', 'tool/goSendEmail.do', '9', '3', null, '1');
INSERT INTO `bgmenu` VALUES ('12', '置二维码', 'tool/goTwoDimensionCode.do', '9', '4', null, '1');
INSERT INTO `bgmenu` VALUES ('13', '多级别树', 'tool/ztree.do', '9', '5', null, '1');
INSERT INTO `bgmenu` VALUES ('14', '地图工具', 'tool/map.do', '9', '6', null, '1');
INSERT INTO `bgmenu` VALUES ('15', '微信管理', '#', '0', '2', 'icon-comments', '2');
INSERT INTO `bgmenu` VALUES ('16', '文本回复', 'textmsg/list.do', '15', '2', null, '2');
INSERT INTO `bgmenu` VALUES ('17', '应用命令', 'command/list.do', '15', '4', null, '2');
INSERT INTO `bgmenu` VALUES ('18', '图文回复', 'imgmsg/list.do', '15', '3', null, '2');
INSERT INTO `bgmenu` VALUES ('19', '关注回复', 'textmsg/goSubscribe.do', '15', '1', null, '2');
INSERT INTO `bgmenu` VALUES ('20', '在线管理', 'onlinemanager/list.do', '1', '5', null, '1');
INSERT INTO `bgmenu` VALUES ('21', '打印测试', 'tool/printTest.do', '9', '7', null, '1');

-- ----------------------------
-- Table structure for bgrole
-- ----------------------------
DROP TABLE IF EXISTS `bgrole`;
CREATE TABLE `bgrole` (
  `roleId` varchar(100) NOT NULL,
  `roleName` varchar(100) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `parentId` varchar(100) DEFAULT NULL,
  `addPermission` varchar(255) DEFAULT NULL,
  `delPermission` varchar(255) DEFAULT NULL,
  `editPermission` varchar(255) DEFAULT NULL,
  `findPermission` varchar(255) DEFAULT NULL,
  `permissionId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bgrole
-- ----------------------------
INSERT INTO `bgrole` VALUES ('1', '系统管理员', '4194294', '0', '1', '1', '1', '1', '1');
INSERT INTO `bgrole` VALUES ('2', '超级管理员', '4194294', '1', '230', '50', '34', '54', '2');
INSERT INTO `bgrole` VALUES ('4', '用户组', '118', '0', '0', '0', '0', '0', null);
INSERT INTO `bgrole` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '特级会员', '498', '7', '0', '0', '0', '0', '55896f5ce3c0494fa6850775a4e29ff6');
INSERT INTO `bgrole` VALUES ('6', '客户组', '18', '0', '1', '1', '1', '1', null);
INSERT INTO `bgrole` VALUES ('68f23fc0caee475bae8d52244dea8444', '中级会员', '498', '7', '0', '0', '0', '0', '68f23fc0caee475bae8d52244dea8444');
INSERT INTO `bgrole` VALUES ('7', '会员组', '498', '0', '0', '0', '0', '1', null);
INSERT INTO `bgrole` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '管理员B', '4194294', '1', '246', '0', '0', '0', '7dfd8d1f7b6245d283217b7e63eec9b2');
INSERT INTO `bgrole` VALUES ('ac66961adaa2426da4470c72ffeec117', '管理员A', '4194294', '1', '54', '54', '0', '246', 'ac66961adaa2426da4470c72ffeec117');
INSERT INTO `bgrole` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '高级会员', '498', '7', '0', '0', '0', '0', 'b0c77c29dfa140dc9b14a29c056f824f');
INSERT INTO `bgrole` VALUES ('e74f713314154c35bd7fc98897859fe3', '黄金客户', '18', '6', '1', '1', '1', '1', 'e74f713314154c35bd7fc98897859fe3');
INSERT INTO `bgrole` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '初级会员', '498', '7', '1', '1', '1', '1', 'f944a9df72634249bbcb8cb73b0c9b86');


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bgUser
-- ----------------------------
DROP TABLE IF EXISTS `bgUser`;
CREATE TABLE `bgUser` (
 	`userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户表id',
	`userName` varchar(255)
		 DEFAULT NULL  COMMENT '用户名' ,	
	`password` varchar(255)
		 DEFAULT NULL  COMMENT '密码' ,	
	`name` varchar(255)
		 DEFAULT NULL  COMMENT '名字' ,	
	`rights` varchar(255)
		 DEFAULT NULL  COMMENT '拥有的权限' ,	
	`roleId` int(11)
		 DEFAULT NULL  COMMENT '角色id' ,	
	`lastLoginTime` datetime
		 DEFAULT NULL  COMMENT '上次登录时间' ,	
	`lastLoginIp` varchar(50)
		 DEFAULT NULL  COMMENT '上次登录IP' ,	
	`status` varchar(50)
		 DEFAULT NULL  COMMENT '状态' ,	
	`remarks` varchar(255)
		 DEFAULT NULL  COMMENT '备注' ,	
	`skin` varchar(50)
		 DEFAULT NULL  COMMENT '皮肤' ,	
	`email` varchar(255)
		 DEFAULT NULL  COMMENT '电子邮箱' ,	
	`userNumber` varchar(100)
		 DEFAULT NULL  COMMENT '用户编号' ,	
	`phone` varchar(20)
		 DEFAULT NULL  COMMENT '电话号码' ,	
  `modifyTime` datetime
		 DEFAULT NULL  COMMENT '修改时间' ,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '系统用户表';

INSERT INTO `bguser` VALUES ('1', 'admin', 'de41b7fb99201d8334c23c014db35ecd92df81bc', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2016-12-13 22:23:40', '0:0:0:0:0:0:0:1', '0', '最高统治者', 'default', 'admin@main.com', '001', '18788888888','2016-12-13 22:23:40');

-- ----------------------------
-- Table structure for comdict
-- ----------------------------
DROP TABLE IF EXISTS `comdict`;
CREATE TABLE `comdict` (
  `dictId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `encode` varchar(100) DEFAULT NULL,
  `orderBy` int(10) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `allEncode` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comdict
-- ----------------------------
INSERT INTO `comdict` VALUES ('1', '属性类型', 'propType', '1', '0', '1', 'propType');
INSERT INTO `comdict` VALUES ('2', '字符串String', 'String', '0', '1', '2', 'propType_String');
INSERT INTO `comdict` VALUES ('3', '整形Int', 'Int', '1', '1', '2', 'propType_Int');
INSERT INTO `comdict` VALUES ('4', '日期型Date', 'Date', '2', '1', '2', 'propType_Date');
INSERT INTO `comdict` VALUES ('5', '开发模块', 'developModule', '0', '0', '1', 'developModule');
INSERT INTO `comdict` VALUES ('6', 'common', 'com', '0', '5', '2', 'developModule_com');
INSERT INTO `comdict` VALUES ('7', 'background', 'bg', '1', '5', '2', 'developModule_bg');
INSERT INTO `comdict` VALUES ('8', 'weixin', 'wx', '2', '5', '2', 'developModule_wx');
INSERT INTO `comdict` VALUES ('9', 'web', 'web', '3', '5', '2', 'developModule_web');
INSERT INTO `comdict` VALUES ('10', 'mobile', 'mb', '4', '5', '2', 'developModule_mb');

-- ----------------------------
-- Table structure for sys_app_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------
INSERT INTO `sys_app_user` VALUES ('04762c0b28b643939455c7800c2e2412', 'dsfsd', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'w', '', '55896f5ce3c0494fa6850775a4e29ff6', '', '', '1', '', '18766666666', '', '', '', '0', '001', '18766666666@qq.com');
INSERT INTO `sys_app_user` VALUES ('3faac8fe5c0241e593e0f9ea6f2d5870', 'dsfsdf', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'wewe', '', '68f23fc0caee475bae8d52244dea8444', '', '', '1', '', '18767676767', '', '', '', '0', 'wqwe', 'qweqwe@qq.com');
