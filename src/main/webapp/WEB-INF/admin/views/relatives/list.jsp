<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>亲友列表</title>
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<div class="" id="wrap">
  <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
  <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
    <jsp:param name="activeMenu" value="report" />
  </jsp:include>
  <div id="content">
    <div class="outer">
      <div>
        <ul class="breadcrumb">
          <li>
            <a href="/admin/box/">药盒列表</a><span class="divider">/</span>
          </li>
          <li>
            <a href="#">亲友列表</a>
          </li>
        </ul>
      </div>
      <div class="inner bg-light lter">
          <!-- 用户基本信息 -->
          <div class="dataTables_wrapper form-inline no-footer">
            <div class="row">
              <br/>
             <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>电话： <input id="phone" type="search" name="phone" class="form-control input-sm" aria-controls="dataTable">
                            </label>&nbsp;&nbsp;<a id="serachCondition" class="btn btn-info btn-sm btn-line">搜索</a>
                            </div>
                        </div>
            </div>
            <div class="row">
              <br/>
            </div>

            <table id="staff_table" class="table table-bordered table-striped">
              <thead>
                <th  class="whitecustumer" data-dynatable-column="relationship">亲友名称</th>
                <th  class="whitecustumer" data-dynatable-column=phoneNumber>亲友电话</th>
                <th  class="whitecustumer" data-dynatable-column="created">创建时间</th>
                <!-- <th  class="whitecustumer" data-dynatable-column="id">操作</th> -->
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>



      </div><!-- /.inner -->
    </div><!-- /.outer -->
  </div><!-- /#content -->

</div><!-- /#wrap -->
<%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>


<script>
  $(document).ready(function() {
    $("#logout_btn").click(function () {
      $("#logout_form").submit();
    });

    function customerRowWriter(rowIndex, record, columns, cellWriter) {
        var tr = '';
        var lockedFlag = "";
        var stickFlag="";
        var detailUrl="";
       for (var i = 0, len = columns.length; i < len; i++) {
    	   /*  if(columns[i].id == "id"){
            var operation = "";
            operation +=  '<a class="btn btn-info btn-xs btn-line" href="/admin/box/modules/detail?id='+ record.id +'">查看</a>' + '&nbsp;';
            record.id = operation;
          }*/
          tr += cellWriter(columns[i], record);
        } 

        return '<tr>' + tr + '</tr>';
      }

    var dynatable = $('#staff_table').dynatable({
      table: {
        bodyRowSelector: 'td'
      },
      dataset: {
        ajaxUrl: '/admin/box/relatives/list?mac=${mac}'
      },
      writers: {
        _rowWriter: customerRowWriter
      }
    }).data('dynatable');

    $("#serachCondition").click(function () {
      var phone = $("#phone").val();
      if (phone == "") {
        dynatable.queries.remove("phone");
      } else {
        dynatable.queries.add("phone",phone);
      }

      dynatable.process();
    });
  });


</script>

</body>
</html>
