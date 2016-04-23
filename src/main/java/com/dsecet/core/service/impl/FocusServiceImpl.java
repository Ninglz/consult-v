package com.dsecet.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.Focus;
import com.dsecet.core.repository.FocusRepository;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.core.service.FocusService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ExpertsListVo;

/**
 * 关注接口实现类
 * @author heyue
 * */

@Service
@Transactional
public class FocusServiceImpl implements FocusService {

	@Autowired
	private FocusRepository focusRepository;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ExpertsService expertsService;
	
	@Override
	public void save(Long consumerId, Long expertsId,Boolean active) {
		Consumer consumer = consumerService.findOne(consumerId);
		Experts experts = expertsService.findOne(expertsId);
		Focus findActivite = focusRepository.findActivite(consumerId, expertsId);
		if(active == true && findActivite == null){
			Focus focus = new Focus();
			focus.setConsumer(consumer);
			focus.setExperts(experts);
			focusRepository.save(focus);
		}else if(active == false){
			if(findActivite != null){
				focusRepository.removeFocusShip(consumerId, expertsId);
			}
		}
	}

	@Override
	public PageableResponse<ExpertsListVo> findByConditions(String name, String industryId, String evaluation, String price,Long id,
			Pageable pageable) {
		name = name == null ? "" : name;
		industryId = industryId == null ? "" : industryId;
		price = price == null ? "" : price;
		evaluation = evaluation == null ? "" : evaluation;
		Page page = focusRepository.findByConditions(name, industryId, evaluation, price,id.toString(), pageable);
		return PageableResponse.of(ExpertsListVo.of(page.getContent()), page.getContent().size(), (int) page.getTotalElements());
	}
}
