//type:1-标题，2-新闻图片，3-正文文字，4-正文图片，5-正文视频

var editor1;
$(function () {
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
		//编辑新闻标题
		$("#sub_btn").focusout(function(){
			var account= $.trim($("#account").val());
			var name=$("#name").val();
			if(account==""){
				$.dialog({
                         type:2,
                         text:"请输入帐号！"
                });
				return false;
			}
            if(name==""){
                $.dialog({
                    type:2,
                    text:"请输入姓名！"
                });
                return false;
            }
            $("form").submit();
		});
    },
    initinfo: function () {

    },
});