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
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		if($("#userId").val()!=""){
			$("#userName").attr("readonly","readonly");
			$("#userName").css("color","gray");
		}
	});
	
	//保存
	function save(){
		if($("#roleId").val()==""){
			
			$("#roleId").tips({
				side:3,
	            msg:'选择角色',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#roleId").focus();
			return false;
		}
		if($("#userName").val()=="" || $("#userName").val()=="此用户名已存在!"){
			
			$("#userName").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#userName").focus();
			$("#userName").val('');
			$("#userName").css("background-color","white");
			return false;
		}else{
			$("#userName").val(jQuery.trim($('#userName').val()));
		}
		
		if($("#userNumber").val()==""){
			
			$("#userNumber").tips({
				side:3,
	            msg:'输入编号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#userNumber").focus();
			return false;
		}else{
			$("#userNumber").val($.trim($("#userNumber").val()));
		}
		
		if($("#userId").val()=="" && $("#password").val()==""){
			
			$("#password").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#password").focus();
			return false;
		}
		if($("#password").val()!=$("#chkpwd").val()){
			
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			
			$("#chkpwd").focus();
			return false;
		}
		if($("#name").val()==""){
			
			$("#name").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		}
		
		var myreg = /^(((13[0-9]{1})|159)+\d{8})$/;
		if($("#phone").val()==""){
			
			$("#phone").tips({
				side:3,
	            msg:'输入手机号',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#phone").focus();
			return false;
		}else if($("#phone").val().length != 11 && !myreg.test($("#phone").val())){
			$("#phone").tips({
				side:3,
	            msg:'手机号格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#phone").focus();
			return false;
		}
		
		if($("#email").val()==""){
			$("#email").tips({
				side:3,
	            msg:'输入邮箱',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#email").focus();
			return false;
		}else if(!ismail($("#email").val())){
			$("#email").tips({
				side:3,
	            msg:'邮箱格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#email").focus();
			return false;
		}
		
		if($("#userId").val()==""){
			hasUser();
		}else{
			$("#userForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	}
	
	function ismail(mail){
		return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
		}
	
	//判断用户名是否存在
	function hasUser(){
		var userName = $.trim($("#userName").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>background/user/hasUser.do',
	    	data: {userName:userName,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					$("#userForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#userName").css("background-color","#D16E6C");
					setTimeout("$('#userName').val('此用户名已存在!')",500);
				 }
			}
		});
	}
	
	//判断邮箱是否存在
	function hasEmail(userName){
		var email = $.trim($("#email").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>background/user/hasEmail.do',
	    	data: {email:email,userName:userName,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#email").tips({
							side:3,
				            msg:'邮箱已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#email').val('')",2000);
				 }
			}
		});
	}
	
	//判断编码是否存在
	function hasUserNumber(userName){
		var userNumber = $.trim($("#userNumber").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>background/user/hasUserNumber.do',
	    	data: {userNumber:userNumber,userName:userName,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#userNumber").tips({
							side:3,
				            msg:'编号已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#userNumber').val('')",2000);
				 }
			}
		});
	}
	
	//判断编码是否存在
	function hasPhone(userName){
		var phone = $.trim($("#phone").val());
		$.ajax({
			type: "POST",
			url: '<%=basePath%>background/user/hasPhone.do',
	    	data: {phone:phone,userName:userName,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#phone").tips({
							side:3,
				            msg:'手机号已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					setTimeout("$('#phone').val('')",2000);
				 }
			}
		});
	}
	
	
</script>
	</head>
<body>
	<form action="background/user/${msg }.do" name="userForm" id="userForm" method="post">
		<input type="hidden" name="userId" id="userId" value="${bgUser.userId }"/>
		<div id="zhongxin">
		<table>
			<c:if test="${fx != 'head'}">
			<c:if test="${bgUser.roleId != '1'}">	
			<tr class="info">
				<td>
				<select class="chzn-select" name="roleId" id="roleId" data-placeholder="请选择职位" style="vertical-align:top;">
				<option value=""></option>
				<c:forEach items="${roleList}" var="role">
					<option value="${role.roleId }" <c:if test="${role.roleId == bgUser.roleId }">selected</c:if>>${role.roleName }</option>
				</c:forEach>
				</select>
				</td>
			</tr>
			</c:if>
			<c:if test="${bgUser.roleId == '1'}">
			<input name="roleId" id="roleId" value="1" type="hidden" />
			</c:if>
			</c:if>
			
			<c:if test="${fx == 'head'}">
				<input name="roleId" id="roleId" value="${bgUser.roleId }" type="hidden" />
			</c:if>
			
			<tr>
				<td><input type="text" name="userName" id="userName" value="${bgUser.userName }" maxlength="32" placeholder="这里输入用户名" title="用户名"/></td>
			</tr>
			<tr>
				<td><input type="text" name="userNumber" id="userNumber" value="${bgUser.userNumber }" maxlength="32" placeholder="这里输入编号" title="编号" onblur="hasUserNumber('${bgUser.userName }')"/></td>
			</tr>
			<tr>
				<td><input type="password" name="password" id="password"  maxlength="32" placeholder="输入密码" title="密码"/></td>
			</tr>
			<tr>
				<td><input type="password" name="chkpwd" id="chkpwd"  maxlength="32" placeholder="确认密码" title="确认密码" /></td>
			</tr>
			<tr>
				<td><input type="text" name="name" id="name"  value="${bgUser.name }"  maxlength="32" placeholder="这里输入姓名" title="姓名"/></td>
			</tr>
			<tr>
				<td><input type="number" name="phone" id="phone"  value="${bgUser.phone }"  maxlength="32" placeholder="这里输入手机号" title="手机号" onblur="hasPhone('${bgUser.userName }')"/></td>
			</tr>
			<tr>
				<td><input type="email" name="email" id="email"  value="${bgUser.email }" maxlength="32" placeholder="这里输入邮箱" title="邮箱" onblur="hasEmail('${bgUser.userName }')"/></td>
			</tr>
			<tr>
				<td><input type="text" name="bz" id="bz"value="${bgUser.bz }" placeholder="这里输入备注" maxlength="64" title="备注"/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
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
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			//$('.date-picker').datepicker();
			
		});
		
		</script>
	
</body>
</html>