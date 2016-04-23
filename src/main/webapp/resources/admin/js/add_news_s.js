//type:1-标题，2-新闻图片，3-正文文字，4-正文图片，5-正文视频

var editor1;
$(function () {
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
		//编辑新闻标题
		$("#sub_btn").click(function(){
			var title= $.trim($("#title").val());
			var img=$("#img").val();
            var summary=$("#summary").val();
            var industry = $("#industry").val();
			if(title==""){
				$.dialog({
                         type:2,
                         text:"请输入新闻标题！"
                });
				return false;
			}
            if(img==""){
                $.dialog({
                    type:2,
                    text:"请选择新闻图片！"
                });
                return false;
            }
            if(summary==""){
                $.dialog({
                    type:2,
                    text:"请输入新闻摘要！"
                });
                return false;
            }
            if(industry==""){
            	$.dialog({
            		type:2,
            		text:"请选择行业！"
            	});
            	return false;
            }
            var content=UM.getEditor('container').getContent();
            if(content=="" || content==null || content.length==0){
                $.dialog({
                    type:2,
                    text:"请输入新闻内容！"
                });
                return false;
            }
            $("#content").val(content);
            $("#news_form").submit();
		});
        //编辑活动
        $("#sub_activity").click(function(){
            var name= $.trim($("#name").val());
            var img=$("#img").val();
            var describe=$("#describe").val();
            var url=$("#url").val();
            if(name==""){
                $.dialog({
                         type:2,
                         text:"请输入活动名称！"
                });
                return false;
            }
            if(img==""){
                $.dialog({
                    type:2,
                    text:"请选择活动图片！"
                });
                return false;
            }
            if(describe==""){
                $.dialog({
                    type:2,
                    text:"请输入活动简介！"
                });
                return false;
            }
            if(url==""){
                $.dialog({
                    type:2,
                    text:"请输入活动连接！"
                });
                return false;
            }
            $("#activity_form").submit();
        });
        $("input[name=img]").uploadPreview({ width: 100, height: 100, imgDiv: "#img_show img", imgType: ["bmp", "gif", "png", "jpg"] });
    },
    initinfo: function () {

    },
});