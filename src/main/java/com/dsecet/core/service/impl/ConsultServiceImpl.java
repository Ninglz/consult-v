package com.dsecet.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.admin.vo.ConsultListVo;
import com.dsecet.api.vo.ConsultVo;
import com.dsecet.core.domain.record.Consult;
import com.dsecet.core.repository.ConsultRepository;
import com.dsecet.core.service.ConsultService;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.foundation.model.PageableResponse;

@Service
@Transactional
public class ConsultServiceImpl implements ConsultService {
	
	@Autowired
	private ConsultRepository consultRepository;

	@Override
	public PageableResponse<ConsultVo> findCondition(String name,Long id, Pageable pageable) {
		NLZPage<Consult> page = consultRepository.findpage(name, id,pageable);
		return PageableResponse.of(page.setCollection(ConsultVo.of(page)));
	}

	@Override
	public PageableResponse<ConsultListVo> queryByCondition(String keyword, Pageable pageable) {
		NLZPage<Consult> page = consultRepository.findpage(keyword, null,pageable);
		return PageableResponse.of(page.setCollection(ConsultVo.of(page)));
	}
}
