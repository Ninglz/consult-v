

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
		//审核未通过
		$(".check_no").click(function(){
			$("#reason").val("");
			$("#modify_export_refuse").trigger("click");
		});
		//设置咨询者级别
		$(".modify_customer_level").click(function(){
			$("#consumerLevels").val("");
			$("#modify_customer_level").trigger("click");
		});
		//设置专家级别
		$(".modify_export_level").click(function(){
			$("#expertsLevels").val("");
			$("#modify_export_level").trigger("click");
		});
		//设置积分
		$(".modify_int").click(function(){
			$("#integral").val("");
			$("#modify_int").trigger("click");
		});
		//设置专家置顶
		$(".experts_top").click(function(){
			$("#experts_top").trigger("click");
		});
		//显示大图
		$("#head_img").click(function(){
			var imgurl=$(this).attr("src");
			$("#show_big_img_div").find("img").attr("src",imgurl);
			$("#show_big_img").trigger("click");
		});
		//显示大图
		$("#credentials_img").click(function(){
			var imgurl=$(this).attr("src");
			$("#show_big_img_div").find("img").attr("src",imgurl);
			$("#show_big_img").trigger("click");
		});
		$("#credentials_img1").click(function(){
			var imgurl=$(this).attr("src");
			$("#show_big_img_div").find("img").attr("src",imgurl);
			$("#show_big_img").trigger("click");
		});
		$(".fany_no").click(function(){
			$.fancybox.close();
		});
    },
    initinfo: function () {
    }
});