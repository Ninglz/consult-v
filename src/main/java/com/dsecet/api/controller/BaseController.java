package com.dsecet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.util.ModelUtils;

/**
 * Created on 2016年4月20日
 * <p>Title:       API</p>
 * <p>Description: Controller超类</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public abstract class BaseController {
	
	@Autowired
	private ConsumerService consumerService;
	
	public Experts validationToken(String token){
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.findOne(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		return (Experts)consumer;
	}

}
