<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>行业列表</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<link href="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
<script src="/resources/admin/js/industry.js"></script>

<body>
<input type="hidden" id="activeMenu" value="industry">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 行业管理</p>
                 <div class="main mt15 clearfix">
            <a href="javascript:" class="work_btn f14 fl">添加行业</a>
            <span class="fr f14">
               <input type="text" class="search_txt f14 ml10" placeholder="请输入关键字" name="keyword" id="keyword"/>
               <a href="javascript:void(0)" id="serachCondition" class="search_btn f14 color_ff" >搜索</a>
            </span>
         </div>
         <table id="industry_table"  class="main mt15 pro_list">
              <thead>
                <th  class="whitecustumer" data-dynatable-column="name">行业名称</th>
                <th  class="whitecustumer" data-dynatable-column="consumerProportion">咨询者比例</th>
                <th  class="whitecustumer" data-dynatable-column="expertsProportion">咨询师比例</th>
                <th  class="whitecustumer" data-dynatable-column="id">操作</th>
              </thead>
              <tbody>
              </tbody>
            </table>
            
    <div style="display: none">
	   <a id="add_industry" href="#add_industry_div" class="fancyboxBtn"></a>
	   <div class="fany_div" id="add_industry_div">
	      <p class="fany_title f14 color_ff">设置行业</p>
	      <form action="/admin/industry/save" method="post">
		      <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		      <input type="hidden" name="id"  id="id"/>
		      <table cellpadding="0" cellspacing="0" class="fany_table">
		         <tr>
		            <td>行业名称</td>
		            <td><input type="text" name="name" id="name" class="fany_input" value="${industry.name}"/></td>
		         </tr>
		         <tr>
		            <td>咨询者比例</td>
		            <td><input type="text" name="consumerProportion" id="scale_customer" class="fany_input" maxlength="4" value="${industry.consumerProportion}"/></td>
		         </tr>
		         <tr>
		            <td>咨询师比例</td>
		            <td><input type="text" name="expertsProportion" id="scale_export" class="fany_input" maxlength="4" value="${industry.expertsProportion}"/></td>
		         </tr>
		      </table>
	      	  <p class="text_center"><a href="javascript:" class="fany_btn color_ff ml15">确定</a><a href="javascript:" class="fany_no ml10">取消</a></p>
	      </form>
   </div>
</div>
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
      </td>
   </tr>
</table>

<script>
$(document).ready(function() {
	  var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);

  $('#createdDate').datepicker({
    language: 'zh-cn',
    pickTime: false,
    todayBtn: true,
    autoClose: true,
    minView: '2',
    forceParse: false,
    format:"yyyy-mm-dd"
  });

  function expertsConsumerRowWriter(rowIndex, record, columns, cellWriter) {
  	 var tr = '';
       var activeFlag = "";
       for (var i = 0, len = columns.length; i < len; i++) {
           if(columns[i].id == "id"){
               var operation = "";
               operation += '&nbsp; <a href="javascript:" class="color_00a modify_industry" data-id="'+record.id+'" data-name="'+record.name+'" data-scale-customer="'+record.consumerProportion+'" data-scale-export="'+record.expertsProportion+'">编辑</a>';
               record.id = operation;
             }
           tr += cellWriter(columns[i], record);
       }
      return '<tr>' + tr + '</tr>';
  }

  var dynatable = $('#industry_table').dynatable({
      table: {
          bodyRowSelector: 'td'
      },
      dataset: {
          ajaxUrl: '/admin/industry/list'
      },
      writers: {
          _rowWriter: expertsConsumerRowWriter
      }
  }).data('dynatable');
  
  $("#serachCondition").click(function () {
	  $('#industry_table').find("tr:gt(0)").remove();
      var keyword = $("#keyword").val();
      if (keyword == "") {
        dynatable.queries.remove("keyword");
      } else {
        dynatable.queries.add("keyword",keyword);
      }
      
      dynatable.process();
    });
  
});

</script>

</body>
</html>
