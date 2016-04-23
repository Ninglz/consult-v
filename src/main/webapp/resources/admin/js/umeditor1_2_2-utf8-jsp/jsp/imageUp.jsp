<%--     <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
        <%@ page import="com.dsecet.util.image.Uploader,com.dsecet.core.service.SystemProperty" %>

            <%
    response.setHeader("X-Frame-Options", "SAMEORIGIN");
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	response.setHeader("X-Frame-Options", "SAMEORIGIN");
	response.setHeader("X-Content-Type-Options", "MIME-sniffing");
    Uploader up = new Uploader(request);
    System.err.println("~~~~~~~~~~~~~~");
    up.setSavePath("upload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB

    
   
    try {
		up.upload(SystemProperty.storageFilePrefix);
	} catch (Exception e) {
		e.printStackTrace();
	}

    String callback = request.getParameter("callback");

    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

    result = result.replaceAll( "\\\\", "\\\\" );

    if( callback == null ){
        response.getWriter().print( result );
    }else{
        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
    }
    %>
 --%>