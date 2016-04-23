package com.dsecet.core.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuartzTestServiceImpl implements QuartzTestService{

	@Scheduled(cron="0/5 * *  * * ? ")
	public void tets() {
		 System.out.println("进入测试");  
		
	}

}
