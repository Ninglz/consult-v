package com.dsecet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.ExpertsService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class DetectionExpertsJob extends QuartzJobBean{

	@Autowired
	private ExpertsService expertsService;
	 SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	 Date d = new Date();  
	 String returnstr = DateFormat.format(d);    
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println(returnstr+"★★★★★★★★★★★");  
		//get到当前applicaotionContext
		WebApplicationContext app = ContextLoader.getCurrentWebApplicationContext();
		//取到Bean
		ExpertsService expertsService = (ExpertsService) app.getBean("expertsService");
		Long now = TimeUtils.currentMillis();
		Long t = TimeUtils.minusMinutes(now, 30);
		expertsService.updateBatch(t);
	}

}
