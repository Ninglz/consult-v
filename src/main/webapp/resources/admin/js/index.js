

$(function () {
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
    	//初始化添加员工弹出层设置
        $(".fancyboxBtn").fancybox({
            'titlePosition': 'outside', //标题的位置,可以设置为'outside', 'inside' or 'over'
            'transitionIn': 'elastic',  //窗口显示的方式，可以设置为'elastic', 'fade' or 'none'
            'centerOnScroll': true,
            'transitionOut': 'elastic',
            'scrolling': 'no',
            'hideOnOverlayClick': false,
            'showCloseButton': false,
            'padding': 0,
            'onComplete': $.initAddMemberWidth
        });
		//设置推送内容
		$(".work_btn").live("click",function(){
			$("#content").val("");
			$("#send_msg").trigger("click");
		});
		//设置取消按钮
		$(".fany_no").click(function(){
			$.fancybox.close();
		});
		//提交审核未通过
		$(".enter_refuse").click(function(){
			var content=$.trim($("#content").val());
			if(content==""){
				$.dialog({type:2,text:"请输入推送内容"});
				return false;
			}
			var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
			$.AjaxPost("/admin/consumer/push?"+token, {content:content}, function (backdata) {
                    if (backdata.ReCode == 1000) {  //序号修改成功
                    	$.fancybox.close();
                    	$.dialog({
                                     type:2,
                                     text:"推送成功！"
                                 });
                    }else {  
						$.dialog({
                                     type:2,
                                     text:"推送失败！"
                                 });
                    }
                }, true);
		});
		
    },
    initinfo: function () {
    }
});