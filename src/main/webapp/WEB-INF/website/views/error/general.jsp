<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/15
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>error</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1,user-scalable=no">
    <style type="text/css">
        .box{width: 96%;max-width: 640px;margin: 0 auto}
        img{width: 100% !important;}
    </style>
</head>
<body>
<div class="box">
        <h3 class="box_newsTitle">
            <span class="">${statusCode }</span>
        </h3>
        ${errorMessage}
</div>
</body>
</html>
