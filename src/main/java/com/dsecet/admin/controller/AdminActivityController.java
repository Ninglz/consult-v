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

import com.dsecet.core.domain.system.Activity;
import com.dsecet.core.service.ActivityService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ActivityVo;

/**
 * Created on 2016年3月22日
 * <p>Title:       咨询汇_平台活动</p>
 * <p>Description: 平台活动控制层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         heyue
 * @version        1.0
 */
@Controller
@RequestMapping("/activity")
public class AdminActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "activity/activity_list";
    }
	
	/**
	 * <p>Description:活动列表</p>
	 * @author:宁良竹
	 * @update: 2016年3月22日
	 * @param keyword
	 * @param active
	 * @param expertState
	 * @param pageable
	 * @return
	 */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse list(String keyword, Pageable pageable){
    	Page<Activity> page = activityService.findPage(keyword, pageable);
        return PageableResponse.of(ActivityVo.of(page), page.getContent().size(), (int) page.getTotalElements());
    }
    
    /**
     * <p>Description:跳转到活动创建页面</p>
     * @author:heyue
     * @return
     */
    @RequestMapping(value = "/create")
    public
    ModelAndView toCreate(){
        return new ModelAndView("activity/activity_create");
    }
    
    /**
     * <p>Description:添加活动</p>
     * @author:heyue
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(String name,MultipartFile img,String describe,String url){
    	activityService.create(name,img,describe,url);
        return new ModelAndView("redirect:/admin/activity/");
    }
    
    /**
     * <p>Description:删除活动</p>
     * @author:heyue
     * @return
     */
    @RequestMapping(value = "/delete")
    public ModelAndView delete(Long id) {
    	Activity activity = activityService.findOne(id);
    	activity.setActive(false);
    	activityService.save(activity);
        return new ModelAndView("redirect:/admin/activity/");
    }
}
