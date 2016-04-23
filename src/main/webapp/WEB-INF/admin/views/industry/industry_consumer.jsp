<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>行业设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="industryConsumer">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 系统设置 > 行业浮动比例设置</p>
         <div class="main">
            <form action="" method=""  id="industry_form">
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table">
               <c:forEach var = "obj" items ="${list }" varStatus ="status" >
               <c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">    
      			<tr>    
     		   </c:if>    
 				 <td width="100" class="text_right">${obj.name}：</td>
                 <td><input type="text" name="consumerProportion" id="" class="fany_input w100 ml10"  value="${obj.consumerProportion}"/></td>
 			  	<c:if test="${status.count % 5 eq 0 || status.count eq 5}">    
      			</tr>    
      			</c:if> 
               </c:forEach>
               <tr>
                     <td width="150" class="text_right"></td>
                     <td><a href="javascript:void(0)" class="fany_btn f14 color_ff ml10">设置</a></td>
                  </tr>
                </table>
              </form>
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
      var inputs=$(".form_table").find("input");
      var len=$(inputs).length;
      var yReg = /^\d+$/;
      var zReg = /^\d+(\.\d{0,2})?$/;
      for(var i=0;i<len;i++){
         if($.trim($(inputs[i]).val())==""){
            $.dialog({type:2,text:"请填写所有行业的比例设置"});
            return false;
         }
         if(!zReg.test($.trim($(inputs[i]).val()))){
            $.dialog({type:2,text:"输入的比例格式不正确"});
            return false;
         }
      }
      industrySubmit();
   });
});
  
  function industrySubmit(){
	  var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
	  var params = $("#industry_form").serialize()+ "&" + token;
      $.ajax({
          type: "POST",
          url: "/admin/industry/save/consumer",
          data: params,
          success: function (data) {
        	  if(data="success"){
         		 alert("设置成功!");
         	 }else{
         		 alert("设置失败，请联系管理员！");
         	 }
          }
      });
  }


</script>

</body>
</html>
