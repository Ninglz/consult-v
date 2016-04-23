
var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
$(function () {
		
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
    	//预览头像
    	$("input[name=headimg]").uploadPreview({ width: 75, height: 75, imgDiv: "#head_img_add img", imgType: ["bmp", "gif", "png", "jpg"] });
    	//预览证书
    	$("#certificate_img1").uploadPreview({ width: 124, height: 78, imgDiv: "#certificate_upload1 img", imgType: ["bmp", "gif", "png", "jpg"] });
    	$("#certificate_img2").uploadPreview({ width: 124, height: 78, imgDiv: "#certificate_upload2 img", imgType: ["bmp", "gif", "png", "jpg"] });
		//提交申请信息
		$(".sub_apply_btn").click(function(e){
			e.preventDefault();
			var headimg=$.trim($("#headimg").val());
			var headimg_hidden=$.trim($("#head_img_file_hidden").val());
			var realname=$.trim($("#realname").val());
			var phone=$.trim($("#phone").val());
			var IDcard=$.trim($("#IDcard").val());
			var professional=$.trim($("#professional").val());
			var certificate_img1=$.trim($("#certificate_img1").val());
			var certificate_img2=$.trim($("#certificate_img2").val());
			var certificate_img_file_hidden1=$("#certificate_img_file_hidden1").val();
			var certificate_img_file_hidden2=$("#certificate_img_file_hidden2").val();
			var introduce=$.trim($("#introduce").val());
			if(headimg_hidden==""){
				if(headimg==""){
					$.dialog({type:2,text:"请上传头像"});
					return false;
				}
			}
			if(realname==""){
				$.dialog({type:2,text:"请输入真实姓名"});
				return false;
			}
			if(phone==""){
				$.dialog({type:2,text:"请输入联系电话"});
				return false;
			}
			var zreg=/^1\d{10}$/;
			if(!zreg.test(phone)){
				$.dialog({type:2,text:"请输入正确的联系电话"});
				return false;
			}
			if(IDcard==""){
				$.dialog({type:2,text:"请输入身份证号"});
				return false;
			}
			var xreg=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
			if(!xreg.test(IDcard)){
				$.dialog({type:2,text:"请输入正确的身份证号"});
				return false;
			}
			if(professional==""){
				$.dialog({type:2,text:"请输入您的长项"});
				return false;
			}
			if(certificate_img_file_hidden1=="" && certificate_img_file_hidden2==""){
				if(certificate_img1=="" && certificate_img2==""){
					if(introduce==""){
						$.dialog({type:2,text:"请上传资历证书或输入个人说明"});
						return false;
					}
				}
			}
			$("form").submit();
		});
		//选择资历结束日期小于开始日期时
		$("#seniorityEnd").live("change",function(){
			var seniorityEnd=$(this).val();
			var seniorityStart=$("#seniorityStart").val();
			if(seniorityEnd<seniorityStart){
				$("#seniorityStart").val(seniorityEnd)
			}
		});
		//选择资历结束日期小于开始日期时
		$("#seniorityStart").live("change",function(){
			var seniorityStart=$(this).val();
			var seniorityEnd=$("#seniorityEnd").val();
			if(seniorityEnd<seniorityStart){
				$("#seniorityEnd").val(seniorityStart)
			}
		});
		//选择省，动态显示市
		$("#province").live("change",function(){
			var id=$(this).val();
			$.getCity(id,1);
		});
    },
    initinfo: function () {
    	//初始化省份列表
    	$.getProvince();
    	//初始化资历
    	$.getcredentials();
    	//初始化行业
    	$.getIndustry();
    },
    //获取资历年限
    getcredentials:function(){
    	var date=new Date();
    	var year=date.getFullYear();
    	var lastyear=1969;
    	var str='';
    	for(var i=year;i>lastyear;i--){
    		str+='<option value="'+i+'">'+i+'</option>';
    	}
    	$("#seniorityStart").append(str);
    	$("#seniorityEnd").append(str);
    	var startyear=$("#seniority_start").val();
    	var endyear=$("#seniority_end").val();
    	if(startyear!=""){
    		$("#seniorityStart").val(startyear.substr(0,4));
    	}
    	if(endyear!=""){
    		$("#seniorityEnd").val(endyear.substr(0,4));
    	}
    },
    //获取省份信息
	getProvince:function(){
		$.AjaxPost("/api/code/province/list?"+token, {}, function (backdata) {
                    if (backdata.c == 0) {  //删除成功
						var province_info=backdata.m;//[{"id":"001","name":"北京"}]
						if(province_info!=null && province_info!=""){
							var $dom=$("#province").empty();
							var str='';
							$.each(province_info,function(i,item){
								str+='<option value="'+item.id+'">'+item.name+'</option>';
								});
								$dom.append(str);
								var startyear=$("#province_id").val();
						    	if(startyear!=""){
						    		$("#province").val(startyear);
						    		$.getCity(startyear,0);
						    	}else{
						    		$.getCity(province_info[0].id,0);
						    	}
						}

                    }else {  
						$.dialog({
                                     type:2,
                                     text:backdata.m
                                 });
                    }
                }, true,true,"get");
	},
	//获取城市信息
	getCity:function(id,num){
		//根据城市id获取城市
		$.AjaxPost("/api/code/city/list?"+token, {id:id}, function (backdata) {
                    if (backdata.c == 0) {  //删除成功
						var city_info=backdata.m;//[{"id":"001","name":"北京"}]*/
						if(city_info!=null && city_info!=""){
							var $dom=$("#city").empty();
							var str='';
							$.each(city_info,function(i,item){
								str+='<option value="'+item.id+'">'+item.name+'</option>';
								});
							$dom.append(str);
							if(num==0){
							var startyear=$("#city_id").val();
						    	if(startyear!=""){
						    		$("#city").val(startyear);
						    		
						    	}
						    	}
							}
                    }else {  
						$.dialog({
                                     type:2,
                                     text:backdata.m
                                 });
                    }
                }, true,true,"get");
	},    //获取行业信息
    getIndustry:function(){
    	$.AjaxPost("/api/code/industry/list?"+token, {}, function (backdata) {
                    if (backdata.c == 0) {  //
						var industry_info=backdata.m;//[{"id":"001","name":"北京"}]
						if(industry_info!=null && industry_info!=""){
							var $dom=$("#industry").empty();
							var str='';
							$.each(industry_info,function(i,item){
								str+='<option value="'+item.id+'">'+item.name+'</option>';
								});
								$dom.append(str);
								var startyear=$("#industry_id").val();
						    	if(startyear!=""){
						    		$("#industry").val(startyear);
						    	}
						}

                    }else {  
						$.dialog({
                                     type:2,
                                     text:backdata.m
                                 });
                    }
                },  true,true,"get");
    }
});