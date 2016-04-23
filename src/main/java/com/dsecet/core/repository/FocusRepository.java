package com.dsecet.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.user.Focus;

/**
 * 关注DAO
 * @author: heyue
 */
@NoRepositoryBean
public interface FocusRepository extends DomainRepository<Focus>{

	void removeFocusShip(Long consumerId, Long expertsId);

	Page findByConditions(String name, String industryId, String evaluation, String price, String id,Pageable pageable);
	
	Focus findActivite(Long consumerId,Long expertsId);
}
