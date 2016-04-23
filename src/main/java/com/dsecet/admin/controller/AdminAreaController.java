package com.dsecet.admin.controller;

import static com.dsecet.foundation.model.StatusResponse.success;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.record.City;
import com.dsecet.core.domain.record.Province;
import com.dsecet.core.service.CityService;
import com.dsecet.core.service.ProvinceService;
import com.dsecet.foundation.model.StatusResponse;

/**
 * Created on 2016年3月28日
 * <p>Title:       咨询汇_平台地区段</p>
 * @author         heyue
 */
@Controller
@RequestMapping("/area")
public class AdminAreaController {
	
	@Autowired
	private CityService cityService;
	  
	@Autowired
	private ProvinceService provinceService;
	
	/**
	 * <p>Description:获取所有地区</p>
	 * @author:heyue
	 * @return
	 */
	@RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public ModelAndView getAll(Long id){
		ModelAndView modelAndView = new ModelAndView("city/city_consumer");
		List<Province> provinces = provinceService.findAll();
		return modelAndView.addObject("provinces",provinces);
    }
	
	@RequestMapping(value = "/getProvince", method = RequestMethod.POST)
	public @ResponseBody StatusResponse getProvince(){
		return success(provinceService.findAll());
	}
	
	@RequestMapping(value = "/getProvinceCity", method = RequestMethod.POST)
	public @ResponseBody StatusResponse getProvinceCity(Long id){
		return success(cityService.findCityByProvinceId(id));
	}
	
	@RequestMapping(value = "/getCity", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAllCity(Long id){
		List<City> getCity = cityService.findCityByProvinceId(id);
		Map<String,Object> backdata = new HashMap<String,Object>();
		backdata.put("ReCode", 1000);
		backdata.put("ReData", getCity);
		return backdata;
	}
	
	/**
	 * <p>Description:保存所有咨询者的地区比例</p>
	 * @author:heyue
	 * @return
	 */
	@RequestMapping(value="/save/consumer",method=RequestMethod.POST)
	public @ResponseBody
		String saveConsumerAll(Double[] proportion,Long id){
		List<City> list = cityService.findCityByProvinceId(id);
		List<City> colls = new ArrayList<City>();
		if(null!=proportion&&proportion.length==list.size()){
			for (int i = 0; i < proportion.length; i++) {
				City t = list.get(i);
				t.setConsumerProportion(proportion[i]);
				colls.add(t);
			}
			cityService.save(colls);
			return "success";
		}
		return  "error";
	}
	
	/**
	 * <p>Description:获取所有地区</p>
	 * @author:heyue
	 * @return
	 */
	@RequestMapping(value = "/experts", method = RequestMethod.GET)
    public ModelAndView getExpertsAll(Long id){
		ModelAndView modelAndView = new ModelAndView("city/city_experts");
		List<Province> provinces = provinceService.findAll();
		List<City> list = cityService.findCityByProvinceId(id);
		return modelAndView.addObject("provinces",provinces).addObject("list", list);
    }
	
	/**
	 * <p>Description:保存所有咨询者的地区比例</p>
	 * @author:heyue
	 * @return
	 */
	@RequestMapping(value="/save/experts",method=RequestMethod.POST)
	public @ResponseBody
		String saveExpertsAll(Double[] proportion,Long id){
		List<City> list = cityService.findCityByProvinceId(id);
		List<City> colls = new ArrayList<City>();
		if(null!=proportion&&proportion.length==list.size()){
			for (int i = 0; i < proportion.length; i++) {
				City t = list.get(i);
				t.setExpertsProportion(proportion[i]);
				colls.add(t);
			}
			cityService.save(colls);
			return "success";
		}
		return  "error";
	}
}
