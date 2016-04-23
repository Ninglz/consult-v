package com.dsecet.core.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dsecet.core.domain.system.Industry;

public interface IndustryService {

	Page<Industry> queryByCondition(String keyword, Pageable pageable);

	Industry findOne(Long id);
	
	Industry save(Industry industry);

	List<Industry> findAll();

	void save(List<Industry> colls);

	void delete(Long id);

	Industry save(String name, Double consumerProportion, Double expertsProportion);

}
