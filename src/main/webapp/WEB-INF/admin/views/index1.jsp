<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>平台管理-专家管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" name="viewport">
<link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
<script src="/resources/admin/js/common/js/jquery-1.7.1.min.js"></script>
</head>

<body>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         <p class="main mt20"><a href="">平台管理</a> > 专家管理</p>
         <div class="main mt15 clearfix">
            <span class="fr f14">状态:
               <select class="select_m">
                  <option value="">全部</option>
                  <option value="">未处理</option>
                  <option value="">审核通过</option>
                  <option value="">审核未通过</option>
                  <option value="">帐号正常</option>
                  <option value="">帐号封停</option>
               </select>
               <input type="text" class="search_txt f14 ml10" placeholder="请输入关键字" name="keyword" />
               <a href="" class="search_btn f14 color_ff">搜索</a>
            </span>
         </div>
         <table cellpadding="0" cellspacing="0" class="main mt15 pro_list">
            <tr>
               <th>专家姓名</th>
               <th>性别</th>
               <th>所属业界</th>
               <th>资历</th>
               <th>擅长</th>
               <th>状态</th>
               <th>操作</th>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>未处理</td>
               <td width="240" class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>审核通过</td>
               <td width="240" class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
                  <a href="javascript:" class="color_00a ml10">展示置顶</a>
                  <a href="javascript:" class="color_ee3 ml10">帐号封停</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>审核未通过</td>
               <td class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>帐号正常</td>
               <td class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
                  <a href="javascript:" class="color_00a ml10">展示置顶</a>
                  <a href="javascript:" class="color_ee3 ml10">展示下沉</a>
                  <a href="javascript:" class="color_ee3 ml10">帐号封停</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>帐号封停</td>
               <td class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
                  <a href="javascript:" class="color_ee3 ml10">帐号解封</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>未处理</td>
               <td width="240" class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>审核通过</td>
               <td width="240" class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
                  <a href="javascript:" class="color_00a ml10">展示置顶</a>
                  <a href="javascript:" class="color_ee3 ml10">帐号封停</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>审核未通过</td>
               <td class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>帐号正常</td>
               <td class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
                  <a href="javascript:" class="color_00a ml10">展示置顶</a>
                  <a href="javascript:" class="color_ee3 ml10">展示下沉</a>
                  <a href="javascript:" class="color_ee3 ml10">帐号封停</a>
               </td>
            </tr>
            <tr>
               <td>张三</td>
               <td>男</td>
               <td>IT</td>
               <td>10年工作经历</td>
               <td>JAVA</td>
               <td>帐号封停</td>
               <td class="text_center">
                  <a href="javascript:" class="color_00a">查看详细</a>
                  <a href="javascript:" class="color_ee3 ml10">帐号解封</a>
               </td>
            </tr>
         </table>
         <!--页码-->
         <div class="page main" id="pagenum">
              <a href="" class=""> < </a>
              <a href="" class="pagenum">1</a>
              <a href="" class="pagenum current">2</a>
              <a href="" class="pagenum">3</a>
              <span class="numoff">...</span>
              <a href="" class="pagenum">10</a>
              <a href="" class=""> > </a>
              <span class="ml10">第<i id="currentpage" class="color_00a">6</i>/<i id="totalpage" class="color_00a">99</i>页</span>
              <span class="ml10">共 <i id="totalobj" class="color_00a">752</i> 条</span>
        </div>
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
      </td>
   </tr>
</table>
<script src="/resources/admin/js/common/js/jquery.longyuJs.js"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script>
   $(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
   });
</script>
</body>
</html>
