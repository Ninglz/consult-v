$(function () {
	//点击展开对应导航
   $(".nav").live("click",function(){
   		$(this).addClass("current");
   		$(this).find("i").text("-");
   		$(this).find("dd").slideDown();
   		$(this).siblings("dl").removeClass("current");
   		$(this).siblings("dl").find("i").text("+");
   }); 
   $.ajax({
       url: "/admin/common/menu",
       type: "get",
       dataType: "text",
       success: function (data) {
           $("#menu").html(data);
         //根据链接地址显示对应的导航
           var url=$.trim($("#activeMenu").val());
           var urls=$(".left").find("a.color_5c");
           var len=$(urls).length;
           var currenturl='';
           for(var i=0;i<len;i++){
           		currenturl=$(urls[i]).attr("data-menu");
              if(url.indexOf(currenturl) != -1){
        		   		$(urls[i]).parent("dd").addClass("current");
        		   		$(urls[i]).parents("dl").addClass("current");
        			   	$(urls[i]).parents("dl").find("i").text("-");
        			   	$(urls[i]).parents("dl").find("dd").slideDown();
        			   	$(urls[i]).parents("dl").siblings("dl").removeClass("current");
        			   	$(urls[i]).parents("dl").siblings("dl").find("i").text("+");
        			   	return false;
           		}
           		
           } 
       }
   })
    
});