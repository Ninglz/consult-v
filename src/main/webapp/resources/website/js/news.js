var currentpage_zcj = 1;
var pagenum_zcj = 4;
var sort_zcj='';
var type_zcj='';
var List_zcj=[];
var Num_zcj=[];
var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
$(function () {
        $.regEvent();
        $.initinfo();
    
});

$.extend({
    regEvent: function () {
        //页码点击效果
        $(".pagenum").live("click", function () {
            currentpage_zcj = $(this).text();
			$.getNewsListByAllTerm();
        });
        //上一页
        $(".prev_page").live("click", function () {
            var current = $("#pagenum").find("a.current").text();
            if (current != 1) {
                currentpage_zcj = parseInt(current) - 1;
				$.getNewsListByAllTerm();
            }
        });
        //下一页
        $(".next_page").live("click", function () {
            var current = $("#pagenum").find("a.current").text();
            var pages = $("#pagenum").find("a.pagenum");
            if (current != $(pages).length) {
                currentpage_zcj = parseInt(current) + 1;
                $.getNewsListByAllTerm();
            }
        });
		//选择分类
		$(".news_type_fl").find("a").live("click",function(){
			type_zcj=$(this).attr("data-id");
			$.getNewsListByAllTerm();
			$(this).addClass("current");
			$(this).siblings("a").removeClass("current");
		});
		//选择排序
		$(".news_sort").click(function(){
			if($(this).hasClass("up")){
				sort_zcj="desc";
				$(this).removeClass("up");
			}else{
				sort_zcj="asc";
				$(this).addClass("up");
			}
			$.getNewsListByAllTerm();
		});
    },
    initinfo: function () {
		//获取推荐的分类
		$.getTypelist();
        //根据页码获取数据
		$.getNewsListByAllTerm();
		
    },
	//获取分类
	getTypelist:function(obj){
		$.AjaxPost("/api/code/industry/list?"+token, {}, function (backdata) {
            if (backdata.c == 0) {
            	var obj=backdata.m;
				if(obj!=null && obj!=""){
				   var $dom=$(".news_type_fl").empty();
				   var str='';
				   $.each(obj,function(i,item){
					   if(i==0){
						   str+='<a href="javascript:" data-id="" class="current">全部</a><a href="javascript:" data-id="'+item.id+'">'+item.name+'</a>';
					   }else{
					       str+='<a href="javascript:" data-id="'+item.id+'">'+item.name+'</a>';
					   }
				   });	
				   $dom.append(str);
		}
            } else {
                $.dialog({
                         type:2,
                         text:"查询失败！"
                });
            }
        }, true,true,"get");
	},
	//根据所有条件查询新闻列表
    getNewsListByAllTerm: function () {
        //异步提交数据,参数：currentpage,要查询的页码;pagenum，每页的条数,keyword关键字，type分类，0表示所有，
        $.AjaxPost("/news/list?"+token, {timeSort:sort_zcj,industryId:type_zcj,page:currentpage_zcj, size:pagenum_zcj}, function (backdata) {
            if (backdata.c == 0) {
                List_zcj = backdata.m.records;
                Num_zcj = backdata.m.totalRecordCount;
                $.ProductPageShow(Num_zcj,pagenum_zcj,"f");
                $.CurrentPageShow(currentpage_zcj);
				var str='';
				var $dom=$(".news_list").empty();
				$.each(List_zcj,function(i,item){
					var classstr=i%2==1?"last":"";
					str+='<a href="/news/'+item.id+'"><li  class="'+classstr+'">\
					        <img src="'+item.titleImg+'" class="news_img fl" />\
					        <div class="fl news_content">\
					          <p class="news_title mt5">'+item.title+'</p>\
					          <p class="clearfix color_979 mt5">\
					            <span class="fl">'+item.lastModified+'</span>\
					            <span class="fr"><img src="/resources/website/img/news_type_tip.jpg" class="mr5" />'+item.industryName+'</span>\
					          </p>\
					          <p class="mt10 color_66">'+$.mySubString(item.content,47)+'</p>\
					        </div>\
					      </li></a>';
				});
				$dom.append(str);

            } else {
                $.dialog({
                         type:2,
                         text:"查询失败！"
                });
            }
        }, true);
    }

});

function toDeatil(id){
	$("#detail_id").val(id);
	$("#detail_form").submit();
}