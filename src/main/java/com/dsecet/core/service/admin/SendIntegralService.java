package com.dsecet.core.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dsecet.core.domain.record.SendIntegral;

/**
 * 系统发送积分Service
 * @author heyue
 * */
public interface SendIntegralService {

	Page<SendIntegral> findPage(String keyword, Pageable pageable);

}
