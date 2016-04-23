package com.dsecet.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.record.Province;
import com.dsecet.core.repository.ProvinceRepository;
import com.dsecet.core.service.ProvinceService;

/**
 * 专家接口实现类
 * @author: heyue
 */
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Override
	public List<Province> findAll() {
		return provinceRepository.findAll();
	}
	
}
