<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>后台用户列表</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
    <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
        <jsp:param name="activeMenu" value="staff" />
    </jsp:include>
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">后台用户列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户基本信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-6">
                            <div id="dataTable_filter" class="dataTables_filter"><label>用户名： <input id="username"
                                                                                                    type="search"
                                                                                                    name="username"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div id="dataTable_filter" class="dataTables_filter"><label>用户状态： <select id="active"
                                                                                                      name="active"
                                                                                                      class="form-control input-sm"
                                                                                                      aria-controls="dataTable">
                                <option value="">-请选择-</option>
                                <option value="0">锁定</option>
                                <option value="1">正常</option>
                            </select>
                            </label> &nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                                <a id="addStaff" href="javascript:addStaff();" class="btn btn-info btn-sm btn-line">新增</a>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="staff_table" class="table table-bordered table-striped">
                        <thead>
                        <th class="whitecustumer" data-dynatable-column="username" data-dynatable-column>
                            用户名
                        </th>
                        <th class="whitecustumer" data-dynatable-column="isRoot">
                            级别
                        </th>
                        <th class="whitecustumer" data-dynatable-column="roles">角色</th>
                        <th class="whitecustumer" data-dynatable-column="active">用户状态</th>
                        <th class="whitecustumer" data-dynatable-column="id">操作</th>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="staffModal" tabindex="-1" role="dialog"
     aria-labelledby="staffModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    管理用户新增
                </h4>
            </div>
            <form class="form-horizontal" id="staffForm">
            <div class="modal-body">

                    <div class="form-group row">
                        <label class="control-label col-lg-3">用户名：</label>
                        <div class="col-lg-4">
                            <input id="staffName" name ="staffName" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row" >
                        <label class="control-label col-lg-3">角色：</label>

                        <div class="col-lg-4">
                            <select id="roles" name="roles"  data-placeholder="请选择角色" size="5" class="chosen-select chosen-select-width" tabindex="-1" multiple="">
                                <option value=""></option>
                                <c:forEach items="${roles}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row" id="passwordDiv">
                        <label class="control-label col-lg-3">密码：</label>

                        <div class="col-lg-4">
                            <input id="password" name ="password" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row" id="comfirmedDiv">
                        <label class="control-label col-lg-3">确认密码：</label>

                        <div class="col-lg-4">
                            <input id="comfirmed" name ="comfirmed" type="password" class="form-control">
                        </div>

                    </div>
                    <input hidden="true" id="id" name="id"/>
                    <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            </div>
            <div class="modal-footer">
            <button type="submit"  class="btn btn-default">
                提交
            </button>
                <button type="button" class="btn btn-default" id="cancelForm">取消
                </button>
            </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
    <!-- /.modal -->

</body>
</html>

<script>
    var dynatable;
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });



        $('#staffForm').bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                staffName: {
                    group: '.col-lg-4',
                    validators: {
                        notEmpty: {
                            message: '<fmt:message key="err.staff.info.username.null"/>'
                        },
                        stringLength: {
                            message: '<fmt:message key="err.staff.info.username.length"/>',
                            max: 20
                        }
                    }
                },
                password: {
                    group: '.col-lg-4',
                    validators: {
                        notEmpty: {
                            message: '<fmt:message key="err.staff.info.password.null"/>'
                        },
                        stringLength: {
                            message: '<fmt:message key="err.staff.info.password.length"/>',
                            max: 12
                        },identical: {
                            field: 'comfirmed',
                            message: '<fmt:message key="err.staff.info.confirmd"/>'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_-]+$/,
                            message: '<fmt:message key="err.staff.info.password.formate"/>'
                        }
                    }
                },
                comfirmed: {
                    group: '.col-lg-4',
                    validators: {
                        identical: {
                            field: 'password',
                            message: '<fmt:message key="err.staff.info.confirmd"/>'
                        }
                    }
                },
                roles: {
                    group: '.col-lg-4',
                    validators: {
                        notEmpty: {
                            message: '<fmt:message key="err.staff.info.role"/>'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
            // Prevent form submission


            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);
            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');
            staffAddOperation()
            // Use Ajax to submit form data
//            $.post($form.attr('action'), $form.serialize(), function(result) {
//                console.log(result);
//            }, 'json');
        });

        $('#cancelForm').click(function() {
            $('#staffForm').data('bootstrapValidator').resetForm(true);
            $('#staffModal').modal('hide');
        });


        function staffRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            var isRootFlag = null;
            for (var i = 0, len = columns.length; i < len; i++) {
                if(columns[i].id == "id"){
                    var operation = "";
                    if(record.isRoot == '一般管理员'){
                        if(record.active == "<fmt:message key="info.customer.active.normal"/>"){
                            operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:lockStaff('+ record.id +')">冻结</a>' + '&nbsp;';
                        }else{
                            operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:unLockStaff('+ record.id +')">解冻</a>' + '&nbsp;';
                        }
                        operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:editStaff('+ record.id + ')">编辑</a>&nbsp;';
                        operation += '&nbsp;' + '<a class="btn btn-info btn-xs btn-line" href="javascript:staffDel('+ record.id + ')">删除</a>&nbsp;';
                    }

                    record.id = operation;
                }
                if(columns[i].id == "isRoot"){
                    isRootFlag = record.isRoot;
                    record.isRoot = (isRootFlag == "true" ? "超级管理员" : "一般管理员");
                }
                if(columns[i].id == "active"){
                    activeFlag = record.active;
                    record.active = (record.active == "true") ? "<fmt:message key="info.customer.active.normal"/>":"<fmt:message key="info.customer.active.lock"/>";
                }
                if(columns[i].id == "roles"){
                    if(record.roles == null){
                        record.roles = "";
                    }
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        dynatable = $('#staff_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/staff/list'
            },
            writers: {
                _rowWriter: staffRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var username = $("#username").val();
            if (username == "") {
                dynatable.queries.remove("username");
            } else {
                dynatable.queries.add("username",username);
            }
            var locked = $("#active").val();
            if (locked == "") {
                dynatable.queries.remove("active");
            } else {
                dynatable.queries.add("active",locked);
            }
            dynatable.process();
        });

        $("#active").chosen({
           no_results_text: '未找到!',
           disable_search:true
        });
        $("#roles").chosen({
            no_results_text: '未找到!',
            disable_search:true,
            width: "100%"
        });

    });

    function lockStaff(id){
        var d1 = new $.Jq_alert();
        $("body").showLoading();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $.ajax({
            type: "POST",
            url: "/admin/staff/status",
            data: "staffId=" + id + "&status=" + false + "&" + token,
            success: function (data) {
                if(data.c != 0){
                    $("body").hideLoading();
                    if(data.c == 10004){
                        d1.init({
                            msg:"<fmt:message key="err.staff.no.exist"/>"
                        });
                    }
                    if(data.c == 10001){
                        d1.init({
                            msg:"<fmt:message key="err.staff.unlocked.failed"/>"
                        });
                    }
                }else{
                    dynatable.process();
                    $("body").hideLoading();
                }
            }
        });
    }

    function unLockStaff(id){
        var d1 = new $.Jq_alert();
        $("body").showLoading();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $.ajax({
            type: "POST",
            url: "/admin/staff/status",
            data: "staffId=" + id + "&status=" + true + "&" + token,
            success: function (data) {
                if(data.c != 0){
                    $("body").hideLoading();
                    if(data.c == 10004){
                        d1.init({
                            msg:"<fmt:message key="err.staff.no.exist"/>"
                        });
                    }
                    if(data.c == 10001){
                        d1.init({
                            msg:"<fmt:message key="err.staff.locked.failed"/>"
                        });
                    }
                }else{
                    dynatable.process();
                    $("body").hideLoading();
                }
            }
        });
    }

    function addStaff() {

        $("#myModalLabel").text("管理用户新增");
        $("#staffName").val("");
        $("#password").val("");
        $("#comfirmed").val("");
        $("#comfirmedDiv").show();
        $("#passwordDiv").show();
        $("#id").val("");
        chose_mult_set_ini()  ;
        $('#staffModal').modal('show');
    }

    function editStaff(id) {
        $.ajax({
            type: "GET",
            url: "/admin/staff/detail",
            data: "id=" + id,
            success: function (data) {
                $("#myModalLabel").text("管理用户编辑");
                $("#comfirmedDiv").hide();
                $("#passwordDiv").hide();
                $("#staffName").val(data.m.username);
                $("#id").val(data.m.id);
                chose_mult_set_ini_staff_role(data.m.roles);
                $('#staffModal').modal('show');
                $("body").hideLoading();
            }
        });

    }
//
    function staffAddOperation(){
        var d1 = new $.Jq_alert();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        var username = $("#staffName").val();
        var password = $("#password").val();
        var comfirmed = $("#comfirmed").val();
        var roles = $("#roles").val();
        alert(roles);
        var id = $("#id").val();
        $("body").showLoading();
        $.ajax({
            type: "POST",
            url: "/admin/staff/add",
            data: "id=" + id
                    + "&username=" + username
                    + "&password=" + password
                    + "&comfirmed=" + comfirmed
                    + "&roles=" + roles
                    + "&" + token,
            success: function (data) {
                if(data.c != 0){
                    $('#staffModal').modal('hide');
                    $("body").hideLoading();
                    d1.init({
                        msg:"<fmt:message key="err.staff.add.failed"/>"
                    });
                }else{
                    dynatable.process();
                    $('#staffModal').modal('hide');
                    $("body").hideLoading();
                }

            }
        });
    }

    function staffDel(id){
        var d1 = new $.Jq_alert();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $("body").showLoading();
        $.ajax({
            type: "POST",
            url: "/admin/staff/del",
            data: "staffId=" + id
                    + "&" + token,
            success: function (data) {
                if(data.c != 0){
                    $("body").hideLoading();
                    if(data.c == 10004){
                        d1.init({
                            msg:"<fmt:message key="err.staff.no.exist"/>"
                        });
                    }
                    if(data.c == 10001){
                        d1.init({
                            msg:"<fmt:message key="err.staff.del.failed"/>"
                        });
                    }
                }else{
                    dynatable.process();
                    $("body").hideLoading();
                }
            }
        });


    }

    function chose_mult_set_ini() {
        $("#roles option").removeAttr('selected');
        $("#roles").trigger("chosen:updated");
    }

    function chose_mult_set_ini_staff_role(values) {
        $("#roles option").removeAttr('selected');
        var arr = values.split(',');
        var length = arr.length;
        for (var i = 0; i < length; i++) {
            $("#roles option[value='" + arr[i] + "']").attr('selected', 'selected');
        }
        $("#roles").trigger("chosen:updated");
    }


</script>
