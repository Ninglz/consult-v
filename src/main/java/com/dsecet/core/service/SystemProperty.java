package com.dsecet.core.service;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;

/**
 * Created on 2016年4月21日
 * <p>Title:       重构</p>
 * <p>Description: 系统配置</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("rawtypes")
@Repository
public class SystemProperty  implements ApplicationListener{

	@Resource
	private SystemDefaultPropertyService defaultPropertyService;
	private static boolean isStart = false;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (!isStart) {//这个可以解决项目启动加载两次的问题
	           isStart= true;
	           of(defaultPropertyService.getUsed());
	       }
		
	}
	
	public static void of(SystemDefaultProperty systemProperty){
		webUrl = systemProperty.getWebsiteStaticUrl();
        tel = systemProperty.getCell();
        storageFilePrefix = systemProperty.getStorageFilePrefix();
        informationPrefix = systemProperty.getInformationPrefix();
        newsUrl = systemProperty.getNewsPrefix();
        if(systemProperty.getFreeIntegral() != null){
        	integral = systemProperty.getFreeIntegral();
        }else{
        	integral = 0.0D;
        }
        activityUrl = systemProperty.getActivityPrefix();
        androidUrl = systemProperty.getAndoridUrl();
        consumerPrice = systemProperty.getConsumerPrice();
        consumerStartPrice = systemProperty.getConsumerStartPrice();
        consumerTimeForm = systemProperty.getConsumerTimeForm();
        consumerTimeTo = systemProperty.getConsumerTimeTo();
        consumerProportion = systemProperty.getConsumerProportion();
	}
	
	public static String webUrl;
	
	public static String tel;
	
	public static String storageFilePrefix;
	
	public static String informationPrefix;
	
	public static String newsUrl;
	
	public static Double integral;
	
	public static String activityUrl;
	
	public static String androidUrl;
	
	public static Double consumerPrice;
	
	public static Double consumerStartPrice;
	
	public static Long consumerTimeForm;
	
	public static Long consumerTimeTo;
	
	public static Double consumerProportion;
	
}
