<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<link href="/resources/website/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/website/css/common.css" rel="stylesheet" type="text/css" />
  <div class="header">
    <div class="main clearfix">
       <a href="/" class="logo_a fl"><img src="/resources/website/img/logo.png" width="100%" /></a>
       <div class="fr header_right clearfix">
          <ul class="nav fl">
             <li><a href="/" class="current">首页</a></li>
             <li><a href="">专家介绍</a></li>
             <li><a href="/news">新闻</a></li>
             <li><a href="">活动专题</a></li>
             <li><a href="">关于我们</a></li>
          </ul>
          <div class="fl header_search">
            <input type="text" name="keyword" id="keyword" placeholder="搜索专家..." />
            <a href="javascript:"></a>
          </div>
          <!--未登录状态-->
          <c:if test="${user==null}">
          <span class="fl hearder_login">
            <a href="/" class="color_4fd f14">登录</a>
            <a href="register" class="ml10 f14">注册</a>
          </span>
          </c:if>
          <!--登陆后才出现-->
          <c:if test="${user!=null}">
          <span class="fl hearder_login f14">
          	<span class="user_nick"><img src="${user.avatar}" class="head_img" /></span>
            <a href="signout" class="ml10">退出</a>
          </span>
          </c:if>
          
          <div class="user_info">
            <c:if test="${user._handle==0 or user._handle==1 or user._handle==-1}">
             <p class="user_para1 clearfix"><span class="user_name color_ff f14">${user.nickName }</span>
             <c:if test="${user.sex==0}"><span class="user_sex"></c:if><c:if test="${user.sex==1}"><span class="user_sex"></c:if></span>
             <c:if test="${user._handle==0 or user._handle==-1}">
             	<a href="beExport" class="user_to_export fr">成为专家</a>
             </c:if>
             <p class="f14 color_ff">联系电话：${user.cell }</p>
             <c:if test="${user._handle!=0}">
             <p class="f14 color_ff">状态：<c:if test="${user._handle==1}">申请专家身份中</c:if><c:if test="${user._handle==-1}">申请专家失败</c:if></p>
             </c:if>
             </c:if>
             <c:if test="${user._handle==2 or user._handle==-2 or user._handle==3}">
             <p class="user_para1 clearfix"><span class="user_name color_ff f14">${user.name }</span><span class="user_sex"></span>
             <span class="fr color_ff"><img src="/resources/website/img/location_tip.jpg" class="mr5" />${province}&nbsp;&nbsp;${city}</span></p>
	            <p class="f14 color_ff">联系电话：${user.cell }</p>
	            <p class="f14 color_ff">行业：${industryName}</p>
	            <p class="f14 color_ff">擅长：${goodAt}</p>
	            </c:if>
          </div>
          <!--登陆后才出现-->
       </div>
    </div>
  </div>