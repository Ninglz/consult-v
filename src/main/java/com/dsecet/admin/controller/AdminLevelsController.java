package com.dsecet.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.record.Levels;
import com.dsecet.core.service.admin.LevelsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2016年3月25日
 * <p>Title:       咨询汇_级别</p>
 * <p>Description: 咨询者/师级别控制层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Controller
@Slf4j
@RequestMapping("/levels")
public class AdminLevelsController {
	
	/**
	 * <p>Description:获取所有级别</p>
	 * @author:宁良竹
	 * @update: 2016年3月25日
	 * @return
	 */
	@RequestMapping(value = "/consumer", method = RequestMethod.GET)
	public ModelAndView getAll(){
		ModelAndView model = new ModelAndView("levels/levels_consumer");
		List<Levels> list = levelsService.findAll();
		model.addObject("list", list);
		return model;
	}
	
	/**
	 * <p>Description:保存所有咨询者的级别</p>
	 * @author:宁良竹
	 * @update: 2016年3月25日
	 * @param levels
	 * @return
	 */
	@RequestMapping(value="/save/consumer",method=RequestMethod.POST)
	public @ResponseBody
		String saveConsumerAll(Double[] levels){
		List<Levels> list = levelsService.findAll();
		List<Levels> colls = new ArrayList<Levels>();
		if(null!=levels&&levels.length==list.size()){
			for (int i = 0; i < levels.length; i++) {
				Levels l = list.get(i);
				l.setConsumerProportion(levels[i]);
				colls.add(l);
			}
			levelsService.save(colls);
			return "success";
		}
		return  "error";
	}
	
	/**
	 * <p>Description:保存所有咨询师的级别</p>
	 * @author:宁良竹
	 * @update: 2016年3月25日
	 * @param levels
	 * @return
	 */
	@RequestMapping(value="/save/experts",method=RequestMethod.POST)
	public @ResponseBody
		String saveExpertsAll(Double[] levels){
		List<Levels> list = levelsService.findAll();
		List<Levels> colls = new ArrayList<Levels>();
		if(null!=levels&&levels.length==list.size()){
			for (int i = 0; i < levels.length; i++) {
				Levels l = list.get(i);
				l.setExpertsProportion(levels[i]);
				colls.add(l);
			}
			levelsService.save(colls);
			return "success";
		}
		return  "error";
	}
	
	@Autowired
	private LevelsService levelsService;
	
	/**
	 * <p>Description:获取咨询师所有级别</p>
	 * @author:宁良竹
	 * @update: 2016年3月25日
	 * @return
	 */
	@RequestMapping(value = "/experts", method = RequestMethod.GET)
	public ModelAndView getExpertsAll(){
		ModelAndView model = new ModelAndView("levels/levels_experts");
		List<Levels> list = levelsService.findAll();
		model.addObject("list", list);
		return model;
	}

}
