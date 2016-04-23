

$(function () {
		//模拟radio切换
		$(".radio_m").click(function(){
			var id=$(this).attr("data-id");
			$(this).addClass("checked");
			$(this).siblings("a").removeClass("checked");
			$(this).siblings("input").val(id);
		});
		//模拟select功能
	    $(".selected_txt").live("click",function () {
	        $(this).parent(".select_m").find("ul").slideToggle();
	    });
	    //模拟select功能
	    $(".select_m ul li").find("a").live("click", function () {
	        var datatype = $(this).attr("data-id");
	        var dataname = $(this).text();
	        $(this).parents("div.select_m").find(".selected_txt").text(dataname);
	        $(this).parents("div.select_m").find("input[type=hidden]").val(datatype);
	        $(this).parents("div.select_m").find("ul").hide();
	    });
    	//显示登录信息
    	$(".user_nick").live("mouseenter",function(){
    		$(".user_info").show();
    	});
    	//隐藏登录信息
    	$(".user_info").live("mouseleave",function(){
    		$(".user_info").hide();
    	});
		//提交登录信息
		$(".login_btn").click(function(e){
			e.preventDefault();
			var account=$.trim($("#cellNo").val());
			var pwd=$.trim($("#password").val());
			if(account==""){
				$.dialog({type:2,text:"请输入手机号"});
				return false;
			}
			var zreg=/^1\d{10}$/;
			if(!zreg.test(account)){
				$.dialog({type:2,text:"请输入正确的手机号"});
				return false;
			}
			if(pwd==""){
				$.dialog({type:2,text:"请输入密码"});
				return false;
			}
			var yReg=/^[A-Za-z0-9]{6,12}$/;
			if(!yReg.test(pwd)){
				$.dialog({type:2,text:"密码为6~16的字母和数字组合"});
				return false;
			}
			var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
			//异步提交数据.
                $.AjaxPost("/insecure/signin?" + token, { cellNo: account, password:pwd}, function (backdata) {
                    if (backdata.c == 0) {  //登录成功
						$.dialog({
                                     type:2,
                                     text:"登录成功！"
                                 });
						window.setTimeout(function(){window.location.href="register";},800);
                    }else{ 
                    	$.dialog({type:2,text:backdata.m});
                    }
                }, true);
		});
		//提交注册信息
		$(".register_btn").click(function(e){
			e.preventDefault();
			var account=$.trim($("#cellNo").val());
			var code=$.trim($("#code").val());
			var pwd=$.trim($("#password").val());
			var repwd=$.trim($("#confirmPassword").val());
			if(account==""){
				$.dialog({type:2,text:"请输入手机号"});
				return false;
			}
			var zreg=/^1\d{10}$/;
			if(!zreg.test(account)){
				$.dialog({type:2,text:"请输入正确的手机号"});
				return false;
			}
			if(pwd==""){
				$.dialog({type:2,text:"请输入密码"});
				return false;
			}
			var yReg=/^[A-Za-z0-9]{6,12}$/;
			if(!yReg.test(pwd)){
				$.dialog({type:2,text:"密码为6~16的字母和数字组合"});
				return false;
			}
			if(repwd!=pwd){
				$.dialog({type:2,text:"两次输入的密码不一致"});
				return false;
			}
			var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
			//异步提交数据.
                $.AjaxPost("/insecure/register?" + token, { cellNo: account, code:code, password:pwd,confirmPassword:repwd}, function (backdata) {
                    if (backdata.c == 0) {  //注册成功
						window.setTimeout(function(){window.location.href="register/success";},800);
                    }else{ 
                    	$.dialog({type:2,text:backdata.m});
                    }
                }, true);
		});
		//获取验证码
		$(".code_btn").click(function(){
			var account=$.trim($("#account").val());
			if(account==""){
				$.dialog({type:2,text:"请输入手机号"});
				return false;
			}
			var zreg=/^1\d{10}$/;
			if(!zreg.test(account)){
				$.dialog({type:2,text:"请输入正确的手机号"});
				return false;
			}
			//异步提交数据.
                $.AjaxPost("getcode", { account: account}, function (backdata) {
                    if (backdata.c == 0) {  //发送成功
						$.dialog({
                                     type:2,
                                     text:"发送成功！请注意查收短信"
                                 });
						var i = 60;
						mytimer = setInterval(function () {
							if (i > 0) {
								$(".code_second").show().find("i").text(i);
								$(".code_btn").hide();
								i--;
							} else {
								window.clearInterval(mytimer);
								$(".code_second").hide();
								$(".code_btn").show();
							}
						}, 1000);
                    }else{ 
                    	$.dialog({type:2,text:backdata.m});
                    }
                }, true);
		});
});
//回车键确认登录
function enterLogin() {
    //alert(dd);
    var event = window.event || arguments.callee.caller.arguments[0];
    if (event.keyCode == 13) {
        $(".login_btn").trigger("click");
    }
}
