

$(function () {
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
		//登录框失去焦点提示
		$("#login_div").find("input").focusout(function(){
			var $this=$(this);
			var valstr=$this.val();
			var num=$this.attr("data-num");
			$("#input_tip").text("");
			switch(num){
				case "1":
				valstr=$.trim(valstr);
				if(valstr==""){
					$("#input_tip").text("请输入帐号");
					break;
				}
				case "2":
				if(valstr==""){
					$("#input_tip").text("请输入密码");
					break;
				}
				case "3":
				valstr=$.trim(valstr);
				if(valstr==""){
					$("#input_tip").text("请按右图输入验证码，不区分大小写");
					break;
				}
			}
		});
		//提交登录信息
		$(".login_btn").click(function(e){
			e.preventDefault();
			var account=$.trim($("#username").val());
			var pwd=$.trim($("#password").val());
			var code=$("#code");
			if(account==""){
				$("#input_tip").text("请输入帐号");
				return false;
			}
			if(pwd==""){
				$("#input_tip").text("请输入密码");
				return false;
			}
			var codestr='';
			if (code.length == 1) {
				codestr=$.trim($(code).val());
                if ( codestr== "") {
                    $("#input_tip").text("请按右图输入验证码，不区分大小写");
					return false;
                }
            }
			$("form").submit();
		});
		
    },
    initinfo: function () {
    }
});
//回车键确认登录
function enterLogin() {
    //alert(dd);
    var event = window.event || arguments.callee.caller.arguments[0];
    if (event.keyCode == 13) {
        $(".login_btn").trigger("click");
    }
}