package com.dsecet.admin.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;

@Controller
@RequestMapping("/system")
public class AdminSystemPropertyController {

	 @Autowired
	 private SystemDefaultPropertyService systemDefaultPropertyService;
	
	/**
	 * 系统配置展示
	 * */
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	 public ModelAndView list(){
	     ModelAndView modelAndView = new ModelAndView("system/system_list");
	     List<SystemDefaultProperty> list = systemDefaultPropertyService.findAll();
	     modelAndView.addObject("list", list);
	     return modelAndView;
	 }
	 
	 @RequestMapping(value = "/detail", method = RequestMethod.GET)
	    public ModelAndView systemPropertyDetail(@RequestParam(value = "id", required = false) Long id){
	        ModelAndView modelAndView = new ModelAndView("system/system_detail");
	        SystemDefaultProperty systemDefaultProperty = null;
	        if(id != null){
	            systemDefaultProperty = systemDefaultPropertyService.get(id);
	        }
	        modelAndView.addObject("property", systemDefaultProperty);
	        return modelAndView;
	    }
	 @RequestMapping(value = "/edit", method = RequestMethod.POST)
	 public ModelAndView systemPropertyEdit(Long id,Long freeTime,Long payTime,Long maxNum,
			 String cell,Double price,Double startPrice,Double proportion,Double consumerProportion,Double consumerPrice,Double consumerStartPrice,
			 Double freeIntegral,String timeForm,String timeTo,String consumerTimeForm,String consumerTimeTo) throws ParseException{
		 
		 SystemProperty.of(systemDefaultPropertyService.save(id, freeTime, payTime, maxNum, cell, price, startPrice, proportion, consumerProportion, consumerPrice, consumerStartPrice, freeIntegral, timeForm, timeTo, consumerTimeForm, consumerTimeTo));
		 return new ModelAndView("redirect:/admin/system/");
	}
}
