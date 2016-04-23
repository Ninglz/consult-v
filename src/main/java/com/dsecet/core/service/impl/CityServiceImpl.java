package com.dsecet.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.core.domain.record.City;
import com.dsecet.core.repository.CityRepository;
import com.dsecet.core.service.CityService;

/**
 * 城市接口实现类
 * @author: heyue
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> findCityByProvinceId(Long id) {
		return cityRepository.findByProvinceId(id);
		//return cityRepository.findCityByProvinceId(id);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Override
	public void save(List<City> colls) {
		cityRepository.save(colls);
	}
	
}
