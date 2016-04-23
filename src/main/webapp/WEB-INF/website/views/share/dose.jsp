<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <title>服药统计</title>
     <script type="text/javascript" src="/resources/website/js/jquery.min.js"></script>
     <script type="text/javascript" src="/resources/website/js/index.js"></script>
  <link rel="stylesheet" href="/resources/website/css/style.css"/>
  <script type="text/javascript">
  
  $(function(){
	  $("#doughnutChart").drawDoughnutChart([
	    {  value : ${quatityontime },  color: "#2DC0E8" },
	    { value:  ${quatitydelay },   color: "#F66288" },
	    {  value:  ${quatitymiss },   color: "#F7F0F0" }
	  ]);
	});
  </script>
    <style>
    * { margin: 0; padding: 0; }
        body,dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, span, a, input, textarea, p, blockquote, th, td, header, nav, footer, article, section, aside, time, figure {
            margin: 0;
            padding: 0;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0)
        }
        html{
            font-size: 62.5%;
            height: 100%;
            overflow: hidden;
            -webkit-text-size-adjust: 100%;
        }
        body{
            font: 12px/1.5 Microsoft Yahei,Heiti SC,Helvetica,HelveticaNeue,'Hiragino Sans GB', sans-serif;
            position: relative;
            margin: 0 auto;
            min-width: 320px;
            word-break: break-all;
            height: 100%;
            /*overflow: hidden;*/
            max-width: 640px;
            background: #eeeeee;
        }
        ul li{
            list-style: none;
        }
        /*.ul-ul li{*/
            /**/
        /*}*/

    </style>
</head>
<meta charset="utf-8"/>
<body style="width: 100%;height: 100%;background: white">
    <div style="width: 100%;height: 100%;overflow: auto">
        <div style="width: 100%; overflow: hidden;height: 40px;text-align: center;line-height: 40px;font-size: 1.3em;background: #F0F3F6">
            <label for="meeting"><span style="font-size: 0.9em">开始：</span></label><input id="meeting" type="text" value="${fromday }"  readonly="readonly" style="width: 28%"/>
            &nbsp;
            <label for="meeting"><span style="font-size: 0.9em">结束：</span></label><input id="meetinga" type="text" value="${today }"  readonly="readonly" style="width: 28%"/>
            <!--<p><&nbsp;&nbsp;&nbsp;<span style="font-weight: bold">10-01</span>&nbsp;~&nbsp;<span style="font-weight: bold">10-07</span>&nbsp;&nbsp;&nbsp;></p>-->
        </div>
        <div style="background: white;width: 100%;height: 180px;">
            <div style="width: 46%;height: 180px;float: left;">
                <div style="width: 145px;height: 145px;margin-top: 20px;margin-left: 10px">
                    <div id="doughnutChart" class="chart"></div>
                </div>


            </div>
            <div style="width: 54%;height: 150px;float: left">
                <div style="margin-top: 25px;width: 82%;height: 120px;margin-left: 10px">

                    <div style="width: 100%;height: 30px;">
                        <p style="font-size: 1.5em;color: #434343;float: left;line-height: 35px">计划服用量</p><p style="font-size: 1.5em;color: #434343;float: right;"><span style="font-size: 1.2em">${quatityplan }</span>次</p>
                    </div>
                    <div style="width: 100%;height: 30px;">
                       <ul style="width: 100%;height: 30px;float: left">
                           <li style="float: left"><img src="/resources/website/img/statistics_03.png" style="width: 12px;height: 12px;margin-top: 6px;"/></li>
                           <li style="float: left;font-size: 1.3em;color: #8E8E8E;margin-left: 13px">按时服用</li>
                           <li style="float: right;font-size: 1.3em;color: #8E8E8E;"><span >${quatityontime}</span>次</li>
                       </ul>
                    </div>
                    <div style="width: 100%;height: 30px;">
                        <ul style="width: 100%;height: 30px;float: left">
                            <li style="float: left"><img src="/resources/website/img/statistics_06.png" style="width: 12px;height: 12px;margin-top: 6px;"/></li>
                            <li style="float: left;font-size: 1.3em;color: #8E8E8E;margin-left: 13px">未按时服用</li>
                            <li style="float: right;font-size: 1.3em;color: #8E8E8E;"><span >${ quatitydelay}</span>次</li>
                        </ul>
                    </div>
                    <div style="width: 100%;height: 30px;">
                        <ul style="width: 100%;height: 30px;float: left">
                            <li style="float: left"><img src="/resources/website/img/statistics_08.png" style="width: 12px;height: 12px;margin-top: 6px;"/></li>
                            <li style="float: left;font-size: 1.3em;color: #8E8E8E;margin-left: 13px">未服用</li>
                            <li style="float: right;font-size: 1.3em;color: #8E8E8E;"><span >${quatitymiss }</span>次</li>
                        </ul>

                    </div>
                </div>
            </div>

        </div>
        <div style="background: white;width: 100%;height:100%;margin-top: 10px">
        
            <div style="width:6%;height: 100%;border-right:3px solid #F4FCFE;float: left"></div>
            <div style="float: right;width: 87%;height: 100%">
            <c:forEach items="${data}" var="data">
            <div style="width: 100%;height: 55px;border-bottom: 1px solid #F7F7F7">
                    <div style="width: 41%;height: 30px;position: relative;top: 10px;left: -11.9%;background: #F4FCFE;border-radius: 15px">
                        <img src="/resources/website/img/timeicon.png" style="position: relative;top:5px;left: 5px"/>
                        &nbsp; &nbsp;<span style="color: #2DC0E8;font-weight: bold;line-height: 10px;font-size: 1.2em;position: relative">${data.date }</span>
                    </div>
                </div>
                <c:forEach items="${data.detailData}"  var="detail">
                     <div style="width: 100%;height: 55px;border-bottom: 1px solid #F7F7F7">
                      <c:if test="${detail.type=='按时'}">
                      <img src="/resources/website/img/time.png" style="position: relative;left: -11%;width: 24px;height: 24px;top: 16px"/>
                      </c:if>
                      <c:if test="${detail.type=='未按时'}">
                      <img src="/resources/website/img/notime.png" style="position: relative;left: -11%;width: 24px;height: 24px;top: 16px"/>
                      </c:if>
                      <c:if test="${detail.type=='未服用'}">
                      <img src="/resources/website/img/noeat.png" style="position: relative;left: -11%;width: 24px;height: 24px;top: 16px"/>
                      </c:if>
                    
                    <ul class="ul-ul">
                        <li style="float: left;font-size: 1.5em;margin-top: -14px;color: #4D4D4D;width: 17%;">${detail.time }</li>
                        <!--<li style="float: left;font-size: 1.4em;margin-top: -13px;margin-left: 3%;color: #4D4D4D;width: 19%;">保健品</li>-->
                        <li style="float: left;font-size: 1.4em;margin-top: -13px;margin-left: 3%;color: #666666;width: 31%;text-overflow: ellipsis;overflow: hidden;white-space: nowrap; ">${detail.medicineName }</li>
                        <li style="float: right;margin-left: 3%;margin-top: -26px"><a href="#"><img src="/resources/website/img/more.png" style="width: 8px;height: 21px;margin-top: 15px;margin-right: 10px"/></a></li>
                        <li style="float: right;font-size: 1.2em;margin-top: -10px;margin-left: 5%;color: #2DC0E8">${detail.type }</li>
                    </ul>

                </div>
                </c:forEach>

            </c:forEach>
              

            </div>


        </div>

    </div>
<script>

</script>
</body>
</html>