<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="param" uri="http://www.maple_param_tld.com"%>
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
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<style type="text/css">
		#dialog-add,#dialog-message,#dialog-comment{width:100%; height:100%; position:fixed; top:0px; z-index:10000; display:none;}
		.commitopacity{position:absolute; width:100%; height:500px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
		.commitbox{width:85%; padding-left:81px; padding-top:50px; position:absolute; top:0px; z-index:99999;}
		.commitbox_inner{width:96%; height:325px;  margin:6px auto; background:#efefef; border-radius:5px;}
		.commitbox_top{width:100%; height:320px; margin-bottom:10px; padding-top:10px; background:#FFF; border-radius:5px; box-shadow:1px 1px 3px #e8e8e8;}
		.commitbox_top textarea{width:95%; height:165px; display:block; margin:0px auto; border:0px;}
		.commitbox_top table{width:95%; margin:0px auto; border:0px;}
		.commitbox_cen{width:95%; height:40px; padding-top:10px;}
		.commitbox_cen div.center{margin:0 auto;; margin-top:7px;}
		.commitbox_cen div.center span{cursor:pointer;}
		.commitbox_cen div.center span.save{border:solid 1px #c7c7c7; background:#6FB3E0; border-radius:3px; color:#FFF; padding:10px 20px;}
		.commitbox_cen div.center span.sp{ padding:10px 40px;}
		.commitbox_cen div.center span.cancel{border:solid 1px #f77400; background:#f77400; border-radius:3px; color:#FFF; padding:10px 20px;}
		
		</style>
		
	</head>
<body>
	<!-- 添加属性  -->
	<input type="hidden" name="editPropName" id="editPropName" value="" />
	<input type="hidden" name="editIndex" id="editIndex" value="" />
	<div id="dialog-add">
		<div class="commitopacity"></div>
	  	<div class="commitbox">
		  	<div class="commitbox_inner">
		        <div class="commitbox_top">
		        	<br/>
		        	<table>
		        		<tr>
		        			<td style="padding-left: 16px;text-align: right;"><font style="color: red;">*</font>属性名：</td>
		        			<td><input id="propName" type="text" value="" placeholder="首字母必须为字母或下划线" title="属性名" /></td>
		        			<td style="padding-left: 16px;text-align: right;"><font style="color: red;">*</font>注释：</td>
		        			<td style="padding-bottom: 6px;"><input id="propComment" type="text" value="" placeholder="例如 name的注释为 '姓名'" title="注释"/></td>
		        		</tr>
		        		<tr>
		        			<td style="padding-left: 16px;text-align: right;"><font style="color: red;">*</font>类型：</td>
		        			<td><param:select id="propType" type="propType" /></td>
		        			<td style="padding-left: 16px;text-align: right;">长度：</td>
		        			<td style="padding-bottom: 6px;">
		        				<input id="propLength" type="number" />
							</td>
		        		</tr>
		        		<tr>
		        			<td style="padding-left: 16px;text-align: right;"><font style="color: red;">*</font>页面录入：</td>
		        			<td style="padding-bottom: 6px;">
		        				<label style="float:left;padding-left: 20px;"><input id="isWrite1" type="radio" value="icon-edit" checked="checked"><span class="lbl">是</span></label>
								<label style="float:left;padding-left: 20px;"><input id="isWrite0" type="radio" value="icon-edit"><span class="lbl">否</span></label>
							</td>
							<td style="padding-left: 16px;text-align: right;"><font style="color: red;">*</font>isNull：</td>
		        			<td style="padding-bottom: 6px;">
		        				<label style="float:left;padding-left: 20px;"><input id="isNull1" type="radio" value="1" checked="checked"><span class="lbl">是</span></label>
								<label style="float:left;padding-left: 20px;"><input id="isNull0" type="radio" value="0"><span class="lbl">否</span></label>
							</td>
		        		</tr>
		        		<tr>
		        			<td style="padding-left: 16px;text-align: right;">默认值：</td>
		        			<td><input id="propDefault" type="text" value="" title="默认值"/></td>
		        			<td style="padding-left: 16px;text-align: right;"></td>
		        			<td>
		        			</td>
		        		</tr>
		        		<tr>
		        			<td colspan="4">
		        			<div class="commitbox_cen">
				                <div class="center">
				                <span class="save" onClick="saveField()">保存</span>
				                <span class="sp"></span>
				                <span class="cancel" onClick="toCancel()">取消</span></div>
				            </div>
		        			</td>
		        		</tr>
		        		<tr>
		        			<td style="padding-left: 16px;" colspan="100">
		        				<font color="red" style="font-weight: bold;">
		        					注意：<br/>
		        					  1. 请不要添加  类名Id 的主键<br/>
		        					  2. 属性为double时默认两位小数，长度填写整数长度
		        				</font>
							</td>
		        		</tr>
		        	</table>
		        </div>
		  	</div>
	  	</div>
	</div>

	<form action="background/config/codeCreate.do" name="Form" id="Form" method="post">
		<input type="hidden" name="fieldCount" id="fieldCount" value="0">
		<div id="zhongxin">
		<table style="margin-top: 10px;">
			<tr>
				<td style="padding-left: 16px;text-align: right;">控制模块：</td>
				<td><param:select name="conModule" id="conModule" type="developModule"/></td>
				<td style="padding-left: 16px;text-align: right;">类模块：</td>
				<td><param:select name="objectModule" id="objectModule" type="developModule"/></td>
			</tr>
			<tr>
				<td style="padding-left: 16px;text-align: right;">类名：</td>
				<td><input name="objectName" id="objectName" type="text"/></td>
				<td style="padding-left: 16px;text-align: right;"></td>
				<td></td>
			</tr>
		</table>
		
		<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">属性名</th>
						<th class="center">注释</th>
						<th class="center">类型</th>
						<th class="center">长度</th>
						<th class="center">页面录入</th>
						<th class="center">isNull</th>
						<th class="center">默认值</th>
						<th class="center" style="width:69px;">操作</th>
					</tr>
				</thead>
										
				<tbody id="fields"></tbody>
				
		</table>
		
		
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="text-align: center;" colspan="100">
					<a class="btn btn-app btn-success btn-mini" onclick="toAddField();"><i class="icon-ok"></i>新增</a>
					<a class="btn btn-app btn-success btn-mini" onclick="save();" id="productc"><i class="icon-print"></i>生成</a>
					<a class="btn btn-app btn-danger btn-mini" onclick="top.Dialog.close();"><i class="icon-share-alt"></i>取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script src="static/js/myjs/codeCreate.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		</script>
	
</body>
</html>