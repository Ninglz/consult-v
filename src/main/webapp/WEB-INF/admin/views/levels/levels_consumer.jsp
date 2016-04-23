<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>级别设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="levelsConsumer">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 系统设置 > 级别浮动比例设置</p>
         <div class="main">
            <form action="" method=""  id="leves_form">
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table">
               <c:forEach var = "obj" items ="${list }" varStatus ="status" >
               <c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">    
      			<tr>    
     		   </c:if>    
 				 <td width="100" class="text_right">级别${obj.levels}：</td>
                 <td><input type="text" name="levels" id="" class="fany_input w100 ml10" maxlength="4" value="${obj.consumerProportion}"/></td>
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
            $.dialog({type:2,text:"请填写所有级别的比例设置"});
            return false;
         }
         if(!zReg.test($.trim($(inputs[i]).val()))){
            $.dialog({type:2,text:"输入的比例格式不正确"});
            return false;
         }
      }
      levelsSubmit();
   });
});
  
  function levelsSubmit(){
	  var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
	  var params = $("#leves_form").serialize()+ "&" + token;
      $.ajax({
          type: "POST",
          url: "/admin/levels/save/consumer",
          data: params,
          success: function (data) {
        	  if(data="success"){
        		  $.dialog({type:2,text:"咨询者等级比例设置成功"});
         	 }else{
         		$.dialog({type:2,text:"咨询者等级比例设置失败"});
         	 }
          }
      });
  }


</script>

</body>
</html>
