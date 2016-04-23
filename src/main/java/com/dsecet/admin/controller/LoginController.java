package com.dsecet.admin.controller;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.service.admin.StaffService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Set;

/**
 * @author: lxl
 */
@Controller
public class LoginController{

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StaffService staffService;

    @RequestMapping(method = RequestMethod.GET)
    public String siginAdmin() {
        return "login";
    }

    @RequestMapping(value = "/common/signed", method = RequestMethod.GET)
    public String signed() {
        return "index";
    }

    @RequestMapping(value = "/common/menu", method = RequestMethod.GET, produces = {"application/text;charset=UTF-8"})
    public
    @ResponseBody
    String menu(Locale locale){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Staff staff = staffService.getByUserName(user.getUsername());
        Set<String> resources = Sets.newHashSet();
        staff.getRoles().stream().forEach(e -> {
            e.getResourceses().stream().forEach(s -> {
                resources.add(s.getPatternUrl());
            });
        });
        return staffMenu(resources, locale);
    }

    public String staffMenu(Set<String> resources,Locale locale){
        String reslut = "";
        //用户
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_CUSTOMER)){
            reslut += messageSource.getMessage("html.staff.menu.customer", null, locale);
        }
        //活动
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_ACTIVITY)){
            reslut += messageSource.getMessage("html.staff.menu.activity", null, locale);
        }
        /*//专家
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_EXPERTS)){
            reslut += messageSource.getMessage("html.staff.menu.experts", null, locale);
        }*/
        
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_NEWS)){
            reslut += messageSource.getMessage("html.staff.menu.news", null, locale);
        }
        /*if(resources.contains(ResourcesPattern.ROLE_PATTERN_JURISDICTION)){
            reslut += messageSource.getMessage("html.staff.menu.jurisdiction", null, locale);
        }
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_FUNDS)){
            reslut += messageSource.getMessage("html.staff.menu.funds", null, locale);
        }
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_VERSION)){
            reslut += messageSource.getMessage("html.staff.menu.version", null, locale);
        }*/
        //系统
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_SYSTEM)){
            reslut += messageSource.getMessage("html.staff.menu.system", null, locale);
        }
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_VERSION)){
            reslut += messageSource.getMessage("html.staff.menu.version", null, locale);
        }
        //财务管理
        if(resources.contains(ResourcesPattern.ROLE_PATTERN_FINANCE)){
        	reslut += messageSource.getMessage("html.staff.menu.finance", null, locale);
        }
        return reslut;
    }

}
