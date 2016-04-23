var cityobj=[];

$(function () {
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
    	//切换省份
    	$("#province").live("change",function(){
    		var id=$(this).val();
    		$.getCity(id);
    	});
		
    },
    initinfo: function () {
    	var id=$("#province").val();
    	$.getCity(id);
    },
    
    //获取城市
    getCity:function(id){
    	var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
		$.AjaxPost("/admin/area/getCity?"+token, {id:id}, function (backdata) {
                    if (backdata.ReCode == 1000) {  //查询成功
                    	if(backdata.ReData!=null && backdata.ReData!=""){
                    		cityobj=backdata.ReData;
                    		var str='';
                    		var $dom=$(".industry_ul").empty();
                    		$.each(cityobj,function(i,item){
                    			str+='<li class="fl">\
					                     <span class="text_right left_txt">'+item.name+'：</span><input type="text" name="proportion" id="" class="fany_input w100 ml10" value="'+item.consumerProportion+'"/>\
					                  </li>';
                    		});
                    		$dom.append(str);
					}else{
						//alert("无数据,请联系后台管理人员!");
					}
                    }else {  
						$.dialog({
                                     type:2,
                                     text:"查询失败！"
                                 });
                    }
                }, true);
    }
});