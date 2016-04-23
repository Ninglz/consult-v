$(function () {
    //点击显示客户类型
    $(".select_m").find("a.down_a").live("click",function () {
        $(this).parent(".select_m").find("ul").slideToggle();
		$(this).parent(".select_m").find(".color_list").slideToggle();
    });
    //点击选中某个客户类型
    $(".select_m ul li").find("a").live("click", function () {
        var datatype = $(this).attr("data-id");
        var dataname = $(this).text();
        $(this).parents("div.select_m").find(".selected_txt").text(dataname);
        $(this).parents("div.select_m").find("input[type=hidden]").val(datatype);
        $(this).parents("div.select_m").find("ul").hide();
    });
	//点击选中某个客户类型
    $(".color_list").find("a").live("click", function () {
        var datatype = $(this).attr("data-id");
        $(this).parents("div.select_m").find(".color_txt").css("background",datatype);
        $(this).parents("div.select_m").find("input").val(datatype);
        $(this).parent(".color_list").hide();
    });
    $(".select_m ul").live("mouseleave",function () {
        $(this).hide();
    });
	$(".select_m .color_list").live("mouseleave",function () {
        $(this).hide();
    });
});