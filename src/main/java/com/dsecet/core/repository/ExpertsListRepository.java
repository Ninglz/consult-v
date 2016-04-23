package com.dsecet.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.Experts;

/**
 * @author heyue
 */
@NoRepositoryBean
public interface ExpertsListRepository extends DomainRepository<Experts>{
	
	Page findByConditions(String name, String industryId, String evaluation, String price, String id,Pageable pageable);

	Page findRecommendExperts(String id,String evaluation,Pageable pageable);

	Page<Experts> queryByCondition(AppType appType,String keyword, String active,Long expertState, Pageable pageable);
	
	Boolean updateBatch(Long time);

}
