-- ----------------------------
-- Table structure for bgUser
-- ----------------------------
DROP TABLE IF EXISTS `bgUser`;
CREATE TABLE `bgUser` (
  `userId` varchar(100) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `roleId` varchar(100) DEFAULT NULL,
  `lastLogin` varchar(255) DEFAULT NULL,
  `loginIp` varchar(100) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `skin` varchar(100) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `userNumber` varchar(100) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bgUser
-- ----------------------------
INSERT INTO `bgUser` VALUES ('089d664844f8441499955b3701696fc0', 'fushide', '67bc304642856b709dfeb907b92cc7e10e0b02f2', '富师德', '', '2', '', '', '0', '18629359', 'default', 'asdadf@qq.com', '1231', '18766666666');
INSERT INTO `bgUser` VALUES ('0b3f2ab1896b47c097a81d322697446a', 'zhangsan', '5ee5d458d02fde6170b9c3ebfe06af85dacd131d', '张三', '', '2', '2015-06-03 22:09:13', '127.0.0.1', '0', '小张', 'default', 'wwwwq@qq.com', '1101', '18788888888');
INSERT INTO `bgUser` VALUES ('0e2da7c372e147a0b67afdf4cdd444a3', 'dfsdf', 'c49639f0b2c5dda506b777a1e518990e9a88a221', 'wqeqw', '', 'e74f713314154c35bd7fc98897859fe3', '', '', '0', 'ff', 'default', 'q324@qq.com', 'dsfsdddd', '18767676767');
INSERT INTO `bgUser` VALUES ('1', 'admin', 'de41b7fb99201d8334c23c014db35ecd92df81bc', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2015-06-30 21:07:00', '127.0.0.1', '0', '最高统治者', 'default', 'admin@main.com', '001', '18788888888');
INSERT INTO `bgUser` VALUES ('8009132b158748a5a84510f91a291119', 'asdasd', '26be4dd18f543d7002b4d8aa525f90a33c0faefb', 'sdsdf', '', '7dfd8d1f7b6245d283217b7e63eec9b2', '', '', '0', '', 'default', '12312312@qq.com', '2312', '18765810587');
INSERT INTO `bgUser` VALUES ('b825f152368549069be79e1d34184afa', 'san', '47c4a8dc64ac2f0bb46bbd8813b037c9718f9349', '三', '', '2', '2015-06-03 22:02:12', '127.0.0.1', '0', 'sdfsdgf', 'default', 'sdfsdf@qq.com', 'sdsaw22', '18765656565');
INSERT INTO `bgUser` VALUES ('be025a79502e433e820fac37ddb1cfc2', 'zhangsan570256', '42f7554cb9c00e11ef16543a2c86ade815b79faa', '张三', '', '2', '', '', '0', '小张', 'default', 'zhangsan@www.com', '21101', '2147483647');


-- ----------------------------
-- Table structure for bgMenu
-- ----------------------------
DROP TABLE IF EXISTS `bgMenu`;
CREATE TABLE `bgMenu` (
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
-- Records of bgMenu
-- ----------------------------
INSERT INTO `bgMenu` VALUES ('1', '系统管理', '#', '0', '1', 'icon-desktop', '1');
INSERT INTO `bgMenu` VALUES ('2', '组织管理', 'role.do', '1', '2', null, '1');
INSERT INTO `bgMenu` VALUES ('4', '会员管理', 'happuser/listUsers.do', '1', '4', null, '1');
INSERT INTO `bgMenu` VALUES ('5', '系统用户', 'user/listUsers.do', '1', '3', null, '1');
INSERT INTO `bgMenu` VALUES ('6', '信息管理', '#', '0', '2', 'icon-list-alt', '2');
INSERT INTO `bgMenu` VALUES ('7', '图片管理', 'pictures/list.do', '6', '1', null, '2');
INSERT INTO `bgMenu` VALUES ('8', '性能监控', 'druid/index.html', '9', '1', null, '1');
INSERT INTO `bgMenu` VALUES ('9', '系统工具', '#', '0', '3', 'icon-th', '1');
INSERT INTO `bgMenu` VALUES ('10', '接口测试', 'tool/interfaceTest.do', '9', '2', null, '1');
INSERT INTO `bgMenu` VALUES ('11', '发送邮件', 'tool/goSendEmail.do', '9', '3', null, '1');
INSERT INTO `bgMenu` VALUES ('12', '置二维码', 'tool/goTwoDimensionCode.do', '9', '4', null, '1');
INSERT INTO `bgMenu` VALUES ('13', '多级别树', 'tool/ztree.do', '9', '5', null, '1');
INSERT INTO `bgMenu` VALUES ('14', '地图工具', 'tool/map.do', '9', '6', null, '1');
INSERT INTO `bgMenu` VALUES ('15', '微信管理', '#', '0', '2', 'icon-comments', '2');
INSERT INTO `bgMenu` VALUES ('16', '文本回复', 'textmsg/list.do', '15', '2', null, '2');
INSERT INTO `bgMenu` VALUES ('17', '应用命令', 'command/list.do', '15', '4', null, '2');
INSERT INTO `bgMenu` VALUES ('18', '图文回复', 'imgmsg/list.do', '15', '3', null, '2');
INSERT INTO `bgMenu` VALUES ('19', '关注回复', 'textmsg/goSubscribe.do', '15', '1', null, '2');
INSERT INTO `bgMenu` VALUES ('20', '在线管理', 'onlinemanager/list.do', '1', '5', null, '1');
INSERT INTO `bgMenu` VALUES ('21', '打印测试', 'tool/printTest.do', '9', '7', null, '1');

-- ----------------------------
-- Table structure for bgRole
-- ----------------------------
DROP TABLE IF EXISTS `bgRole`;
CREATE TABLE `bgRole` (
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
-- Records of bgRole
-- ----------------------------
INSERT INTO `bgRole` VALUES ('1', '系统管理员', '4194294', '0', '1', '1', '1', '1', '1');
INSERT INTO `bgRole` VALUES ('2', '超级管理员', '4194294', '1', '230', '50', '34', '54', '2');
INSERT INTO `bgRole` VALUES ('4', '用户组', '118', '0', '0', '0', '0', '0', null);
INSERT INTO `bgRole` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '特级会员', '498', '7', '0', '0', '0', '0', '55896f5ce3c0494fa6850775a4e29ff6');
INSERT INTO `bgRole` VALUES ('6', '客户组', '18', '0', '1', '1', '1', '1', null);
INSERT INTO `bgRole` VALUES ('68f23fc0caee475bae8d52244dea8444', '中级会员', '498', '7', '0', '0', '0', '0', '68f23fc0caee475bae8d52244dea8444');
INSERT INTO `bgRole` VALUES ('7', '会员组', '498', '0', '0', '0', '0', '1', null);
INSERT INTO `bgRole` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '管理员B', '4194294', '1', '246', '0', '0', '0', '7dfd8d1f7b6245d283217b7e63eec9b2');
INSERT INTO `bgRole` VALUES ('ac66961adaa2426da4470c72ffeec117', '管理员A', '4194294', '1', '54', '54', '0', '246', 'ac66961adaa2426da4470c72ffeec117');
INSERT INTO `bgRole` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '高级会员', '498', '7', '0', '0', '0', '0', 'b0c77c29dfa140dc9b14a29c056f824f');
INSERT INTO `bgRole` VALUES ('e74f713314154c35bd7fc98897859fe3', '黄金客户', '18', '6', '1', '1', '1', '1', 'e74f713314154c35bd7fc98897859fe3');
INSERT INTO `bgRole` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '初级会员', '498', '7', '1', '1', '1', '1', 'f944a9df72634249bbcb8cb73b0c9b86');


-- ----------------------------
-- Table structure for bgConfig
-- ----------------------------
DROP TABLE IF EXISTS `bgConfig`;
CREATE TABLE `bgConfig` (
  `configId` varchar(100) NOT NULL,
  `configType` varchar(255) DEFAULT NULL,
  `configName` varchar(255) DEFAULT NULL,
  `param1` varchar(255) DEFAULT NULL,
  `param2` varchar(255) DEFAULT NULL,
  `param3` varchar(255) DEFAULT NULL,
  `param4` varchar(255) DEFAULT NULL,
  `isOpen` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`configId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bgConfig
-- ----------------------------
INSERT INTO `bgConfig` VALUES ('1', 'configBgSystem', '系统配置', 'JX', '10', 'admin', '', true);
INSERT INTO `bgConfig` VALUES ('2', 'configBgEmailServer', '邮箱服务器配置', 'smtp.qq.com', '25', 'it@126.com', '123', true);
INSERT INTO `bgConfig` VALUES ('3', 'configBgMessage', '短信账户配置', 'http://www.dxton.com/', '', 'username', 'ppp', true);
INSERT INTO `bgConfig` VALUES ('4', 'configBgWordWaterMark', '文字水印配置', 'JX', '20', '1', '1', true);
INSERT INTO `bgConfig` VALUES ('5', 'configBgImageWaterMark', '图片水印配置', 'watermark.png', '', '1', '1', true);
INSERT INTO `bgConfig` VALUES ('6', 'configBgWeiXin', ' 微信接口配置', 'http://localhost:8080/FHMYSQL/weixin/index ', '', 'token', '', true);
INSERT INTO `bgConfig` VALUES ('7', 'configBgInstantChat', '即时聊天服务器配置', '127.0.0.1', '3020', '', '', true);
INSERT INTO `bgConfig` VALUES ('8', 'configBgOnlineManage', '在线管理服务器配置', '127.0.0.1', '3021', '', '', true);


-- ----------------------------
-- Table structure for comDict
-- ----------------------------
DROP TABLE IF EXISTS `comDict`;
CREATE TABLE `comDict` (
  `dictId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `encode` varchar(100) DEFAULT NULL,
  `orderBy` int(10) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `allEncode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comDict
-- ----------------------------
INSERT INTO `comDict` VALUES (1, '人事部', '001', 1, 8, 2, 'BM_001');
INSERT INTO `comDict` VALUES (2, '行政部', '002', 2, 8, 2, 'BM_002');
INSERT INTO `comDict` VALUES (4, '北京', 'dq001', 1, 9, 2, 'DQ_dq001');
INSERT INTO `comDict` VALUES (5, '研发部', '004', 4, 8, 2, 'BM_004');
INSERT INTO `comDict` VALUES (6, '财务部', '003', 3, 8, 2, 'BM_003');
INSERT INTO `comDict` VALUES (7, '科技不', 'kj', 7, 8, 2, 'BM_kj');
INSERT INTO `comDict` VALUES (8, '部门', 'BM', 1, 0, 1, 'BM');
INSERT INTO `comDict` VALUES (9, '地区', 'DQ', 2,0, 1, 'DQ');
INSERT INTO `comDict` VALUES (10, '上海', 'SH', 2,9, 2, 'DQ_SH');
INSERT INTO `comDict` VALUES (11, '客服部', '006', 6, 2, 2, 'BM_006');
