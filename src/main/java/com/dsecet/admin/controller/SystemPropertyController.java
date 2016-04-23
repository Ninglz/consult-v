package com.dsecet.admin.controller;

import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with 66cf-v2
 * User : xp.li
 * Date : 2015/3/2.
 */
@Controller
@RequestMapping("/system_property")
public class SystemPropertyController{

    @Autowired
    private SystemDefaultPropertyService systemDefaultPropertyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("system/systemPropertyList");
        List<SystemDefaultProperty> list = systemDefaultPropertyService.findAll();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView systemPropertyDetail(@RequestParam(value = "id", required = false) Long id){
        ModelAndView modelAndView = new ModelAndView("system/systemPropertyDetail");
        SystemDefaultProperty systemDefaultProperty = null;
        if(id != null){
            systemDefaultProperty = systemDefaultPropertyService.get(id);
        }
        modelAndView.addObject("property", systemDefaultProperty);
        return modelAndView;
    }

//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public ModelAndView systemPropertyEdit(@ModelAttribute("property") SystemDefaultProperty property){
//        systemDefaultPropertyService.save(property);
//        return new ModelAndView("redirect:/admin/system_property/");
//    }
}
