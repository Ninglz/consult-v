package com.dsecet.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.dsecet.core.domain.record.City;
import com.dsecet.core.domain.record.Province;

/**
 * @author: lxp
 * Date: 2015/5/11 16:02
 * 66cf-v2
 */
//@NoRepositoryBean
public interface CityRepository extends DomainRepository<City>{

	List<City> findByProvinceId(Long id);
}
