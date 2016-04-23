<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>咨询汇</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" name="viewport">
</head>

<body>
<div class="wapper">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
   <!--头部-->
	<%@ include file="/WEB-INF/website/views/common/header.jsp"%>
	<link href="/resources/website/css/login.css" rel="stylesheet" type="text/css" />
  <!--主要内容区-->
  <div class="login be_export">
    <p class="title">成为专家</p>
    <form action="/experts/save" method="post" class="form-horizontal" enctype="multipart/form-data">
    <input hidden="true" id="id" name="id" value="${user.id}"/>
    <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <div class="be_export_fl">
        <P class="mt40 f14">头像<span class="color_4fd ml10">*</span></P>
        <div class="mt10 position_re">
          <span class="head_img_add" id="head_img_add"><img src="${user.avatar }" /></span>
          <input type="file" class="head_img_file" name="headimg" id="headimg"  />
           <input hidden="true" id="head_img_file_hidden" value="${user.avatar}"/>
        </div>
        <P class="mt30 f14">真实姓名<span class="color_4fd ml10">*</span></P>
        <P class="mt10"><input type="text" class="login_input" name="realname" id="realname"  value="${user.name}"/></P>
        <P class="mt30 f14">性别</P>
        <P class="mt10">
        <c:if test="${user.sex==0 }">
         <a href="javascript:" data-id="0" class="radio_m checked"><i></i></a>男<a href="javascript:" data-id="1" class="radio_m ml10"><i></i></a>女
         <a href="javascript:" data-id="2" class="radio_m ml10"><i></i></a>保密
        </c:if>
        <c:if test="${user.sex==1 }">
         <a href="javascript:" data-id="0" class="radio_m "><i></i></a>男<a href="javascript:" data-id="1" class="radio_m ml10 checked" ><i></i></a>女
        <a href="javascript:" data-id="2" class="radio_m ml10"><i></i></a>保密
        </c:if>
        <c:if test="${user.sex==2 }">
         <a href="javascript:" data-id="0" class="radio_m "><i></i></a>男<a href="javascript:" data-id="1" class="radio_m ml10 " ><i></i></a>女
        <a href="javascript:" data-id="2" class="radio_m ml10 checked"><i></i></a>保密
        </c:if>
        <input type="hidden" name="sex" id="sex" value="${user.sex}" />
       </P>
        <P class="mt30 f14">联系电话<span class="color_4fd ml10">*</span></P>
        <P class="mt10"><input type="text" class="login_input" name="phone" id="phone"  value="${user.cell }" readonly="readonly"/></P>
        <P class="mt30 f14">身份证号<span class="color_4fd ml10">*</span></P>
        <P class="mt10"><input type="text" class="login_input" name="IDcard" id="IDcard"  value="${user.idCard }"/></P>
        <P class="mt30 f14">所在地<span class="color_4fd ml10">*</span></P>
        <div class="mt10">
          <div class="select_mode w48p">
            <span class="select_down"></span>
            <select name="province" id="province"><option>北京</option></select>
          </div>
          <div class="select_mode w48p">
            <span class="select_down"></span>
            <select name="city" id="city"><option>北京</option></select>
          </div>
        </div>
      </div>
       <div class="be_export_fr">
        <P class="mt40 f14">资历<span class="color_4fd ml10">*</span></P>
        <div class="mt10">
          <div class="select_mode w46p">
            <span class="select_down"></span>
            <select name="seniorityStart" id="seniorityStart"></select>
          </div>
          至
          <div class="select_mode w46p">
            <span class="select_down"></span>
            <select name="seniorityEnd" id="seniorityEnd"></select>
          </div>
        </div>
        <P class="mt30 f14">行业<span class="color_4fd ml10">*</span></P>
        <div class="mt10">
          <div class="select_mode">
            <span class="select_down"></span>
            <select name="industry" id="industry"><option>行业1</option></select>
          </div>
        </div>
       

        
        <P class="mt30 f14">擅长<span class="color_4fd ml10">*</span></P>
        <P class="mt10"><input type="text" class="login_input" name="professional" id="professional"  value="${user.goodAt}"/></P>
        <P class="mt30 f14">资历证书</P>
        <div class="mt10">
          <div class="certificate_upload" id="certificate_upload1">
            <img src="<c:if test="${user.certificateImg[0]==null}">/resources/website/img/certificate_img.jpg</c:if>
            <c:if test="${user.certificateImg[0]!=null}">${user.certificateImg[0]}</c:if>" />
            <input type="file" class="certificate_img_file" name="certificate_img" id="certificate_img1" />
            <input hidden="true" id="certificate_img_file_hidden1" value="${user.certificateImg[0]}"/>
          </div>
          <div class="certificate_upload ml10" id="certificate_upload2">
             <img src="<c:if test="${user.certificateImg[1]==null}">/resources/website/img/certificate_img.jpg</c:if>
            <c:if test="${user.certificateImg[1]!=null}">${user.certificateImg[1]}</c:if>" />
            <input type="file" class="certificate_img_file" name="certificate_img" id="certificate_img2" />
            <input hidden="true" id="certificate_img_file_hidden2" value="${user.certificateImg[1]}"/>
          </div>
        </div>
        <P class="mt30 f14">个人说明(未上传证书必填)</P>
        <P class="mt10"><textarea type="text" class="login_input h120" name="introduce" id="introduce">${user.description }</textarea></P>
      </div>
      <div class="clear"></div>
      <P class="mt30 text_center">
        <a href="javascript:" class="sub_apply_btn f16 color_ff">提交申请</a>
      </P>
    </form>
  </div>
  <!--底部-->
   <%@ include file="/WEB-INF/website/views/common/footer.jsp"%>
<input hidden="false" id="city_id"  value="${user.cityId}"/>
<input hidden="false" id="province_id"  value="${user.provinceId}"/>
<input hidden="false" id="industry_id"  value="${user.industryId}"/>
<input hidden="false" id="seniority_start"  value="${user.seniorityStart}"/>
<input hidden="false" id="seniority_end"  value="${user.seniorityEnd}"/>
<script src="/resources/website/js/login.js"></script>
<script src="/resources/admin/js/common/js/uploadPreview.js"></script>
<script src="/resources/website/js/beExport.js"></script>
</body>
</html>
