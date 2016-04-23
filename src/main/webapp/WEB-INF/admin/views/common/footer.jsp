<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="footer text_center">&copy;copyright 2015 问堂有限公司  版权所有</div>

<!--jquery dynatable-->
<script src="/resources/admin/lib/jquery-dynatable/jquery.dynatable.js"></script>

<!--time select-->

<script src="/resources/admin/lib/datepicker/js/bootstrap-datepicker.js"></script>

<script src="/resources/admin/js/common/js/jquery.longyuJs.js"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script src="/resources/admin/js/nav.js"></script>





<script>
    $(document).ready(function () {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });
        //dynatable init config
        $.dynatableSetup({
            features: {
                paginate: true,
                sort: false,
                pushState: false,
                search: false,
                recordCount: true,
                perPageSelect: true
            },
            table: {
                defaultColumnIdStyle: 'camelCase',
                columns: null,
                headRowSelector: 'thead tr', // or e.g. tr:first-child
                bodyRowSelector: 'tbody tr',
                headRowClass: 'whitecustumer',
                copyHeaderClass:false
            },
            inputs: {
                queries: null,
                sorts: null,
                multisort: ['ctrlKey', 'shiftKey', 'metaKey'],
                page: null,
                queryEvent: 'blur change',
                recordCountTarget: null,
                recordCountPlacement: 'after',
                paginationLinkTarget: null,
                paginationLinkPlacement: 'after',//defalut after
                paginationPrev: '前一页',
                paginationNext: '下一页',
                paginationGap: [1,2,2,1],
                recordCountPageBoundTemplate: '{pageLowerBound} 至 {pageUpperBound}',
                recordCountPageUnboundedTemplate: '{pageLowerBound} 至 {pageUpperBound}',
                recordCountFilteredTemplate: ' (总共 {recordsTotal} 条)',
                recordCountText:"从",
                recordCountTotalTemplate: '条',
                searchTarget: null,
                searchPlacement: 'before',//defalut before
                perPageTarget: null,
                perPagePlacement: 'before',//defalut before
                perPageText: '每页: ',
                processingText: '加载中...',
                pageText: '页码: ',
                processingText: '加载中 <img src="/resources/admin/lib/jquery-dynatable/loading.gif" />'
            },
            dataset: {
                ajax: true,
                ajaxOnLoad: true,
                ajaxMethod: 'GET',
                ajaxDataType: 'json',
//            totalRecordCount: null,
//            queries: null,
//            queryRecordCount: null,
//            page: null,
                perPageDefault: 10,
                perPageOptions: [10,20,50,100],
//            sorts: null,
//            sortsKeys: null,
//            sortTypes: {},
                records:[]
            },
            table: {
                defaultColumnIdStyle: 'trimDash'
            },
            params: {
                dynatable: 'dynatable',
                queries: "queries",//query condition model
                sorts: 'sorts',
                page: 'page', //current page,default page
                perPage: 'size',//size of per page,default perPage
                offset: 'offset',
                records: 'records',
                record: null,
                queryRecordCount: 'queryRecordCount',
                totalRecordCount: 'totalRecordCount'
            }
        });

        $.ajaxSetup({
            complete: function (XMLHttpRequest, textStatus) {
                var sessionTimeout = XMLHttpRequest.getResponseHeader("session-timeout");
                if (sessionTimeout == "true") {
                    window.location.href = "/admin/login";
                }
            }
        });

    });
</script>