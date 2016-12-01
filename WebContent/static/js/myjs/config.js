var locat = (window.location+'').split('/'); 
$(function(){
	if('head'== locat[3]){
		locat =  locat[0]+'//'+locat[2];
	}else{
		locat =  locat[0]+'//'+locat[2]+'/'+locat[3];
	};
});
$(top.hangge());

//=======================================配置NO3
function save3(){
	
	//weixin-----------
	if($("#param1WeiXin").val()==""){
		$("#param1WeiXin").tips({
			side:3,
            msg:'URL(服务器地址)',
            bg:'#AE81FF',
            time:3
        });
		$("#param1WeiXin").focus();
		return false;
	}
	
	if($("#param3WeiXin").val()==""){
		$("#param3WeiXin").tips({
			side:3,
            msg:'Token(令牌)',
            bg:'#AE81FF',
            time:3
        });
		$("#param3WeiXin").focus();
		return false;
	}
	//weixin-----------
	
	//即时聊天-----------
	if($("#param1Chat").val()==""){
		$("#param1Chat").tips({
			side:3,
            msg:'地址',
            bg:'#AE81FF',
            time:3
        });
		$("#param1Chat").focus();
		return false;
	}
	
	if($("#param2Chat").val()==""){
		$("#param2Chat").tips({
			side:3,
            msg:'端口',
            bg:'#AE81FF',
            time:3
        });
		$("#param2Chat").focus();
		return false;
	}
	//即时聊天-----------
	
	//在线管理-----------
	if($("#param1Manage").val()==""){
		$("#param1Manage").tips({
			side:3,
            msg:'地址',
            bg:'#AE81FF',
            time:3
        });
		$("#param1Manage").focus();
		return false;
	}
	
	if($("#param2Manage").val()==""){
		$("#param2Manage").tips({
			side:3,
            msg:'端口',
            bg:'#AE81FF',
            time:3
        });
		$("#param2Manage").focus();
		return false;
	}
	//在线管理-----------
	
	$("#Form3").submit();
	$("#zhongxin").hide();
	$("#zhongxin2").show();
}
//=======================================配置NO2
//清除空格
String.prototype.trim=function(){
     return this.replace(/(^\s*)|(\s*$)/g,'');
};

//====================上传二维码=================
$(document).ready(function(){
	$("#uploadify1").uploadify({
		'buttonImg'	: 	locat+"/plugins/uploadify/uploadp.png",
		'uploader'	:	locat+"/plugins/uploadify/uploadify.swf",
		'script'    :	locat+"/plugins/uploadify/uploadFile.jsp;jsessionid="+jsessionid,
		'cancelImg' :	locat+"/plugins/uploadify/cancel.png",
		'folder'	:	locat+"/uploadFiles/uploadImgs",//上传文件存放的路径,请保持与uploadFile.jsp中PATH的值相同
		'queueId'	:	"fileQueue",
		'queueSizeLimit'	:	1,//限制上传文件的数量
		//'fileExt'	:	"*.rar,*.zip",
		//'fileDesc'	:	"RAR *.rar",//限制文件类型
		'fileExt'     : '*.jpg;*.gif;*.png',
		'fileDesc'    : 'Please choose(.JPG, .GIF, .PNG)',
		'auto'		:	false,
		'multi'		:	true,//是否允许多文件上传
		'simUploadLimit':	2,//同时运行上传的进程数量
		'buttonText':	"files",
		'scriptData':	{'uploadPath':'/uploadFiles/uploadImgs/','fileNmae':'watermark.png'},//这个参数用于传递用户自己的参数，此时'method' 必须设置为GET, 后台可以用request.getParameter('name')获取名字的值
		'method'	:	"GET",
		'onComplete':function(event,queueId,fileObj,response,data){
			$("#param1Image").val(response.trim());
		},
		'onAllComplete' : function(event,data) {
			$("#Form2").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
    	},
    	'onSelect' : function(event, queueId, fileObj){
    		$("#hasTp1").val("ok");
    	}
	});
			
});
//====================上传图片=================

function save2(){
	
	//文字水印-----------
	if($("#param1Word").val()==""){
		$("#param1Word").tips({
			side:3,
            msg:'输入水印文字内容',
            bg:'#AE81FF',
            time:3
        });
		$("#param1Word").focus();
		return false;
	}
	if($("#param2Word").val()==""){
		$("#param2Word").tips({
			side:3,
            msg:'输入字号',
            bg:'#AE81FF',
            time:3
        });
		$("#param2Word").focus();
		return false;
	}
	if($("#param3Word").val()==""){
		$("#param3Word").tips({
			side:3,
            msg:'输入X坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#param3Word").focus();
		return false;
	}
	if($("#param4Word").val()==""){
		$("#param4Word").tips({
			side:3,
            msg:'输入Y坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#param4Word").focus();
		return false;
	}
	//文字水印-----------
	
	//图片水印-----------
	if($("#param3Image").val()==""){
		$("#param3Image").tips({
			side:3,
            msg:'输入X坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#param3Image").focus();
		return false;
	}
	if($("#param4Image").val()==""){
		$("#param4Image").tips({
			side:3,
            msg:'输入Y坐标',
            bg:'#AE81FF',
            time:3
        });
		$("#param4Image").focus();
		return false;
	}
	//图片水印-----------
	
	if("ok" == $("#hasTp1").val()){
		$('#uploadify1').uploadifyUpload();
	}else{
		$("#Form2").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
}


function openThis1(){
	if($("#check1").attr("checked") == 'checked'){
		$("#isOpenWord").val('1');
	}else{
		$("#isOpenWord").val('0');
	}
}
function openThis2(){
	if($("#check2").attr("checked") == 'checked'){
		$("#isOpenImage").val('1');
	}else{
		$("#isOpenImage").val('0');
	}
}


//=======================================配置NO1
//保存
function save(){
	//系统-----------
	if($("#param1System").val()==""){
		$("#param1System").tips({
			side:3,
            msg:'输入系统名称',
            bg:'#AE81FF',
            time:3
        });
		$("#param1System").focus();
		return false;
	}

	if($("#param2System").val()==""){
		$("#param2System").tips({
			side:3,
            msg:'输入每页条数',
            bg:'#AE81FF',
            time:3
        });
		$("#param2System").focus();
		return false;
	}
	//系统-----------
	
	//邮箱-----------
	if($("#param1Email").val()==""){
		$("#param1Email").tips({
			side:1,
            msg:'输入SMTP',
            bg:'#AE81FF',
            time:3
        });
		$("#param1Email").focus();
		return false;
	}
	
	if($("#param2Email").val()==""){
		$("#param2Email").tips({
			side:1,
            msg:'输入端口',
            bg:'#AE81FF',
            time:3
        });
		$("#param2Email").focus();
		return false;
	}
	
	if($("#param3Email").val()==""){
		
		$("#param3Email").tips({
			side:3,
            msg:'输入邮箱',
            bg:'#AE81FF',
            time:3
        });
		$("#param3Email").focus();
		return false;
	}else if(!ismail($("#param3Email").val())){
		$("#param3Email").tips({
			side:3,
            msg:'邮箱格式不正确',
            bg:'#AE81FF',
            time:3
        });
		$("#param3Email").focus();
		return false;
	}

	if($("#param4Email").val()==""){
		$("#param4Email").tips({
			side:1,
            msg:'输入密码',
            bg:'#AE81FF',
            time:3
        });
		$("#param4Email").focus();
		return false;
	}
	//邮箱-----------
	
	//短信-----------
	if($("#param1Message").val()==""){
		$("#param1Message").tips({
			side:1,
            msg:'输入官网',
            bg:'#AE81FF',
            time:3
        });
		$("#param1Message").focus();
		return false;
	}
	
	if($("#param3Message").val()==""){
		$("#param3Message").tips({
			side:1,
            msg:'输入账号',
            bg:'#AE81FF',
            time:3
        });
		$("#param3Message").focus();
		return false;
	}
	
	if($("#param4Message").val()==""){
		$("#param4Message").tips({
			side:1,
            msg:'输入密码',
            bg:'#AE81FF',
            time:3
        });
		$("#param4Message").focus();
		return false;
	}
	//短信-----------
	
	$("#Form").submit();
	$("#zhongxin").hide();
	$("#zhongxin2").show();
}

function ismail(mail){
	return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
	}