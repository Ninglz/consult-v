package com.dsecet.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.foundation.model.NLZPage;

/**
 * @author: lxp
 * Date: 2015/5/11 16:02
 * 66cf-v2
 */
@NoRepositoryBean
public interface ActivityRepository extends DomainRepository<Activity>{

	NLZPage<Activity> findpage(String keyword, Pageable pageable);

}
