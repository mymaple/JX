
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ${objectModuleEL}${objectNameU}
-- ----------------------------
DROP TABLE IF EXISTS `${objectModuleEL}${objectNameU}`;
CREATE TABLE `${objectModuleEL}${objectNameU}` (
 	`${objectNameL}Id` int(11) NOT NULL AUTO_INCREMENT,
 	<#list fieldList as var>
		<#if var[3] == 'propType_String'>
	`${var[1]}` varchar(${var[4]})
		<#elseif var[3] == 'propType_Int'>
	`${var[1]}` int(${var[4]})
		<#elseif var[3] == 'propType_Date'>
	`${var[1]}` date
		<#elseif var[3] == 'propType_Double'>
	`${var[1]}` double(${var[4]},2)
		</#if>
		<#if var[6] == '0'> NOT NULL <#if var[7] != '(null)'> DEFAULT var[7] </#if><#else> DEFAULT NULL </#if> COMMENT '${var[2]}' ,	
	</#list>
  
  PRIMARY KEY (`${objectNameL}Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
