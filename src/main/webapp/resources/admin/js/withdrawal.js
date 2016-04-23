

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
		
		//打款须知
		$(".pay_for").live("click",function(){
			var id=$(this).attr("data-id");
			$("#id").val(id);
			$("#pay_for").trigger("click");
		});
		
		$(".fany_no").click(function(){
			$.fancybox.close();
		});
    },
    initinfo: function () {
    }
});