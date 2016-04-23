

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
		//添加行业
		$(".work_btn").click(function(){
			$("#id").val("");
			$("#name").val("");
			$("#scale_customer").val("");
			$("#scale_export").val("");
			$("#add_industry").trigger("click");
		});
		//编辑行业
		$(".modify_industry").live("click",function(){
			var id=$(this).attr("data-id");
			var name=$(this).attr("data-name");
			var scale_customer=$(this).attr("data-scale-customer");
			var scale_export=$(this).attr("data-scale-export");
			$("#id").val(id);
			$("#name").val(name);
			$("#scale_customer").val(scale_customer);
			$("#scale_export").val(scale_export);
			$("#add_industry").trigger("click");
		});
		$(".fany_no").click(function(){
			$.fancybox.close();
		});
		//提交行业设置
		$(".fany_btn").click(function(e){
			e.preventDefault();
			var name=$.trim($("#name").val());
			var scale_customer=$.trim($("#scale_customer").val());
			var scale_export=$.trim($("#scale_export").val());
			var yReg = /^\d+$/;
         	var zReg = /^\d+(\.\d{0,2})?$/;
			if(name==""){
				$.dialog({type:2,text:"请输入行业名称"});
				return false;
			}
			if(scale_customer==""){
				$.dialog({type:2,text:"请输入咨询者比例"});
				return false;
			}
			if(!zReg.test(scale_customer)){
				$.dialog({type:2,text:"咨询者比例格式不正确"});
				return false;
			}
			if(scale_export==""){
				$.dialog({type:2,text:"请输入专家比例"});
				return false;
			}
			if(!zReg.test(scale_export)){
				$.dialog({type:2,text:"专家比例格式不正确"});
				return false;
			}
			
			var fristTh = $("#industry_table th:eq(0)");
			
			$("form").submit();
		});
    },
    initinfo: function () {
    }
});