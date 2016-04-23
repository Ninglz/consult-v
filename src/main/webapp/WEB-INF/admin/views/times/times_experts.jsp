<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>时间段设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="timesExperts">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 系统设置 > 时间段浮动比例设置</p>
         <div class="main">
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table mt20">
                  <tr>
                     <td>星期/时</td>
                     <td>星期日</td>
                     <td>星期一</td>
                     <td>星期二</td>
                     <td>星期三</td>
                     <td>星期四</td>
                     <td>星期五</td>
                     <td>星期六</td>
                  </tr>
                  <tr>
                     <td class="times">
                        <p>00:00-00:59</p>
                        <p>01:00-01:59</p>
                        <p>02:00-02:59</p>
                        <p>03:00-03:59</p>
                        <p>04:00-04:59</p>
                        <p>05:00-05:59</p>
                        <p>06:00-06:59</p>
                        <p>07:00-07:59</p>
                        <p>08:00-08:59</p>
                        <p>09:00-09:59</p>
                        <p>10:00-10:59</p>
                        <p>11:00-11:59</p>
                        <p>12:00-12:59</p>
                        <p>13:00-13:59</p>
                        <p>14:00-14:59</p>
                        <p>15:00-15:59</p>
                        <p>16:00-16:59</p>
                        <p>17:00-17:59</p>
                        <p>18:00-18:59</p>
                        <p>19:00-19:59</p>
                        <p>20:00-20:59</p>
                        <p>21:00-21:59</p>
                        <p>22:00-22:59</p>
                        <p>23:00-23:59</p>
                     </td>
                     <td colspan="7">
                        <form id="times_form" action="" method="post">
                           <table cellpadding="0" cellspacing="0" width="100%" class="form_table_inside">
                           <c:forEach var = "obj" items ="${list }" varStatus ="status" >
				               <c:if test="${status.count eq 1 || (status.count-1) % 7 eq 0}">    
				      			<tr>    
				     		   </c:if>    
				 				 <td><input type="text" name="times" id="" class="fany_input w100" maxlength="4" value="${obj.expertsProportion}"/></td>
				 			  	<c:if test="${status.count %7 eq 0 || status.count eq 7}">    
				      			</tr>    
				      			</c:if> 
			               </c:forEach>
                           </table>
                        </form>
                     </td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right"></td>
                     <td colspan="7"><a href="javascript:void(0);" class="fany_btn f14 color_ff">设置</a></td>
                  </tr>
               </table>
            </div>
         
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
      </td>
   </tr>
</table>


<script>
$(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
   //提交设置
   $(".fany_btn").click(function(){
      var inputs=$(".form_table_inside").find("input");
      var len=$(inputs).length;
      var yReg = /^\d+$/;
      var zReg = /^\d+(\.\d{0,2})?$/;
      for(var i=0;i<len;i++){
         if($.trim($(inputs[i]).val())==""){
            $.dialog({type:2,text:"请填写所有时间段的比例设置"});
            return false;
         }
         if(!zReg.test($.trim($(inputs[i]).val()))){
            $.dialog({type:2,text:"输入的比例格式不正确"});
            return false;
         }
      }
      timesSubmit();
   });
});
  
  function timesSubmit(){
	  var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
	  var params = $("#times_form").serialize()+ "&" + token;
      $.ajax({
          type: "POST",
          url: "/admin/times/save/experts",
          data: params,
          success: function (data) {
        	  if(data="success"){
        		  $.dialog({type:2,text:"咨询师时间比例设置成功"});
          	 }else{
        		  $.dialog({type:2,text:"咨询师时间比例设置失败"});
          	 }
          }
      });
  }


</script>

</body>
</html>
