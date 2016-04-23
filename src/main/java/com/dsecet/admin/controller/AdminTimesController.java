package com.dsecet.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.record.Levels;
import com.dsecet.core.domain.record.Times;
import com.dsecet.core.service.admin.TimesService;

/**
 * Created on 2016年3月28日
 * <p>Title:       咨询汇_平台时间段</p>
 * <p>Description: 平台新闻控制层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Controller
@RequestMapping("/times")
public class AdminTimesController {
	
	/**
	 * <p>Description:获取所有时间段</p>
	 * @author:宁良竹
	 * @update: 2016年3月28日
	 * @return
	 */
	@RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public ModelAndView getAll(){
		ModelAndView model = new ModelAndView("times/times_consumer");
		List<Times> list = timesService.findAll();
		model.addObject("list", list);
		return model;
    }
	
	/**
	 * <p>Description:保存所有咨询者的时间段</p>
	 * @author:宁良竹
	 * @update: 2016年3月28日
	 * @param times
	 * @return
	 */
	@RequestMapping(value="/save/consumer",method=RequestMethod.POST)
	public @ResponseBody
		String saveConsumerAll(Double[] times){
		List<Times> list = timesService.findAll();
		List<Times> colls = new ArrayList<Times>();
		if(null!=times&&times.length==list.size()){
			for (int i = 0; i < times.length; i++) {
				Times t = list.get(i);
				t.setConsumerProportion(times[i]);
				colls.add(t);
			}
			timesService.save(colls);
			return "success";
		}
		return  "error";
	}

    
    @Autowired
    private TimesService timesService;
    
    

	/**
	 * <p>Description:获取咨询师所有时间段</p>
	 * @author:宁良竹
	 * @update: 2016年3月28日
	 * @return
	 */
	@RequestMapping(value = "/experts", method = RequestMethod.GET)
    public ModelAndView getExpertsAll(){
		ModelAndView model = new ModelAndView("times/times_experts");
		List<Times> list = timesService.findAll();
		model.addObject("list", list);
		return model;
    }
	
	/**
	 * <p>Description:保存所有咨询者的时间段</p>
	 * @author:宁良竹
	 * @update: 2016年3月28日
	 * @param times
	 * @return
	 */
	@RequestMapping(value="/save/experts",method=RequestMethod.POST)
	public @ResponseBody
		String saveExpertsAll(Double[] times){
		List<Times> list = timesService.findAll();
		List<Times> colls = new ArrayList<Times>();
		if(null!=times&&times.length==list.size()){
			for (int i = 0; i < times.length; i++) {
				Times t = list.get(i);
				t.setExpertsProportion(times[i]);
				colls.add(t);
			}
			timesService.save(colls);
			return "success";
		}
		return  "error";
	}
}
