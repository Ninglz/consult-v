<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>咨询事件列表</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="consult">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">用户管理</a> > 咨询事件列表</p>
         <div class="main mt15 clearfix">
            <span class="fr f14">
               <input type="text" class="search_txt f14 ml10" placeholder="咨询师姓名" name="keyword" id="keyword" />
               <a href="javascript:void(0)" id="serachCondition" class="search_btn f14 color_ff">搜索</a>
            </span>
         </div>
         <table id="consult_table"  class="main mt15 pro_list">
              <thead>
         
                <th  class="whitecustumer" data-dynatable-column="consumerName">咨询者</th>
                <th  class="whitecustumer" data-dynatable-column="expertsName">咨询师</th>
                <th  class="whitecustumer" data-dynatable-column="launchTime">申请通话时间</th>
                <th  class="whitecustumer" data-dynatable-column="startTimes">视频开始时间</th>
                <th  class="whitecustumer" data-dynatable-column="endTimes">视频结束时间</th>
                <th  class="whitecustumer" data-dynatable-column="state">事件状态</th>
                <th  class="whitecustumer" data-dynatable-column="consumerIntegral">咨询者付费积分</th>
                <th  class="whitecustumer" data-dynatable-column="consumerSink">咨询者付费汇点</th>
                <th  class="whitecustumer" data-dynatable-column="expertsIntegral">咨询师付费积分</th>
                <th  class="whitecustumer" data-dynatable-column="expertsSink">咨询师付费汇点</th>
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


    var dynatable = $('#consult_table').dynatable({
        table: {
            bodyRowSelector: 'td'
        },
        dataset: {
            ajaxUrl: '/admin/consumer/consult/list'
        }
    }).data('dynatable');
    
    $("#serachCondition").click(function () {
    	$('#consult_table').find("tr:gt(0)").remove();
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
