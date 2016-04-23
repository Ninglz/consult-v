<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>模板详情</title>

  <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
  <link rel="stylesheet" href="/resources/admin/lib/jasny-bootstrap/css/jasny-bootstrap.min.css">
  <script src="/resources/admin/lib/jquery/jquery.min.js"></script>
  <script src="/resources/admin/lib/jasny-bootstrap/js/jasny-bootstrap.min.js"></script>
  <link rel="stylesheet" href="/resources/admin/lib/timepicker/css/bootstrap-responsive.css">
  <link rel="stylesheet" href="/resources/admin/lib/timepicker/css/bootstrap-timepicker.min.css">

</head>
<body>
<div class="bg-light lter" id="wrap">
  <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
  <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
    <jsp:param name="activeMenu" value="comment" />
  </jsp:include>
  <div id="content">
    <div class="outer">
      <div>
        <ul class="breadcrumb">
        <li>
            <a href="/admin/box/">药盒列表</a> <span class="divider">/</span>
          </li>
          <li>
            <a href="/admin/box/modules?mac=${mac }">模板列表</a> <span class="divider">/</span>
          </li>
          <li>
            <a href="#">模板详情</a>
          </li>
        </ul>
      </div>
      <div class="inner bg-light lter">
        <div class="row">
          <div class="col-lg-12">
            <div class="box">
              <header>
                <div class="icons">
                  <i class="fa fa-th"></i>
                </div>
                <h5>基本信息</h5>

                <!-- .toolbar -->
                <div class="toolbar">
                  <nav style="padding: 8px;">
                    <a class="btn btn-default btn-xs collapse-box" href="javascript:;">
                      <i class="fa fa-minus"></i>
                    </a>
                    <a class="btn btn-default btn-xs full-box" href="javascript:;">
                      <i class="fa fa-expand"></i>
                    </a>
                  </nav>
                </div>
                <!-- /.toolbar -->
              </header>
              <div class="body collapse in" id="div-5" aria-expanded="true" style="">
                <form:form commandName="questionCommandName" id="question_form" method="post"
                           action="" class="form-horizontal" enctype="multipart/form-data">
                           <input type="hidden" name="reportId" value="${modules.id}"/>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  <div id="open">
                    
                     <div class="form-group row">
                      <label class="control-label col-lg-2">模板名称：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.name}</label>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-lg-2">药品名称：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.medicineName}</label>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-lg-2">药量：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.quatity}</label>
                      </div>
                    </div>
                      <div class="form-group row">
                      <label class="control-label col-lg-2">服药安排：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.frequency}</label>
                      </div>
                    </div>
                       <div class="form-group row">
                      <label class="control-label col-lg-2">服药时间：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.quatity}</label>
                      </div>
                    </div>
                      <div class="form-group row">
                      <label class="control-label col-lg-2">服药周期：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.eattimeStr[0]},${modules.eattimeStr[1]}</label>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-lg-2">创建时间：</label>
                      <div class="col-lg-4">
                        <label class="control-label col-lg-2">${modules.created}</label>
                      </div>
                    </div>


                    <div class="form-group row">
                      <div class="col-lg-1 col-lg-offset-5">
                      
                        <a class="btn btn-primary btn-sm btn-line" href="javascript:history.back()"
                           data-original-title="返回" title="">返回</a>
                      </div>
                    </div>
                  </div>
                </form:form>
              </div>
            </div>
          </div>
        </div>

      </div>
      <!-- /.inner -->
    </div>
    <!-- /.outer -->
  </div>
  <!-- /#content -->

</div>
<%@ include file="/WEB-INF/admin/views/common/footer.jsp" %>
</body>
</html>
