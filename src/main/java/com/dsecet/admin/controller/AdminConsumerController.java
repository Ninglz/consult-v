package com.dsecet.admin.controller;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.admin.aop.annotation.OptsTrace;
import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.domain.record.Levels;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.repository.admin.StaffRepository;
import com.dsecet.core.service.ConsultService;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.core.service.admin.LevelsService;
import com.dsecet.ext.sms.SmsSender;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ExpertsVo;
import com.dsecet.util.TimeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 后台用户
 * @author: heyue
 */
@Controller
@RequestMapping("/consumer")
@Slf4j
public class AdminConsumerController{

    @Autowired
    private ConsumerService consumerService;
     
    @Autowired
    private ExpertsService expertsService;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private SmsSender smsSender;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private LevelsService levelsService;
    
    @Autowired
    private ConsultService consultService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    /**
     *   用户列表
     *   @author heyue
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse list(String keyword, String active,Long expertState,Pageable pageable){
    	Page<Experts> expertsPage = expertsService.queryByCondition(keyword,active,expertState,pageable);
        return PageableResponse.of(ExpertsVo.of(expertsPage), expertsPage.getContent().size(), (int) expertsPage.getTotalElements());
    }
    
    /**
     * 用户详情
     * @author   heyue
     * */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView detail(Long id){
    	ModelAndView modelAndView = new ModelAndView("experts_detail");
    	Experts experts = expertsService.findOne(id);
    	ExpertsVo expertsVo = ExpertsVo.of(experts);
    	return modelAndView.addObject("expertsVo",expertsVo);
    }
    

    /**
     * 用户封停
     * @author heyue
     * */
    @OptsTrace(value = OptsTraceLog.OperationType.CUSTOMER_STATUS)
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public @ResponseBody
    Boolean update(
            @RequestParam("customerId") Long customerId, Boolean status) {
        Consumer consumer = consumerService.findOne(customerId);
        consumer.setActive(status);
        //consumerService.save(consumer);
        return status;
    }
    
    /**
     * 咨询师审核
     * @author heyue
     * */
    @RequestMapping(value = "/auth")
	public 
	@ResponseBody ModelAndView auth(Long id,boolean flag,String reason,Locale locale){
		
		log.info("Path:/admin/consumer/auth");
		expertsService.saveAuth(id, flag, reason);
		if(flag){
//			if(!smsSender.send(messageSource.getMessage("consumerAuthPass.sms.template",new String[]{experts.getCellNo()} ,locale),
//					Lists.newArrayList(experts.getCellNo()))){
//				throw new RespondableException(SMS_SEND_FAILED);
//			}
		}else{
//			if(!smsSender.send(messageSource.getMessage("consumerAuthNo.sms.template",new String[]{experts.getCellNo()} ,locale),
//					Lists.newArrayList(experts.getCellNo()))){
//				throw new RespondableException(SMS_SEND_FAILED);
//			}
		}
		log.info("return----------------success--------------------------");
		return new ModelAndView("redirect:/admin/consumer/");
	}
    
    /**
     * 修改密码
     * @author heyue
     * */
    @RequestMapping(value = "/resetPwd")
    public ModelAndView resetPwd(Long consumerId){
        ModelAndView modelAndView = new ModelAndView("consumer_resetPwd");
        return modelAndView.addObject("consumerId", consumerId);
    }
    
    @RequestMapping(value = "/doResetPwd")
    public ModelAndView doResetPwd(String password,String confirmPassword,Long consumerId){
    	Staff staff = staffRepository.findOne(consumerId);
    	staff.password(password);
    	staffRepository.save(staff);
        return new ModelAndView("redirect:/admin");
    }
    
    /**
     * 修改积分
     * @author heyue
     * */
    @RequestMapping(value = "/updateIntegral")
    public
    @ResponseBody
    ModelAndView updateIntegral(Long id,Double integral){
    	Experts experts = expertsService.findOne(id);
    	experts.getConsumerDetails().setIntegral(integral);
    	expertsService.save(experts);
    	return new ModelAndView("redirect:/admin/consumer/");
    }
    /**
     * 修改咨询者等级
     * @author heyue
     * */
    @RequestMapping(value = "/consumerLevels")
    public
    @ResponseBody
    ModelAndView updateConsumerLevels(Long id,Integer consumerLevels){
    	Experts experts = expertsService.findOne(id);
    	Levels levels = levelsService.findOne(Long.valueOf(consumerLevels));
    	experts.getConsumerDetails().setConsuemrLevels(levels);
    	expertsService.save(experts);
    	return new ModelAndView("redirect:/admin/consumer/");
    }
    /**
     * 修改咨询师等级
     * @author heyue
     * */
    @RequestMapping(value = "/expertsLevels")
    public
    @ResponseBody
    ModelAndView updateExpertsLevels(Long id,Integer expertsLevels){
    	Experts experts = expertsService.findOne(id);
    	Levels levels = levelsService.findOne(Long.valueOf(expertsLevels));
    	experts.getConsumerDetails().setExpertsLevels(levels);
    	expertsService.save(experts);
    	return new ModelAndView("redirect:/admin/consumer/");
    }
    
    /**
     * 专家置顶
     * */
    @RequestMapping(value = "/top")
    public
    @ResponseBody
    ModelAndView topExperts(Long id){
    	Experts experts = expertsService.findOne(id);
    	experts.setTop(true);
    	experts.setTopTime(TimeUtils.currentMillis());
    	expertsService.save(experts);
    	return new ModelAndView("redirect:/admin/consumer/");
    }
    /**
     * 推送
     * */
//    @RequestMapping(value = "/push")
//    public
//    @ResponseBody
//    	//安卓用户
//    Map<String,Object> push(String content,String keyword, String active,Long expertState){
//    	Map<String,Object> backdata = new HashMap<String,Object>();
//    	List<String> androidList = expertsService.findAndroidOrIosByCondition(AppType.ANDROID,keyword,active,expertState);
//    	//IOS用户
//    	List<String> iosList = expertsService.findAndroidOrIosByCondition(AppType.IOS,keyword,active,expertState);
//    	try {
//			PushHelper.batchPushIOS(content, iosList, 0);
//			PushHelper.batchPushANDORID(content, androidList, 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	
//		backdata.put("ReCode", 1000);
//		return backdata;
//    }
    
    /**
     * 用户历史咨询记录列表
     * */
    @RequestMapping(value = "/consult", method = RequestMethod.GET)
    public String consultIndex(){
        return "consult/consult_list";
    }
    
    @RequestMapping(value = "/consult/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse consultList(String keyword,Pageable pageable){
        		return consultService.queryByCondition(keyword,pageable);
    }
}
