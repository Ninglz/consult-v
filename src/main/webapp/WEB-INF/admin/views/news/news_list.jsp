<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>新闻列表</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<input type="hidden" id="activeMenu" value="news">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">新闻管理</a> > 新闻列表</p>
         <div class="main mt15 clearfix">
            <span class="fr f14">
               <input type="text" class="search_txt f14 ml10" placeholder="新闻标题" name="keyword" id="keyword" />
               <a href="javascript:void(0)" id="serachCondition" class="search_btn f14 color_ff">搜索</a>
            </span>
         </div>
         <table id="news_table"  class="main mt15 pro_list">
              <thead>
         
                <!-- <th  class="whitecustumer" data-dynatable-column="id">圈子id</th> -->
                <th  class="whitecustumer" data-dynatable-column="title">标题</th>
                <th  class="whitecustumer" data-dynatable-column="titleImg">新闻图</th>
                <th  class="whitecustumer" data-dynatable-column="digest">摘要</th>
                <th  class="whitecustumer" data-dynatable-column="lastModified">创建时间</th>
                <th  class="whitecustumer" data-dynatable-column="industryName">行业</th>
                <th  class="whitecustumer" data-dynatable-column="id">操作</th>
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

    function expertsConsumerRowWriter(rowIndex, record, columns, cellWriter) {
    	 var tr = '';
         var activeFlag = "";
         for (var i = 0, len = columns.length; i < len; i++) {
        	 if(columns[i].id == "titleImg"){
                 var operation = "";
                 operation += '&nbsp; <img src="'+ record.titleImg +'" width="200" />';
                 record.titleImg = operation;
               }
             if(columns[i].id == "id"){
                 var operation = "";
                 operation += '&nbsp; <a class="btn btn-success btn-xs btn-line" href="/admin/news/delete?newsId='+ record.id +'">删除</a>';
                 record.id = operation;
               }

             tr += cellWriter(columns[i], record);
         }
        return '<tr>' + tr + '</tr>';
    }

    var dynatable = $('#news_table').dynatable({
        table: {
            bodyRowSelector: 'td'
        },
        dataset: {
            ajaxUrl: '/admin/news/list'
        },
        writers: {
            _rowWriter: expertsConsumerRowWriter
        }
    }).data('dynatable');
    
    $("#serachCondition").click(function () {
    	$('#news_table').find("tr:gt(0)").remove();
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
