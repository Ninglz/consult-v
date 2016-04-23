package com.dsecet.website.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.service.ExpertsService;

@Controller
@RequestMapping("/experts")
public class ExpertsController {
	
	@Autowired
	private ExpertsService expertsService;
	
	@RequestMapping(value = "/save", method = POST)
	public @ResponseBody ModelAndView saveExpertsInformation(Long id,@RequestParam(value="realname")String realname,
			@RequestParam(value="phone")String phone,@RequestParam(value="sex")Long sex,
			@RequestParam(value="IDcard")String IDcard,@RequestParam(value="headimg")MultipartFile headimg,
			@RequestParam(value="seniorityStart")String seniorityStart,@RequestParam(value="seniorityEnd")String seniorityEnd,
			@RequestParam(value="industry")Long industry,@RequestParam(value="city")Long city,
			@RequestParam(value="professional")String professional, @RequestParam(value="introduce")String introduce, 
			@RequestParam(value="certificate_img") MultipartFile[] certificate_img) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
	    Date seniorityStartDate =  sdf.parse(seniorityStart);  
	    Date seniorityEndDate =  sdf.parse(seniorityEnd);  
		
		expertsService.saveInformation(realname, sex, IDcard, headimg, seniorityStartDate,seniorityEndDate, industry, city, professional,
				introduce, null, certificate_img, id);
		return new ModelAndView("redirect:/insecure/signin");
	}
}
