
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <title>一键体检</title>
    <link rel="stylesheet" href="/resources/website/css/examination.css" media="screen" type="text/css" />

</head>

<body >
    <div class="warp">
        <div class="title">你的爱车环境体检报告</div>
        <div class="times">
            <div class="time-in">
                <div class="select-select">
                <input type="hidden" id="mac_" value="${mac}"/>
                    <div id="span-span">最近 ${day } 天</div>&nbsp;&nbsp;
                    <img src="/resources/website/img/pic-one.png" alt="" width="9px" height="9px" class="pic"/>
                </div>
                <lable id="lblSelect">
                    <select  id="selectPointOfInterest" >
                        <option value="7">最近 7 天</option>
                        <option value="15">最近 15 天</option>
                        <option value="30">最近 30 天</option>
                    </select>
                    <script type="text/javascript">
                    
                    //$("#selectPointOfInterest option[value='${day}']").
                    </script>
                </lable>
            </div>
        </div>
        <div class="lablea">
            <span class="ample"></span>优
            <span class="good"></span>良
            <span class="differ"></span>差
        </div>
        <div class="content-top">
            <div class="left">
                <div class="leftpieChart"  id="chart">
                    <div class="yuan">
                        <div class="yuan-in">
                            <div >
                                <canvas id="chart-area" width="86" height="86"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="left-font">
                    <p class="pone">行驶：<span>${driving.travel }</span> 小时</p>
                    <p>停车：<span>${driving.parking}</span> 小时</p>
                </div>
            </div>
            <div class="center">
                <div class="centerpieChart">
                    <div class="yuan">
                        <div class="yuan-in">
                            <div >
                                <canvas id="chart-areb" width="86" height="86"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="center-font">
                   <p>行驶车内环境</p>
                </div>
            </div>
            <div class="right">
                <div class="rightpieChart">
                    <div class="yuan">
                        <div class="yuan-in">
                            <div >
                                <canvas id="chart-arec" width="86" height="86"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="right-font">
                     <p>停车车内环境</p>
                </div>
            </div>
        </div>
        <div class="bottom">
            <div class="histogram-container" id="histogram-container"></div>
        </div>
        <div class="font">
            行车个检测参数平均值
        </div>
        <div class="conclusion">
            <p>结论：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span>${message}</span>
            </p>
        </div>
        <div class="kong"></div>
    </div>

<script type="text/javascript" src="/resources/website/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/resources/website/js/Chart.js"></script>
<script type="text/javascript" src="/resources/website/js/excanvas.js"></script>
<script type="text/javascript" src="/resources/website/js/examination.js"></script>
<script type="text/javascript">

//第一个
var pieDataa = [
    //驾驶
    {
        value: ${driving.travel },
        color:"#01E4AD",
        label: "小时"
    },
    //停车
    {
        value: ${driving.parking},
        color:"#272D51",
        label: "小时"
    }
];
//第二个
var pieDatab = [
    //良
    {
        value: ${travel.good},
        color:"#0E75BE",
        label: "天数"
    },
    //差
    {
        value: ${travel.bad},
        color: "#B21145",
        label: "天数"
    },
    //优
    {
        value: ${travel.excellent},
        color: "#12B51A",
        label: "天数"
    }

];

//第三个
var pieDatac = [
    //良
    {
        value: ${parking.good},
        color:"#0E75BE",
        label: "天数"
    },
    //差
    {
        value: ${parking.bad},
        color: "#B21145",
        label: "天数"
    },
    //优
    {
        value: ${parking.excellent},
        color: "#12B51A",
        label: "天数"
    }
];

//json数据格式及调用方法：
var data=[];
var index=0;

if('${data.pm}'!=""){
	var pm ={"id":1,"name":"PM2.5<br/>(ug/m³)","per":'${data.pm}'};
	data[index++]=pm;
}
if('${data.tvoc}'!=""){
	var tvoc ={"id":2,"name":"TVOC<br/>(ppm)","per":'${data.tvoc}'};
	data[index++]=tvoc;
}
if('${data.formaldehyde}'!=""){
	var formaldehyde ={"id":3,"name":"甲醛<br/>(mg/m³)","per":'${data.formaldehyde}'};
	data[index++]=formaldehyde;
}
if('${data.carbon_dioxide}'!=""){
	var carbon_dioxide ={"id":4,"name":"二氧化碳<br/>(ppm)","per":'${data.carbon_dioxide}'};
	data[index++]=carbon_dioxide;
}
if('${data.carbon_monoxide}'!=""){
	var carbon_monoxide ={"id":5,"name":"一氧化碳<br/>(ppm)","per":'${data.carbon_monoxide}'};
	data[index++]=carbon_monoxide;
}

var dataArr={
    "data":data
};
new histogram().init(dataArr.data);

$("#selectPointOfInterest").val('${day}');
</script>
</body>
</html>