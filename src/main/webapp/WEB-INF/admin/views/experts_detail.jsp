<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>用户列表</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         
       <p class="main mt20"><a href="">平台管理</a> > <a href="">用户管理</a> > 用户详情</p>
          <div class="main">
	       <form id="experts_form" method="post" action="/admin/consumer/auth" class="form-horizontal" enctype="multipart/form-data">
	       	<input type="hidden" name="id" value="${expertsVo.id}" />
			<input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
			<input type="hidden" name="flag" id="flag" value="" />
	            <p class="f14 color_33 mt30"><span class=""><img width="100" height="100" src="${expertsVo.avatar}" id="head_img"/></span> <span class="ml10">姓名：<i>${expertsVo.name}</i></span>
	            <span class="ml50">
	            <c:if test="${expertsVo.auditState == false && (expertsVo.handel == 1 || expertsVo.handel == 2) && expertsVo.actived == true}">
		               <a href="javascript:" class="work_btn ml10" onclick="submit(true)">审核通过</a>
	               	   <a href="javascript:" class="work_btn ml10 check_no">审核未通过</a>
	            </c:if>
		           <a href="javascript:" class="work_btn ml10 modify_int">修改积分</a>
	               <a href="javascript:" class="work_btn ml10 modify_customer_level">调整咨询者级别</a>
	            <c:if test="${expertsVo.auditState == true && (expertsVo.handel == 3 || expertsVo.handel == 2 || expertsVo.handel == -2) && expertsVo.actived == true }">
	               <a href="javascript:" class="work_btn ml10 modify_export_level">调整专家级别</a>
	            </c:if>
	            <c:if test="${expertsVo.auditState == true && (expertsVo.handel == 3 || expertsVo.handel == 2 || expertsVo.handel == -2)&& expertsVo.actived == true }">
	               <a href="javascript:" class="work_btn ml10 experts_top">专家置顶</a>
	            </c:if>
	            </span>
	            </p>
	            <p class="f14 color_33 mt15">手机号：<span class="">${expertsVo.cell}</span></p>
	            <p class="f14 color_33 mt15">咨询者级别：<span class="">${expertsVo.consumerLevels}</span></p>
	            <p class="f14 color_33 mt15">专家级别：<span class="">${expertsVo.expertsLevels}</span></p>
	            <p class="f14 color_33 mt15">专家状态：<span class="">${expertsVo.expertState}</span></p>
	            <p class="f14 color_33 mt15">专家所在城市：<span class="">${expertsVo.city}</span></p>
	            <p class="f14 color_33 mt15">专家行业：<span class="">${expertsVo.industry}</span></p>
	            <p class="f14 color_33 mt15">专家当前上下线状态：<span class="">
	            	<c:if test="${expertsVo.state == null}"></c:if>
	            	<c:if test="${expertsVo.state == 1}">上线</c:if>
					<c:if test="${expertsVo.state == 0}">下线</c:if>
	            </span></p>
	            <p class="f14 color_33 mt15">汇点余额：<span class="">${expertsVo.sink}</span></p>
	            <p class="f14 color_33 mt15">积分余额：<span class="">${expertsVo.integral}</span></p>
	            <p class="f14 color_33 mt15">专家有效咨询次数：<span class="">${expertsVo.expertsInvalidTime}</span></p>
	            <p class="f14 color_33 mt15">咨询者有效咨询次数：<span class="">${expertsVo.consumerValidTime}</span></p>
	            <p class="f14 color_33 mt15">累积充值金额：<span class="">${expertsVo.rechargePrice}</span></p>
	            <p class="f14 color_33 mt15">累积提现金额：<span class="">${expertsVo.cashPrice}</span></p>
	            <p class="f14 color_33 mt15">专家关注量：<span class="">${expertsVo.focusNo}</span></p>
	<%--             <p class="f14 color_33 mt15">专家评分平均分：<span class="">${expertsVo.eSocre}</span></p> --%>
	<%--             <p class="f14 color_33 mt15">咨询者评分平均分：<span class="">${expertsVo.cSocre}</span></p> --%>
	            <p class="f14 color_33 mt15">咨询者无效咨询次数：<i>${expertsVo.consumerInvalidTime}</i></p>
	            <p class="f14 color_33 mt15">注册时间：<span class="">${expertsVo.created}</span></p>
	            <p class="f14 color_33 mt15">上次APP登录时间：<span class="">${expertsVo.lastSigned}</span></p>
	            <p class="f14 color_33 mt15">身份证号：<i>${expertsVo.idCard}</i></p>
	            <p class="f14 color_33 mt15">擅长：<i>${expertsVo.goodAt}</i></p>
	            <p class="f14 color_33 mt15">资历证书：
	            	<img width="350" height="200" src="${expertsVo.certificateImg[0]}" id="credentials_img"/>
	            	<img width="350" height="200" src="${expertsVo.certificateImg[1]}" id="credentials_img1"/>
	            </p>
	            <p class="f14 color_33 mt15">详细说明：<span class="">${expertsVo.description}</span></p>
			</form>
         </div>
        <div class="footer text_center">&copy;copyright 2015 问堂有限公司  版权所有</div>
      </td>
   </tr>
</table>
<div style="display: none">

<a id="modify_int" href="#modify_int_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="modify_int_div">
      <p class="fany_title f14 color_ff">修改积分</p>
      <form action="/admin/consumer/updateIntegral" method="post" class="form-horizontal" enctype="multipart/form-data" id="integral_form">
	      <input type="hidden" name="id" value="${expertsVo.id}" />
	      <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
	      <table cellpadding="0" cellspacing="0" class="fany_table">
	         <tr>
	            <td></td>
	            <td class="text_center"><input type="text" name="integral" id="" class="fany_input" value="${expertsVo.integral}"/></td>
	         </tr>
	      </table>
	      <p class="text_center"><a href="javascript:" class="fany_btn color_ff enter_int"  onclick="submitIntegral()">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
      </form>
   </div>
    <a id="modify_export_refuse" href="#modify_export_refuse_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="modify_export_refuse_div">
      <p class="fany_title f14 color_ff">审核未通过</p>
      <form id="experts_no_form" method="post" action="/admin/consumer/auth" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
		<input type="hidden" name="flag" id="flag1" value="" />
		<input type="hidden" name="id" value="${expertsVo.id}" />
      <table cellpadding="0" cellspacing="0" class="fany_table">
         <tr>
            <td>审核未通过理由：</td>
            <td class="text_center"><input type="text" name="reason" id="" class="fany_input" /></td>
         </tr>
      </table>
      <p class="text_center"><a href="javascript:" class="fany_btn color_ff enter_refuse" onclick="submit1(false)">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
      </form>
   </div>
   
   <a id="modify_customer_level" href="#modify_customer_level_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="modify_customer_level_div">
      <p class="fany_title f14 color_ff">调整咨询者级别</p>
      <form action="/admin/consumer/consumerLevels" method="post" class="form-horizontal" enctype="multipart/form-data" id="consumerLevels_form">
      <input type="hidden" name="id" value="${expertsVo.id}" />
	  <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
      <table cellpadding="0" cellspacing="0" class="fany_table">
         <tr>
            <td></td>
            <td class="text_center"><input type="text" name="consumerLevels" id="" class="fany_input" value="${expertsVo.consumerLevels}"/></td>
         </tr>
      </table>
      <p class="text_center"><a href="javascript:" class="fany_btn color_ff enter_level_customer" onclick="submitConsumerLevels()">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
      </form>
   </div>
   <a id="modify_export_level" href="#modify_export_level_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="modify_export_level_div">
      <p class="fany_title f14 color_ff">调整专家级别</p>
      <form action="/admin/consumer/expertsLevels" method="post" class="form-horizontal" enctype="multipart/form-data" id="expertsLevels_form">
      <input type="hidden" name="id" value="${expertsVo.id}" />
	  <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
      <table cellpadding="0" cellspacing="0" class="fany_table">
         <tr>
            <td></td>
            <td class="text_center"><input type="text" name="expertsLevels" id="" class="fany_input" value="${expertsVo.expertsLevels}"/></td>
         </tr>
      </table>
      <p class="text_center"><a href="javascript:" class="fany_btn color_ff enter_level_export" onclick="submitExpertsLevels()">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
      </form>
   </div>
   <a id="experts_top" href="#experts_top_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="experts_top_div" style="width: 300px;">
      <p class="fany_title f14 color_ff">是否置顶?</p>
      <form action="/admin/consumer/top" method="post" class="form-horizontal" enctype="multipart/form-data" id="top_form">
      <input type="hidden" name="id" value="${expertsVo.id}" />
	  <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
	  <table cellpadding="0" cellspacing="0" class="fany_table">
         <tr>
            <td></td>
            <td class="text_center"></td>
         </tr>
      </table>
      <p class="text_center"><a href="javascript:" class="fany_btn color_ff" onclick="submitExpertsTop()">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
      </form>
   </div>
	<a id="show_big_img" href="#show_big_img_div" class="fancyboxBtn"></a>
   		<div class="fany_div" style="width: 550px;" id="show_big_img_div">
      		<img src="" width="500" />
      			<p class="text_center"><a href="javascript:" class="fany_no ml10">关闭</a></p>
   		</div>
	</div>
<link href="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script src="/resources/admin/js/exportDetail.js"></script>
<script src="/resources/admin/js/nav.js"></script>
<script>
   $(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
   });
   function submit(flag) {
		$("#flag").val(flag);
		$("#experts_form").submit();
	}
   function submit1(flag) {
		var int=$.trim($("input[name='reason']").val());
		if(int==""){
			$.dialog({type:2,text:"请输入审核未通过理由"});
			return false;
		}
		$("#flag1").val(flag);
		$("#experts_no_form").submit();
	}
   
   function submitIntegral(){
	   var int=$.trim($("input[name='integral']").val());
		if(int==""){
			$.dialog({type:2,text:"请输入积分"});
			return false;
		}
		var yReg = /^\d+$/;
		if(!yReg.test(int) || int<0){
			$.dialog({type:2,text:"请输入大于0的积分"});
			return false;
		}
		$("#integral_form").submit();
   }
   function submitConsumerLevels(){
	   var int=$.trim($("input[name='consumerLevels']").val());
		if(int==""){
			$.dialog({type:2,text:"请输入咨询者等级"});
			return false;
		}
		var yReg = /^\d+$/;
		if(!yReg.test(int) || int<0 || int>100){
			$.dialog({type:2,text:"请输入0~100的级别"});
			return false;
		}
		$("#consumerLevels_form").submit();
   }
   function submitExpertsLevels(){
	   var int=$.trim($("input[name='expertsLevels']").val());
		if(int==""){
			$.dialog({type:2,text:"请输入咨询师等级"});
			return false;
		}
		var yReg = /^\d+$/;
		if(!yReg.test(int) || int<1 || int>100){
			$.dialog({type:2,text:"请输入1~100的级别"});
			return false;
		}
		$("#expertsLevels_form").submit();
   }
   function submitExpertsTop(){
	   $("#top_form").submit();
   }
</script>

</body>
</html>