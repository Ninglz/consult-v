package com.dsecet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created on 2016年3月29日
 * <p>Title:       springmvc 文件上传过滤</p>
 * <p>Description: </p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public class CommonsMultiparResolver  extends CommonsMultipartResolver{
	
	@Override
	public boolean isMultipart(HttpServletRequest request) {
		  String uri = request.getRequestURI();  
	        //过滤使用百度EmEditor的URI  
	        if (uri.contains("jsp/imageUp.jsp")) {   
	            return false;  
	        }  
	        return super.isMultipart(request);  
	}

}
