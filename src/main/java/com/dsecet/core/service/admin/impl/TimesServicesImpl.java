package com.dsecet.core.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.record.Times;
import com.dsecet.core.repository.admin.TimesRepository;
import com.dsecet.core.service.admin.TimesService;

@Service
@Transactional
public class TimesServicesImpl  implements TimesService{

	@Override
	public Times save(Times times) {
		return timesRepository.save(times);
	}

	@Override
	public void save(List<Times> times) {
		 timesRepository.save(times);
		
	}

	@Override
	public Times findOne(Long id) {
		return timesRepository.findOne(id);
	}

	@Override
	public List<Times> findAll() {
		return timesRepository.findAllByOrderByTimeFromAsc();
	}
	
	@Autowired
	private TimesRepository timesRepository;

	@Override
	public Times findProportion(Long weeks, Long times) {
		return timesRepository.findProportion(weeks, times);
	}

}
