<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		
		<!-- 上传图片插件 -->
		<link href="plugins/uploadify/uploadify.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="plugins/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="plugins/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
		<!-- 上传图片插件 -->
		<script type="text/javascript">
		var jsessionid = "<%=session.getId()%>";  //勿删，uploadify兼容火狐用到
		</script>
		<!--引入属于此页面的js -->
		<script type="text/javascript" src="static/js/myjs/sys.js"></script>	
		
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		
	</head>
<body>

<div id="zhongxin">
 <div class="span6">
	<div class="tabbable">
            <ul class="nav nav-tabs" id="myTab">
              <li class="active"><a data-toggle="tab" href="#home"><i class="green icon-home bigger-110"></i> 配置 NO1</a></li>
              <li><a data-toggle="tab" href="#profile"><i class="green icon-cog bigger-110"></i>配置 NO2</a></li>
              <li><a data-toggle="tab" href="#profile3"><i class="green icon-cog bigger-110"></i>配置 NO3</a></li>
            </ul>
            <div class="tab-content">
			  <div id="home" class="tab-pane in active">
				<form action="background/config/editConfig.do" name="Form" id="Form" method="post">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgSystem.configName }
							<input type="hidden" name="configId" id="configIdSystem" value="${pd.configBgSystem.configId }" />
							<input type="hidden" name="configType" id="configTypeSystem" value="${pd.configBgSystem.configType }" />
							<input type="hidden" name="configName" id="configNameSystem" value="${pd.configBgSystem.configName }" />
							<input type="hidden" name="isOpen" id="isOpenSystem" value="${pd.configBgSystem.isOpen }" />
							<input type="hidden" name="param3" id="param3System" value="${pd.configBgSystem.param3 }" />
							<input type="hidden" name="param4" id="param4System" value="${pd.configBgSystem.param4 }" />
						</td>
					</tr>
					<tr>
						<td style="width:70px;text-align: right;padding-top: 13px;">系统名称:</td>
						<td><input type="text" name="param1" id="param1System" value="${pd.configBgSystem.param1 }" placeholder="这里输入系统名称" style="width:90%" title="系统名称"/></td>
					
						<td style="width:70px;text-align: right;padding-top: 13px;">每页条数:</td>
						<td><input type="number" name="param2" id="param2System" value="${pd.configBgSystem.param2 }" placeholder="这里输入每页条数" style="width:90%" title="每页条数"/></td>
					</tr>
				</table>
				
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgEmailServer.configName }
							<input type="hidden" name="configId" id="configIdEmail" value="${pd.configBgEmailServer.configId }" />
							<input type="hidden" name="configType" id="configTypeEmail" value="${pd.configBgEmailServer.configType }" />
							<input type="hidden" name="configName" id="configNameEmail" value="${pd.configBgEmailServer.configName }" />
							<input type="hidden" name="isOpen" id="isOpenEmail" value="${pd.configBgEmailServer.isOpen }" />
							
						</td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 13px;">SMTP:</td>
						<td><input type="text" name="param1" id="param1Email" value="${pd.configBgEmailServer.param1 }" placeholder="例如:smtp.qq.com" style="width:90%" title="SMTP"/></td>
					
						<td style="width:50px;text-align: right;padding-top: 13px;">端口:</td>
						<td><input type="number" name="param2" id="param2Email" value="${pd.configBgEmailServer.param2 }" placeholder="一般为：25" style="width:90%" title="端口"/></td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 13px;">邮箱:</td>
						<td><input type="email" name="param3" id="param3Email" value="${pd.configBgEmailServer.param3 }" placeholder="请输入邮件服务器邮箱" style="width:90%" title="邮箱"/></td>
					
						<td style="width:50px;text-align: right;padding-top: 13px;">密码:</td>
						<td><input type="text" name="param4" id="param4Email" value="${pd.configBgEmailServer.param4 }" placeholder="请输入邮箱密码" style="width:90%" title="密码"/></td>
					</tr>
				</table>
				
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgMessage.configName }&nbsp;(短信商&nbsp;<a href="${pd.configBgMessage.param1 }" target="_blank">官网</a>)
							<input type="hidden" name="configId" id="configIdMessage" value="${pd.configBgMessage.configId }" />
							<input type="hidden" name="configType" id="configTypeMessage" value="${pd.configBgMessage.configType }" />
							<input type="hidden" name="configName" id="configNameMessage" value="${pd.configBgMessage.configName }" />
							<input type="hidden" name="isOpen" id="isOpenMessage" value="${pd.configBgMessage.isOpen }" />
							<input type="hidden" name="param2" id="param2Message" value="${pd.configBgMessage.param2 }" />
						</td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 13px;">官网:</td>
						<td colspan="3"><input type="text" name="param1" id="param1Message" value="${pd.configBgMessage.param1 }" placeholder="官网" style="width:90%" title="网站"/></td>
					
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 13px;">账号:</td>
						<td><input type="text" name="param3" id="param3Message" value="${pd.configBgMessage.param3 }" placeholder="请输入账号" style="width:90%" title="邮箱"/></td>
					
						<td style="width:50px;text-align: right;padding-top: 13px;">密码:</td>
						<td><input type="text" name="param4" id="param4Message" value="${pd.configBgMessage.param4 }" placeholder="请输入密码" style="width:90%" title="密码"/></td>
					</tr>
				</table>
		
				<table class="center" style="width:100%" >
					<tr>
						<td style="text-align: center;" colspan="100">
							<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
						</td>
					</tr>
				</table>
				</form>
			  </div>
			  <div id="profile" class="tab-pane">
			  	<form action="head/saveSys2.do" name="Form2" id="Form2" method="post">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgWordWaterMark.configName }
							<input type="hidden" name="configId" id="configIdWord" value="${pd.configBgWordWaterMark.configId }" />
							<input type="hidden" name="configType" id="configTypeWord" value="${pd.configBgWordWaterMark.configType }" />
							<input type="hidden" name="configName" id="configNameWord" value="${pd.configBgWordWaterMark.configName }" />
							<input type="hidden" name="isOpen" id="isOpenWord" value="${pd.configBgWordWaterMark.isOpen }" />
							<label style="float:left;padding-left: 15px;"><input name="fcheckbox" class="ace-checkbox-2" type="checkbox" id="check1" onclick="openThis1();" /><span class="lbl">开启</span></label>
						</td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 12px;">内容:</td>
						<td><input type="text" name="param1" id="param1Word" value="${pd.configBgWordWaterMark.param1 }"  style="width:90%" title="水印文字内容"/></td>
						<td style="width:50px;text-align: right;padding-top: 12px;">字号:</td>
						<td><input type="number" name="param2" id="param2Word" value="${pd.configBgWordWaterMark.param2 }"  style="width:90%" title="字号"/></td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 12px;">X坐标:</td>
						<td><input type="number" name="param3" id="param3Word" value="${pd.configBgWordWaterMark.param3 }"  style="width:90%" title="X坐标"/></td>
						<td style="width:50px;text-align: right;padding-top: 12px;">Y坐标:</td>
						<td><input type="number" name="param4" id="param4Word" value="${pd.configBgWordWaterMark.param4 }"  style="width:90%" title="Y坐标"/></td>
					</tr>
				</table>
				
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgImageWaterMark.configName }
							<input type="hidden" name="configId" id="configIdImage" value="${pd.configBgImageWaterMark.configId }" />
							<input type="hidden" name="configType" id="configTypeImage" value="${pd.configBgImageWaterMark.configType }" />
							<input type="hidden" name="configName" id="configNameImage" value="${pd.configBgImageWaterMark.configName }" />
							<input type="hidden" name="isOpen" id="isOpenImage" value="${pd.configBgImageWaterMark.isOpen }" />
							<input type="hidden" name="param2" id="param2Image" value="${pd.configBgImageWaterMark.param2 }" />
							
							<label style="float:left;padding-left: 15px;"><input name="fcheckbox" class="ace-checkbox-2" type="checkbox" id="check2" onclick="openThis2();" /><span class="lbl">开启</span></label>
						</td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 12px;">X坐标:</td>
						<td><input type="number" name="param3" id="param3Image" value="${pd.configBgImageWaterMark.param3 }" style="width:90%" title="X坐标"/></td>
						<td style="width:50px;text-align: right;padding-top: 12px;">Y坐标:</td>
						<td><input type="number" name="param4" id="param4Image" value="${pd.configBgImageWaterMark.param4 }"  style="width:90%" title="Y坐标"/></td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 12px;">水印:</td>
						<td colspan="10">
						<div style="float:left;"><img src="<%=basePath%>uploadFiles/uploadImgs/${pd.configBgImageWaterMark.param1 }"  width="100"/></div>
						<div style="float:right;"><input type="file" name="param1" id="param1Image" keepDefaultStyle = "true"/></div>
						</td>
					</tr>
				</table>
				
				<table class="center" style="width:100%" >
					<tr>
						<td style="text-align: center;" colspan="100">
							<a class="btn btn-mini btn-primary" onclick="save2();">保存</a>
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
						</td>
					</tr>
				</table>
				<input type="hidden" name="imgUrl" id="imgUrl" value="${pd.imgUrl }"/>
				<input type="hidden" value="no" id="hasTp1" />
				</form>
			  </div>
			  
			  
			  <div id="profile3" class="tab-pane">
			  	<form action="head/saveSys3.do" name="Form3" id="Form3" method="post">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgWeiXin.configName }
							<input type="hidden" name="configId" id="configIdWeiXin" value="${pd.configBgWeiXin.configId }" />
							<input type="hidden" name="configType" id="configTypeWeiXin" value="${pd.configBgWeiXin.configType }" />
							<input type="hidden" name="configName" id="configNameWeiXin" value="${pd.configBgWeiXin.configName }" />
							<input type="hidden" name="isOpen" id="isOpenWeiXin" value="${pd.configBgWeiXin.isOpen }" />
							<input type="hidden" name="param2" id="param2WeiXin" value="${pd.configBgWeiXin.param2 }" />
							<input type="hidden" name="param4" id="param4WeiXin" value="${pd.configBgWeiXin.param4 }" />
							
						</td>
					</tr>
					<tr>
						<td style="width:120px;text-align: right;padding-top: 12px;">URL(服务器地址):</td>
						<td><input type="text" name="param1" id="param1WeiXin" value="<%=basePath%>${pd.configBgWeiXin.param1 }" style="width:90%" title="URL(服务器地址)必须是域名，ip地址验证通不过"/></td>
					</tr>
					<tr>
						<td style="width:120px;text-align: right;padding-top: 12px;">Token(令牌):</td>
						<td><input type="text" name="param3" id="param3WeiXin" value="${pd.configBgWeiXin.param3 }"  style="width:90%" title="URL(服务器地址)"/></td>
					</tr>
				</table>
				
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgInstantChat.configName }
							<input type="hidden" name="configId" id="configIdChat" value="${pd.configBgInstantChat.configId }" />
							<input type="hidden" name="configType" id="configTypeChat" value="${pd.configBgInstantChat.configType }" />
							<input type="hidden" name="configName" id="configNameChat" value="${pd.configBgInstantChat.configName }" />
							<input type="hidden" name="isOpen" id="isOpenChat" value="${pd.configBgInstantChat.isOpen }" />
							<input type="hidden" name="param3" id="param3Chat" value="${pd.configBgInstantChat.param3 }" />
							<input type="hidden" name="param4" id="param4Chat" value="${pd.configBgInstantChat.param4 }" />
							
						</td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 13px;">地址:</td>
						<td><input type="text" name="param1" id="param1Chat" value="${pd.configBgInstantChat.param1 }" placeholder="请输入服务器地址" style="width:90%" title="服务器地址"/></td>
					
						<td style="width:50px;text-align: right;padding-top: 13px;">端口:</td>
						<td><input type="number" name="param2" id="param2Chat" value="${pd.configBgInstantChat.param2 }" placeholder="端口" style="width:90%" title="端口"/></td>
					</tr>
				</table>
				
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<tr>
						<td style="text-align: center;" colspan="100">
							${pd.configBgOnlineManage.configName }
							<input type="hidden" name="configId" id="configIdManage" value="${pd.configBgOnlineManage.configId }" />
							<input type="hidden" name="configType" id="configTypeManage" value="${pd.configBgOnlineManage.configType }" />
							<input type="hidden" name="configName" id="configNameManage" value="${pd.configBgOnlineManage.configName }" />
							<input type="hidden" name="isOpen" id="isOpenManage" value="${pd.configBgOnlineManage.isOpen }" />
							<input type="hidden" name="param3" id="param3Manage" value="${pd.configBgOnlineManage.param3 }" />
							<input type="hidden" name="param4" id="param4Manage" value="${pd.configBgOnlineManage.param4 }" />
							
						</td>
					</tr>
					<tr>
						<td style="width:50px;text-align: right;padding-top: 13px;">地址:</td>
						<td><input type="text" name="param1" id="param1Manage" value="${pd.configBgOnlineManage.param1 }" placeholder="请输入服务器地址" style="width:90%" title="服务器地址"/></td>
					
						<td style="width:50px;text-align: right;padding-top: 13px;">端口:</td>
						<td><input type="number" name="param2" id="param2Manage" value="${pd.configBgOnlineManage.param2 }" placeholder="端口" style="width:90%" title="端口"/></td>
					</tr>
				</table>
				
				<table class="center" style="width:100%" >
					<tr>
						<td style="text-align: center;" colspan="100">
							<a class="btn btn-mini btn-primary" onclick="save3();">保存</a>
							<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
						</td>
					</tr>
				</table>
				</form>
			  </div>
			  
			  
            </div>
	</div>
 </div><!--/span-->



</div>
		
<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			if("${pd.isCheck1 }" == "yes"){
				$("#check1").attr("checked",true);
			}else{
				$("#check1").attr("checked",false);
			}
			if("${pd.isCheck2 }" == "yes"){
				$("#check2").attr("checked",true);
			}else{
				$("#check2").attr("checked",false);
			}
		});
		</script>
</body>
</html>