<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>用户列表</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/js/common/css/jquery.wait_Mask.css" rel="stylesheet" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>

<body>
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
         <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
    <jsp:param name="activeMenu" value="consumer" />
  </jsp:include> 
      </td>
      <td class="right" valign="top">
      <img alt="" src="">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 专家管理</p>
           <div class="main mt15 clearfix">
             <a href="javascript:" class="work_btn f14 fl">推送</a>
               <span class="fr f14">
          	咨询师状态:
               <select class="select_m" id="expertState">
                  <option value="">全部</option>
                  <option value="0">空</option>
                  <option value="1">身份申请中</option>
                  <option value="-1">身份拒绝</option>
                  <option value="2">资料变更申请中</option>
                  <option value="-2">资料变更拒绝</option>
               </select>&nbsp;&nbsp;
            账号状态:
               <select class="select_m" id="active">
                  <option value="">全部</option>
                  <option value="true">帐号正常</option>
                  <option value="false">帐号封停</option>
               </select>
               <input type="text" class="search_txt f14 ml10" placeholder="账号/真实姓名/昵称" name="keyword" id="keyword" />
               <a href="javascript:void(0)" id="serachCondition" class="search_btn f14 color_ff">搜索</a>
            </span>
         </div>
         <table id="expertsConsumer_table"  class="main mt15 pro_list">
              <thead>
         
                <!-- <th  class="whitecustumer" data-dynatable-column="id">圈子id</th> -->
                <th  class="whitecustumer" data-dynatable-column="userName">账号</th>
                <th  class="whitecustumer" data-dynatable-column="nickName">昵称</th>
                <th  class="whitecustumer" data-dynatable-column="name">真实姓名</th>
                <th  class="whitecustumer" data-dynatable-column="consumerLevels">咨询者级别</th>
                <th  class="whitecustumer" data-dynatable-column="expertsLevels">咨询师级别</th>
                <th  class="whitecustumer" data-dynatable-column="expertState">咨询师状态</th>
                <th  class="whitecustumer" data-dynatable-column="state">上下线</th>
                <th  class="whitecustumer" data-dynatable-column="created">注册时间</th>
                <th  class="whitecustumer" data-dynatable-column="lastSigned">最后登录时间</th>
                <th  class="whitecustumer" data-dynatable-column="sink">汇点</th>
                <th  class="whitecustumer" data-dynatable-column="integral">积分</th>
                <th  class="whitecustumer" data-dynatable-column="active">状态</th>
                <th  class="whitecustumer" data-dynatable-column="id">操作</th>
              </thead>
              <tbody>
              </tbody>
            </table>
            
         
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
      </td>
   </tr>
</table>
<div style="display: none">
   <a id="send_msg" href="#send_msg_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="send_msg_div">
      <p class="fany_title f14 color_ff">推送</p>
      <table cellpadding="0" cellspacing="0" class="fany_table">
         <tr>
            <td>推送内容：</td>
            <td class="text_center"><textarea name="content" id="content" class="fany_input" style="height: 60px; resize: none;" ></textarea></td>
         </tr>
      </table>
      <p class="text_center"><a href="javascript:" class="fany_btn color_ff enter_refuse">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
   </div>
</div>
<link href="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script src="/resources/admin/js/index.js"></script>
<script src="/resources/admin/js/nav.js"></script>

<script>
var dynatable;
  $(document).ready(function() {
	  var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
    $("#logout_btn").click(function () {
      $("#logout_form").submit();
    });

    $('#createdDate').datepicker({
      language: 'zh-cn',
      pickTime: false,
      todayBtn: true,
      autoClose: true,
      minView: '2',
      forceParse: false,
      format:"yyyy-mm-dd"
    });

    function expertsCustomerRowWriter(rowIndex, record, columns, cellWriter) {
    	 var tr = '';
         var activeFlag = "";
         for (var i = 0, len = columns.length; i < len; i++) {
             if(columns[i].id == "active"){
                 activeFlag = record.active;
                 record.active = (record.active == "true") ? "<fmt:message key="info.customer.active.normal"/>":"<fmt:message key="info.customer.active.lock"/>";
             }
             if(columns[i].id == "sex"){
            	 if(record.sex == "null"){
            		 record.sex = "";
            	 }else{
	                 sexFlag = record.sex;
	                 record.sex = (record.sex == "true") ? "男":"女";
            	 }
             }
             if(columns[i].id == "name"){
                 if(record.name == "null"){
                     record.name = "";
                 }
             }
             if(columns[i].id == "state"){
                 if(record.state == "null"){
                     record.state = "";
                 }else if(record.state == 1){
                     record.state = "上线";
                 }else if(record.state == 0){
                     record.state = "下线";
                 }
             }
             if(columns[i].id == "id"){
                 var operation = "";
                 operation += '&nbsp; <a class="btn btn-success btn-xs btn-line" href="/admin/consumer/detail?id='+ record.id +'">详情 || </a>';
                 if(record.active == "<fmt:message key="info.customer.active.normal"/>"){
                     operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:lockConsumer('+ record.id +')">禁用</a>' + '&nbsp;';
                 }else{
                     operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:unLockConsumer('+ record.id +')">解禁</a>' + '&nbsp;';
                 }
                 record.id = operation;
               }

             tr += cellWriter(columns[i], record);
         }
         return '<tr>' + tr + '</tr>';
    }

    dynatable = $('#expertsConsumer_table').dynatable({
        table: {
            bodyRowSelector: 'td'
        },
        dataset: {
            ajaxUrl: '/admin/consumer/list'
        },
        writers: {
            _rowWriter: expertsCustomerRowWriter
        }
    }).data('dynatable');
    
    $("#serachCondition").click(function () {
    	$('#expertsConsumer_table').find("tr:gt(0)").remove();
        var keyword = $("#keyword").val();
        if (keyword == "") {
          dynatable.queries.remove("keyword");
        } else {
          dynatable.queries.add("keyword",keyword);
        }
        var active = $("#active").val();
        if (active == "") {
          dynatable.queries.remove("active");
        } else {
          dynatable.queries.add("active",active);
        }
        
        var expertState = $("#expertState").val();
        if (expertState == "") {
          dynatable.queries.remove("expertState");
        } else {
          dynatable.queries.add("expertState",expertState);
        }
        
        dynatable.process();
      });
    
    
  });

  function lockConsumer(id){
//       $("body").showLoading();
      var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
      $.ajax({
          type: "POST",
          url: "/admin/consumer/status",
          data: "customerId=" + id + "&status=" + false + "&" + token,
          success: function (data) {
        	  //$('#expertsConsumer_table').find("tr:gt(0)").remove();
              	dynatable.process();
              //$("body").hideLoading();
          }
      });
  }

  function unLockConsumer(id){
//       $("body").showLoading();
      var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
      $.ajax({
          type: "POST",
          url: "/admin/consumer/status",
          data: "customerId=" + id + "&status=" + true + "&" + token,
          success: function (data) {
        	  $('#expertsConsumer_table').find("tr:gt(0)").remove();
              dynatable.process();
              //$("body").hideLoading();
          }
      });
  }

</script>

</body>
</html>
