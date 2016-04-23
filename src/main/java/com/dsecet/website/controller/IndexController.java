package com.dsecet.website.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.service.SystemProperty;

/**
 * Created on 2016年4月20日
 * <p>Title:      咨询汇_pc/p>
 * <p>Description: pc跳转</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Controller
public class IndexController{
	
	private ModelAndView returnView(HttpSession session,String view){
		if(session.getAttribute("user")==null)
    		return new ModelAndView("redirect:/login");
		 return new ModelAndView(view);
	}
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session){
    	session.setAttribute("tel", SystemProperty.tel);
        return new ModelAndView("login");
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register");
    }
    
    @RequestMapping(value = "/register/success", method = RequestMethod.GET)
    public ModelAndView registerSuccess(HttpSession session){
    	return returnView(session, "register_success");
    }
    
    @RequestMapping(value = "/beExport", method = RequestMethod.GET)
    public ModelAndView beExport(HttpSession session){
    	return returnView(session, "beExport");
    }
    
    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public ModelAndView signout(HttpSession session){
    	session.invalidate();
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView news(){
        return new ModelAndView("news/news");
    }
    
}
