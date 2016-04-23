<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>系统设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>

<input type="hidden" id="activeMenu" value="system">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
         <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
    	 <jsp:param name="activeMenu" value="consumer" />
  		</jsp:include> 
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         </div>
         <p class="main mt20"><a href="">平台管理</a> > <a href="">系统设置</a></p>
         <div class="main" id="system_div">
         <c:forEach items="${list}" var="list">
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table" id="system_table">
                  <tr>
                     <td width="130" class="text_right">主机地址：</th>
                     <td>${list.systemHostUrl}</td>
                     
                     <td width="150" class="text_right">系统映射地址：</td>
                     <td>${list.websiteStaticUrl}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">文件存放路径：</td>
                     <td>${list.storageFilePrefix}</td>
                     
                     <td width="150" class="text_right">安卓应用下载地址：</td>
                     <td>${list.andoridUrl}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">IOS应用下载地址：</td>
                     <td>${list.iosUrl}</td>
                     
                     <td width="150" class="text_right">APP前缀：</th>
                     <td>${list.appPrefix}</td>
                  </tr>
                   <tr>
                     <td width="150" class="text_right">新闻前缀：</td>
                     <td>${list.newsPrefix}</td>
                     
                     <td width="150" class="text_right">个人信息前缀：</td>
                     <td>${list.informationPrefix}</td>
                  </tr>
                  
                  
                  <tr>
                     <td width="180" class="text_right">咨询前置免费时间(秒数)：</td>
                     <td >${list.freeTime}</td>
                     
                     <td width="150" class="text_right">计费时间(秒数)：</td>
                     <td>${list.payTime}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right">注册积分：</td>
                     <td>${list.freeIntegral}</td>
                     
                     <td width="150" class="text_right">客服电话：</td>
                     <td>${list.cell}</td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right f14">对咨询师：</td>
                     <td></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">标准单价(汇点)：</td>
                     <td>${list.price}</td>
                     
                     <td width="150" class="text_right">单次咨询起价(汇点)：</td>
                     <td>${list.startPrice}</td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">临时设置时间起：</td>
                     <td id="timeDisplay">${list.timeForm}</td>
                     
                     <td width="150" class="text_right">临时设置时间止：</td>
                     <td id="timeDisplay1">${list.timeTo}</td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">浮动比例：</td>
                     <td>${list.proportion}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right f14">对咨询者：</td>
                     <td></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">标准单价(汇点)：</td>
                     <td>${list.consumerPrice}</td>
                     
                     <td width="150" class="text_right">单次咨询起价(汇点)：</td>
                     <td>${list.consumerStartPrice}</td>
                  </tr>
                 <tr>
                     <td width="150" class="text_right">临时设置时间起：</td>
                     <td id="timeDisplay2">${list.consumerTimeForm}</td>
                     
                     <td width="150" class="text_right">临时设置时间止：</td>
                     <td id="timeDisplay3">${list.consumerTimeTo}</td>
                  </tr>
                  
                  <tr>
                  <tr>
                     <td width="150" class="text_right">浮动比例：</td>
                     <td>${list.consumerProportion}</td>
                  </tr>
                  
                  <tr>
                     <td width="150" class="text_right"></td>
                     <td><a href="/admin/system/detail?id=${list.id}" class="del_btn color_ff f14">修改</a></td>
                  </tr>
               </table>
             </c:forEach>
         </div>

        <div class="footer text_center">&copy;copyright 2015 问堂有限公司  版权所有</div>
      </td>
   </tr>
</table>
<script src="/resources/admin/js/common/js/jquery-1.7.1.min.js"></script>
<script src="/resources/admin/js/common/js/jquery.longyuJs.js"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<link href="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
<script src="/resources/admin/js/exportDetail.js"></script>
<script src="/resources/admin/js/nav.js"></script>


<script>
   $(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
	   
	   var timestr=$("#timeDisplay").text();
	   timestr=$.keyToNoSTime(timestr);
	   $("#timeDisplay").text(timestr);
	   
	   var timestr1=$("#timeDisplay1").text();
	   timestr1=$.keyToNoSTime(timestr1);
	   $("#timeDisplay1").text(timestr1);
	   
	   var timestr2=$("#timeDisplay2").text();
	   timestr2=$.keyToNoSTime(timestr2);
	   $("#timeDisplay2").text(timestr2);
	   
	   var timestr3=$("#timeDisplay3").text();
	   timestr3=$.keyToNoSTime(timestr3);
	   $("#timeDisplay3").text(timestr3);
	   
	   
   });
   
//    $(document).ready(function(){
// 	   var dynatable = $('#system_table').dynatable({
// 	        table: {
// 	            bodyRowSelector: 'td'
// 	        },
// 	        dataset: {
// 	            ajaxUrl: '/admin/system/list'
// 	        }
// 	    }).data('dynatable');
//    });
	
</script>

</body>
</html>