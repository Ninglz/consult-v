<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>地区设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="areaConsumer">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 系统设置 > 地区浮动比例设置</p>
         
         <div class="main">
            <p class="mt20">
               	选择省份：
	               <select name="provincesId" id="province" class="fany_input ml10">
	                  <option value="" selected="selected">请选择所在省份</option>
	                  <c:forEach items="${provinces}" var="province">
	                  	  <option value="${province.id}">${province.name}</option>
	                  </c:forEach>
	               </select>
            </p>
            <form action="" method ="" id="city_form">
               <ul class="clearfix industry_ul mt20">
               </ul>
               <p class="mt20 text_center"><a href="javascript:" class="fany_btn f14 color_ff ml10">设置</a></p>
            </form>
            
       </div>
         
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
      </td>
   </tr>
</table>
<link href="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
<script src="/resources/admin/js/areaLevel.js"></script>

<script>
$(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
  });
  
$(".fany_btn").click(function(){
    var inputs=$(".industry_ul").find("input");
    var len=$(inputs).length;
    var yReg = /^\d+$/;
    var zReg = /^\d+(\.\d{0,2})?$/;
    for(var i=0;i<len;i++){
       if($.trim($(inputs[i]).val())==""){
          $.dialog({type:2,text:"请填写所有地区的比例设置"});
          return false;
       }
       if(!zReg.test($.trim($(inputs[i]).val()))){
          $.dialog({type:2,text:"输入的比例格式不正确"});
          return false;
       }
    }
    citySubmit();
 });
function citySubmit(){
	  var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
	  var params = $("#city_form").serialize()+ "&" + token;
	  var id= $("#province").val();
    $.ajax({
        type: "POST",
        url: "/admin/area/save/consumer?"+params,
        data: {id:id},
        success: function (data) {
      	  if(data="success"){
      		$.dialog({type:2,text:"咨询者地区比例设置成功"});
       	 }else{
      		$.dialog({type:2,text:"咨询者地区比例设置失败"});
       	 }
        }
    },true);
}

</script>

</body>
</html>
