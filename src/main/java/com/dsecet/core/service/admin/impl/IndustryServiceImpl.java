package com.dsecet.core.service.admin.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.repository.admin.IndustryRepository;
import com.dsecet.core.service.admin.IndustryService;;

@Service
@Transactional
public class IndustryServiceImpl implements IndustryService{

	@Autowired
	private IndustryRepository industryRepository;
	
	@Override
	public Page<Industry> queryByCondition(String keyword, Pageable pageable) {
		return industryRepository.queryByCondition(keyword,pageable);
	}

	@Override
	public Industry findOne(Long id) {
		return industryRepository.findOne(id);
	}

	@Override
	public Industry save(Industry industry) {
		return industryRepository.save(industry);
	}

	@Override
	public List<Industry> findAll() {
		return industryRepository.findAll();
	}

	@Override
	public void save(List<Industry> colls) {
		industryRepository.save(colls);
	}

	@Override
	public void delete(Long id) {
		industryRepository.delete(id);
	}

	@Override
	public Industry save(String name, Double consumerProportion, Double expertsProportion) {
		Industry industry = new Industry();
		industry.setName(name);
		industry.setConsumerProportion(consumerProportion);
		industry.setExpertsProportion(expertsProportion);
		return null;
	}
}
