package com.dsecet.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.service.admin.IndustryService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.IndustryVo;

@Controller
@RequestMapping("/industry")
public class AdminIndustryController {

	@Autowired
	private IndustryService industryService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(){
		return "industry/industry_list";
	}

	/**
	 *   行业列表
	 *   @author heyue
	 * */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public
	@ResponseBody
	PageableResponse list(String keyword,Pageable pageable){
		Page<Industry> industryPage = industryService.queryByCondition(keyword,pageable);
	    return PageableResponse.of(IndustryVo.of(industryPage), industryPage.getContent().size(), (int) industryPage.getTotalElements());
	}
	/**
	 *   新增/编辑行业
	 *   @author heyue
	 * */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public
	@ResponseBody
	ModelAndView save(Long id , Industry industry){
		if(id != null){
			Industry indu = industryService.findOne(id);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("industry", indu);
			industryService.save(industry);
		}else{
			industryService.save(industry);
		}
		return new ModelAndView("redirect:/admin/industry/");
	}
}
