package com.dsecet.core.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.repository.admin.SendIntegralRepository;
import com.dsecet.core.service.admin.SendIntegralService;

@Service
@Transactional
public class SendIntegralServiceImpl implements SendIntegralService {
	
	@Autowired
	private SendIntegralRepository sendIntegralRepository;

	@Override
	public Page<SendIntegral> findPage(String keyword, Pageable pageable) {
		return sendIntegralRepository.findPage(keyword,pageable);
	}
}
