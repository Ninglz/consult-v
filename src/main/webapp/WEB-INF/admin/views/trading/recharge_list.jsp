<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>充值列表</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="recharge">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">财务管理</a> > 充值列表</p>
         <div class="main mt15 clearfix">
         	<span class="fr f14">状态:
               <select class="select_m" id="state" name="state">
                  <option value="">全部</option>
                  <option value="0">充值成功</option>
                  <option value="1">充值失败</option>
               </select>
         
               <input type="text" class="search_txt f14 ml10" placeholder="请输入关键字" name="keyword" id="keyword" />
               <a href="javascript:void(0)" id="serachCondition" class="search_btn f14 color_ff">搜索</a>
            </span>
         </div>
         <table id="recharge_table"  class="main mt15 pro_list">
              <thead>
         
                <th  class="whitecustumer" data-dynatable-column="money">充值金额</th>
                <th  class="whitecustumer" data-dynatable-column="cell">手机号</th>
                <th  class="whitecustumer" data-dynatable-column="name">姓名</th>
                <th  class="whitecustumer" data-dynatable-column="created">充值时间</th>
                <th  class="whitecustumer" data-dynatable-column="state">状态</th>
              </thead>
              <tbody>
              </tbody>
            </table>
         
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


    var dynatable = $('#recharge_table').dynatable({
        table: {
            bodyRowSelector: 'td'
        },
        dataset: {
            ajaxUrl: '/admin/trading/recharge/list'
        }
    }).data('dynatable');
    
    $("#serachCondition").click(function () {
    	$('#recharge_table').find("tr:gt(0)").remove();
        var keyword = $("#keyword").val();
        if (keyword == "") {
          dynatable.queries.remove("keyword");
        } else {
          dynatable.queries.add("keyword",keyword);
        }
        var state = $("#state").val();
        if (state == "") {
          dynatable.queries.remove("state");
        } else {
          dynatable.queries.add("state",state);
        }
        
        dynatable.process();
      });
    
    
  });


</script>

</body>
</html>
