/**
 * Created by Administrator on 2015/3/12.
 */

(function($){

    $.selectDate=function(opt){
        var yearSelectObj
        var monthSelectObj
        var daySelectObj

        var yearDom="<option>选择年</option>"
        var monthDom="<option>选择月</option>"
        var dayDom="<option>选择日</option>"

        var yearLen;
        var dayLen;

        var from=opt.dateStart;
        var end=opt.dateEnd;
        var date=new Date();

        var yearNum;
        var monthNum;

        //var isLeapYear=function(optYear){
        //    return optYear%400==0||(optYear%4==0&&optYear%100!=0)?true:false;
        //}

        var GetDayOfMonth=function(optYear,optMonth){
            var dayRs;
           var  isLeapYear=optYear%400==0||(optYear%4==0&&optYear%100!=0)?true:false;

            if(optMonth==1||optMonth==3||optMonth==5||optMonth==7||optMonth==8||optMonth==10||optMonth==12){
                dayRs=31;
            }else if(optMonth==2){
                dayRs=isLeapYear?29:28;
            }else{
                dayRs=30;
            }

            return dayRs;
        }

        yearSelectObj=$("."+opt.yearSelect);
        monthSelectObj=$("."+opt.monthSelect);
        daySelectObj=$("."+opt.daySelect);


        monthSelectObj.attr("disabled","disabled");
        daySelectObj.attr("disabled","disabled");

        if(end==undefined||end==""){
            end=date.getFullYear();
        }

        var yearLen=end-from+1;

        for(var i=0;i<yearLen;i++){
            yearDom+="<option>"+(end-i)+"</option>";
        }
        $(yearDom).appendTo(yearSelectObj);

        var monthLen=12;

        for(var i=0;i<monthLen;i++){
			var m_set=parseInt(i+1);
			if(parseInt(i+1)<10){
				m_set="0"+m_set;
			}
            monthDom+="<option>"+(m_set)+"</option>";
        }
        $(monthDom).appendTo(monthSelectObj);
        //console.log(yearDom)

        $(dayDom).appendTo(daySelectObj);

        yearSelectObj.change(function(){
            monthSelectObj.removeAttr("disabled");
            $(monthSelectObj).empty();
            $(monthDom).appendTo(monthSelectObj);
            daySelectObj.attr("disabled","disabled");
            $(daySelectObj).empty();
            $(dayDom).appendTo(daySelectObj);
            //yearNum=parseInt($(this).find("option:selected").text());
            //alert(yearNum)
            //alert(isLeapYear(yearNum))

        })

        monthSelectObj.change(function(){

            yearNum=parseInt($(yearSelectObj).find("option:selected").text());
            monthNum=parseInt($(this).find("option:selected").text());
            //alert(yearNum)
            //alert(monthNum)
           dayLen=GetDayOfMonth(yearNum,monthNum);
            //alert(dayLen)
            dayDom="<option>选择日</option>"
            for(var i=0;i<dayLen;i++){
				var d_set=parseInt(i+1);
				if(parseInt(i+1)<10){
					d_set="0"+d_set;
				}
                dayDom+="<option>"+(d_set)+"</option>";
            }
            daySelectObj.empty();
            $(dayDom).appendTo(daySelectObj);
            daySelectObj.removeAttr("disabled");
        })



    }

})(jQuery)