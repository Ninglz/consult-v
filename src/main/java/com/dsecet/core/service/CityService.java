package com.dsecet.core.service;

import java.util.List;

import com.dsecet.core.domain.record.City;

/**
 *   省份接口
 * @author heyue
 * */
public interface CityService {

	List<City> findCityByProvinceId(Long id);

	List<City> findAll();

	void save(List<City> colls);
}
