<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>充值列表</title>
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/fancybox/jquery.fancybox-1.3.4.js" type="text/javascript"></script>
<script src="/resources/admin/js/withdrawal.js"></script>
</head>
<body>
<input type="hidden" id="activeMenu" value="withdraw">
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">财务管理</a> > 提现列表</p>
         <div class="main mt15 clearfix">
         	<span class="fr f14">状态:
               <select class="select_m" id="status" name="status">
                  <option value="">全部</option>
                  <option value="0">申请中</option>
                  <option value="1">处理中</option>
                  <option value="2">提现完成</option>
                  <option value="3">提现拒绝</option>
               </select>
         
               <input type="text" class="search_txt f14 ml10" placeholder="请输入关键字" name="keyword" id="keyword" />
               <a href="javascript:void(0)" id="serachCondition" class="search_btn f14 color_ff">搜索</a>
            </span>
         </div>
         <table id="withdraw_table"  class="main mt15 pro_list">
              <thead>
                <th  class="whitecustumer" data-dynatable-column="money">提现金额</th>
                <th  class="whitecustumer" data-dynatable-column="cell">手机号</th>
                <th  class="whitecustumer" data-dynatable-column="name">姓名</th>
                <th  class="whitecustumer" data-dynatable-column="alipayUserName">支付宝账号</th>
                <th  class="whitecustumer" data-dynatable-column="created">申请时间</th>
                <th  class="whitecustumer" data-dynatable-column="status">状态</th>
                <th  class="whitecustumer" data-dynatable-column="id">操作</th>
              </thead>
              <tbody>
              </tbody>
            </table>
<div style="display: none">
   <a id="pay_for" href="#pay_for_div" class="fancyboxBtn"></a>
   <div class="fany_div" id="pay_for_div">
      <p class="fany_title f14 color_ff">打款须知:</p>
      <form action="/admin/trading/payfor" method="post" id="payForm">
      <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <input type="hidden" name="id"  id="id"/>
      <table cellpadding="0" cellspacing="0" class="fany_table">
         <tr>
            <td>系统只能往本人的支付宝帐号提现，不同姓名的不能提现!</td>
         </tr>
      </table>
      <p class="text_center"><a href="javascript:" class="fany_btn color_ff" onclick="submitPay()">确定</a></p>
      </form>
   </div>
 </div>
            
         
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
      </td>
   </tr>
</table>


<script>
var dynatable;
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

    
    function expertsCustomerRowWriter(rowIndex, record, columns, cellWriter) {
   	 var tr = '';
        var activeFlag = "";
        for (var i = 0, len = columns.length; i < len; i++) {
            if(columns[i].id == "id"){
                var operation = "";
                if(record.status == "申请中"){
                    operation +=  '<a class="color_00a" href="/admin/trading/deal?id='+ record.id +'">处理</a>' + '&nbsp;';
                    operation +=  '<a href="/admin/trading/refuse?id='+ record.id +'" class="color_ee3">拒绝</a>' + '&nbsp;';
                }else if(record.status == "处理中"){
                    operation +=  '<a class="color_00a pay_for" href="javascript:" data-id="'+record.id+'">打款</a>' + '&nbsp;';
                }else{
                	operation += "";
                }
                record.id = operation;
              }

            tr += cellWriter(columns[i], record);
        }
        return '<tr>' + tr + '</tr>';
   }
    dynatable = $('#withdraw_table').dynatable({
        table: {
            bodyRowSelector: 'td'
        },
        dataset: {
            ajaxUrl: '/admin/trading/withdraw/list'
        },
        writers: {
            _rowWriter: expertsCustomerRowWriter
        }
    }).data('dynatable');
    
    $("#serachCondition").click(function () {
    	$('#withdraw_table').find("tr:gt(0)").remove();
        var keyword = $("#keyword").val();
        if (keyword == "") {
          dynatable.queries.remove("keyword");
        } else {
          dynatable.queries.add("keyword",keyword);
        }
        var status = $("#status").val();
        if (status == "") {
          dynatable.queries.remove("status");
        } else {
          dynatable.queries.add("status",status);
        }
        
        dynatable.process();
      });
  });
  
  function submitPay(){
	  $("#payForm").submit();
  }


</script>

</body>
</html>
