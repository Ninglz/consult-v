/**
 * Created by Administrator on 2015/3/16.
 */


(function($){

    $.dialog=function(opt){


        var css="<style id='style' type='text/css'>";

        css+= ".bg{position: fixed; width: 100%; height: 100%; left: 0; top: 0; background: #000;opacity: 0.1; z-index: 9999; }";

        css+=".dialog-box{position: fixed; width: 100%;height: 100%;left: 0;top: 0;z-index: 10000;}";

        css+=" .dialog{ width: 23%; height: 160px; background: #fff; margin: auto;font-family: '微软雅黑'; border:1px solid #ccc;}";
		css+=" .dialog_title{ padding-left:20px; line-height: 50px; background: #32a480;border-radius: 8px 8px 0 0; font-family: '微软雅黑'; color:#fff; font-size:16px;}";
        css+=".textWrap{font-size: 14px;text-align: center; padding-top: 40px;}";
        css+=" .ask{height: 50px;margin-top: 40px; text-align:center;}";

        css+=" .ask-bt{ display:inline-block; width: 32%;background: #4ac4cc;font-size: 12px;color: #fff;text-align: center;height: 31px;line-height: 31px; border-bottom:5px solid #3eb0b7;cursor: pointer;}";
		css+=" .cancel-bt{ background: #f96159;border-bottom:5px solid #dc5750; margin-left:10px;}";
        css+=".i-hava-konwn{width: 32%;margin: auto;height: 31px;line-height: 31px;background: #4ac4cc;text-align: center;color: #fff; border-bottom:5px solid #3eb0b7;margin-top: 30px;cursor: pointer; }";

        css+="@media screen and (max-width: 640px) {";
        css+=".dialog{ width: 90%;height: 190px;background: #fafafa; border-radius: 8px;margin: auto; font-family: '微软雅黑';}";
        css+=".textWrap{ font-size: 1rem; }";
        css+=" }</style>";

        if($("#style").attr("type")==undefined){
            $(css).appendTo("head");
        }
        var dialogBox="<div class='bg'></div><div class='dialog-box'><div class='dialog'></div></div>";
        var textWrap="<div class='textWrap'>"+opt.text+"</div>";

        var askDom="<div class='ask'><a class='ask-bt'>确定</a><a class='ask-bt cancel-bt'>取消</a></div>";

        var iKnow="<div class='know'><div class='i-hava-konwn'>确认</div></div>";

        $(dialogBox).appendTo("body");
        $(".dialog").css("marginTop",($(window).height()-$(".dialog").height())/2);


        function close(){
            $(".aks").remove();
            $(".dialog-box").remove();
            $(".bg").remove();

        }
        //comfirm框
        if(opt.type==0){
            $(textWrap).appendTo(".dialog");
            $(askDom).appendTo(".dialog");

            $(".ask-bt").click(function(){

                close();

                if($(this).index()==0){
                    opt["ok"]();
                }else{
                    return false;
                }

            })
        }
        //alert框
        if(opt.type==1){
            $(textWrap).appendTo(".dialog");
            $(iKnow).appendTo(".dialog");

            $(".i-hava-konwn").click(function(){
                close();
            })
        }
		//无确认的提示框
		if(opt.type==2){
			$(".dialog").css({"background":"none","border":"none"});
			$(".bg").css({"opacity":"0"});
            $(textWrap).appendTo(".dialog");
			$(".textWrap").css({"background":"#50d2c2","border-radius":"20px","padding":"10px","color":"#fff"});
            window.setTimeout(function () { close(); }, 1000);
        }


    }





})(jQuery)