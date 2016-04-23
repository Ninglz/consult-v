package com.dsecet.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.version.Version;
import com.dsecet.core.service.admin.VersionService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.VersionListVo;

/**
 * Created on 2015年10月20日
 * <p>Description: 版本管理</p>
 * @author         heyue
 * @version        1.0
 */
@Controller
@RequestMapping("/version")
public class AdminVersionController {
    @Autowired
    private VersionService versionService;
    
    @RequestMapping("/")
    public ModelAndView goVersion(){
        return new ModelAndView("version/version_list");
    }

    @RequestMapping("/list")
    public @ResponseBody PageableResponse list(@RequestParam(required = false) String keyword,Pageable pageable){  
    	Page<Version> page = versionService.findCondition(keyword,pageable);
    	return PageableResponse.of(VersionListVo.of(page), page.getContent().size(), (int)page.getTotalElements());
    }
    
    @RequestMapping("/create")
    public ModelAndView goCreate(){
        return new ModelAndView("version/version_create");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(String name,String description,boolean forceUpdate,MultipartFile new_apk){
    	Version version = versionService.create(name, description, forceUpdate, new_apk);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/version/");
        return modelAndView;
    }
    
}
