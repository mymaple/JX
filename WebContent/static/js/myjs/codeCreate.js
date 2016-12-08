	var arrayField = new Array();
	var index = 0;

	//打开编辑属性(新增)
	function toAddField(){
		$("#editIndex").val('');
		$("#editPropName").val('');
		
		$("#propName").val('');
		$("#propComment").val('');
		$("#propType").val('');
		$("#propLength").val('propType_String');
		$("#isWrite1").attr("checked",true);
		$("#isNull1").attr("checked",true);
		$("#propDefault").val('');
		
		$("#dialog-add").css("display","block");
	}
	
	//保存编辑属性
	function saveField(){
		var editIndex = $("#editIndex").val(); 	 //editIndex不为空时是修改
		var editPropName = $("#editPropName").val();
		
		var propName = $("#propName").val();				//属性名
		var propComment = $("#propComment").val();			//注释
		var propType = $("#propType").val();				//类型
		var propLength = $("#propLength").val();			//长度
		var isWrite = $("#isWrite1").attr("checked")?1:0;	//页面录入
		var isNull = $("#isNull1").attr("checked")?1:0;		//isNull
		var propDefault = $("#propDefault").val();			//默认值
		
		var headstr = propName.substring(0,1);
		var reg = new RegExp("^[0-9]+$");
		
		if(propName==""){
			$("#propName").tips({
				side:3,
	            msg:'输入属性名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#propName").focus();
			return false;
		}else if(!isSame(propName)&&reg.test(headstr)){
			$("#propName").tips({
				side:3,
	            msg:'属性名首字母必须为字母或下划线',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#propName").focus();
			return false;
		}else if(isSame(propName)&&
				(editIndex === ''||(editIndex != ''&&editPropName != propName))){
			$("#propName").tips({
				side:3,
	            msg:'属性名重复',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#propName").focus();
			return false;
		}
		
		if(propComment==""){
			$("#propComment").tips({
				side:3,
	            msg:'输入注释',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#propComment").focus();
			return false;
		}
		
		var fields = propName + ',maple,' + propComment + ',maple,' + propType 
					+ ',maple,' + propLength + ',maple,' + isWrite
					+ ',maple,' + isNull + ',maple,' + propDefault;
		
		if(editIndex == ''){
			addArrayField(fields);
		}else{
			editArrayField(fields,editIndex);
		}
		$("#dialog-add").css("display","none");
	}
	
	
	//保存属性后往数组添加元素
	function addArrayField(value){
		arrayField[index] = value;
		appendField(value);
	}

	//打开编辑属性(修改)
	function toEditField(value,editIndex){
		var fieldarray = value.split(',maple,');
		$("#editIndex").val(editIndex);
		$("#editPropName").val(fieldarray[0]);
		
		$("#propName").val(fieldarray[0]);
		$("#propComment").val(fieldarray[1]);
		$("#propType").val(fieldarray[2]);
		$("#propLength").val(fieldarray[3]);
		$(fieldarray[4]==='1'?"#isWrite1":"#isWrite0").attr("checked",true);
		$(fieldarray[5]==='1'?"#isNull1":"#isNull0").attr("checked",true);
		$("#propDefault").val(fieldarray[6]);
	
		$("#dialog-add").css("display","block");
	}
	
	//修改属性
	function editArrayField(value,editIndex){
		arrayField[editIndex] = value;
		index = 0;
		$("#fields").html('');
		for(var i=0;i<arrayField.length;i++){
			appendField(arrayField[i]);
		}
	}
	
	//删除数组添加元素并重组列表
	function removeField(value){
		index = 0;
		$("#fields").html('');
		arrayField.splice(value,1);
		for(var i=0;i<arrayField.length;i++){
			appendField(arrayField[i]);
		}
	}
	
	//关闭编辑属性
	function toCancel(){
		$("#dialog-add").css("display","none");
	}
	
	//生成
	function save(){
		
		if($("#conModule").val()==""){
			$("#conModule").tips({
				side:3,
	            msg:'输入控制模块',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#conModule").focus();
			return false;
		}
		
		if($("#objectModule").val()==""){
			$("#objectModule").tips({
				side:3,
	            msg:'输入类模块',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#objectModule").focus();
			return false;
		}
		
		if($("#objectName").val()==""){
			$("#objectName").tips({
				side:3,
	            msg:'输入类名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#objectName").focus();
			return false;
		}else{
			var pat = new RegExp("^[A-Za-z]+$");
			if(!pat.test($("#objectName").val())){
				$("#objectName").tips({
					side:3,
		            msg:'只能输入字母',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#objectName").focus();
				return false;
			}
		}

		
		if($("#fields").html() == ''){
			$("#table_report").tips({
				side:3,
	            msg:'请添加属性',
	            bg:'#AE81FF',
	            time:2
	        });
			return false;
		}
		
		if(!confirm("确定要生成吗?")){
			return false;
		}
		
		$("#Form").submit();

		$("#objectName").val('');
		$("#productc").tips({
			side:3,
            msg:'提交成功,等待下载',
            bg:'#AE81FF',
            time:9
        });
		window.parent.jzts();
		setTimeout("top.Dialog.close()",10000);
		
	}
	
	



	
	

	

	//追加属性列表
	function appendField(value){	
		var fieldarray = value.split(',maple,');
		$("#fields").append(
			'<tr>'+
			'<td class="center">'+fieldarray[0]+'<input type="hidden" name="propName" id="propName'+index+'" value="'+fieldarray[0]+'"></td>'+
			'<td class="center">'+fieldarray[1]+'<input type="hidden" name="propComment" id="propComment'+index+'" value="'+fieldarray[1]+'"></td>'+
			'<td class="center">'+fieldarray[2]+'<input type="hidden" name="propType" id="propType'+index+'" value="'+fieldarray[2]+'"></td>'+
			'<td class="center">'+fieldarray[3]+'<input type="hidden" name="propLength" id="propLength'+index+'" value="'+fieldarray[3]+'"></td>'+
			'<td class="center">'+fieldarray[4]+'<input type="hidden" name="isWrite" id="isWrite'+index+'" value="'+fieldarray[4]+'"></td>'+
			'<td class="center">'+fieldarray[5]+'<input type="hidden" name="isNull" id="isNull'+index+'" value="'+fieldarray[5]+'"></td>'+
			'<td class="center">'+fieldarray[6]+'<input type="hidden" name="propDefault" id="propDefault'+index+'" value="'+fieldarray[6]+'"></td>'+
			'<td class="center">'+
				'<input type="hidden" name="field'+index+'" id="field'+index+'" value="'+value+'">'+
				'<a class="btn btn-mini btn-info" title="编辑" onclick="toEditField(\''+value+'\',\''+index+'\')"><i class="icon-edit"></i></a>&nbsp;'+
				'<a class="btn btn-mini btn-danger" title="删除" onclick="removeField(\''+index+'\')"><i class="icon-trash"></i></a>'+
			'</td>'+
			'</tr>'
		);
		index++;
		$("#fieldCount").val(index);
	}
	
	//判断属性名是否重复
	function isSame(value){
		for(var i=0;i<arrayField.length;i++){
			var array0 = arrayField[i].split(',maple,')[0];
			if(array0 == value){
				return true;
			}
		}
		return false;
	}
	