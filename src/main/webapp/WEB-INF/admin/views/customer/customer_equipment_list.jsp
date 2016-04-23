<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
    <%@ include file="/WEB-INF/admin/views/common/left.jsp" %>
    <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">用户设备列表</a>
                    </li>
                </ul>
                <a class="btn btn-primary btn-line upload_a" href="javascript:exportCustomer()">导出</a>
                <form id="export_form" target="_blank" action="/admin/customer/equipment/export" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" id="_page" name="page" value=""/>
                    <input type="hidden" id="_size" name="size" value=""/>
                    <input type="hidden" id="_offset" name="offset" value=""/>
                </form>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户基本信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>手机号： <input id="cellNo"
                                                                                                    type="search"
                                                                                                    name="cellNo"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>&nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                            </div>
                        </div>


                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="customer_table" class="table table-bordered table-striped">
                        <thead>
                        <th  class="whitecustumer" data-dynatable-column="cellNo">
                            手机号
                        </th>
                        <th  class="whitecustumer" data-dynatable-column="recordUserd">
                            历史数据量
                        </th>
                        <th  class="whitecustumer" data-dynatable-column="lastUsed">
                            最近使用日期
                        </th>
                        <th  class="whitecustumer" data-dynatable-column="equipments">
                            常用设备
                        </th>

                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.inner -->
        </div>
        <!-- /.outer -->
    </div>
    <!-- /#content -->

</div>
<!-- /#wrap -->
<%@ include file="/WEB-INF/admin/views/common/footer.jsp" %>

</body>
</html>

<script>
    var dynatable;
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        function customerRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            var activeFlag = "";
            for (var i = 0, len = columns.length; i < len; i++) {

                if(columns[i].id == "cellNo"){
                    record.cellNo = '<a href="/admin/customer/detail/' + record.id + '">' + record.cellNo + '</a><br/>';
                }

                if(columns[i].id == "lastUsed"){
                    if(record.lastUsed == null || record.lastUsed == ""){
                        record.lastUsed = "暂无使用记录";
                    }
                }

                if(columns[i].id == "recordUserd"){
                    //todo
                    var arr = [];
                    arr.push('<a href="/admin/equipment/user_record?active=true&consumerId='+ record.id +'">');
                    arr.push(record.recordUserd + '</a>');
                    record.recordUserd = arr.join('');
                }

                if(columns[i].id == "equipments"){
                    var equipments = record.equipments;
                    var opt = '';
                    if(equipments.length > 0){
                        for (var j = 0; j < equipments.length; j++) {
                            for(var oj in equipments[j]){
                                opt += '<a href="/admin/equipment/'+ oj + '/users_statistics">' + equipments[j][oj] + '</a><br/>';
                            }
                        }
                    }else{
                        opt = "暂无使用记录";
                    }

                    record.equipments =  opt;
                }

                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        dynatable = $('#customer_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/customer/equipment/list',
                sorts:{
                    lastSigned : 1
                }
            },
            writers: {
                _rowWriter: customerRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var cellNo = $("#cellNo").val();
            if (cellNo == "") {
                dynatable.queries.remove("cellNo");
            } else {
                dynatable.queries.add("cellNo",cellNo);
            }
            dynatable.process();
        });



    });

    function exportCustomer(){
        $("#_page").attr("value", dynatable.settings.dataset.page-1);
        $("#_size").attr("value", dynatable.settings.dataset.perPage);

//        $("#_offset").attr("value", dynatable.settings.dataset.page * dynatable.settings.dataset.perPageDefault -1);
        $("#export_form").submit();
    }



</script>
