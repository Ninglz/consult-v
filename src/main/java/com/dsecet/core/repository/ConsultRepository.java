package com.dsecet.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.record.Consult;
import com.dsecet.foundation.model.NLZPage;

/**
 * @author: lxp
 * Date: 2015/5/11 16:02
 * 66cf-v2
 */
@NoRepositoryBean
public interface ConsultRepository extends DomainRepository<Consult>{

	NLZPage<Consult> findpage(String keyword, Long id,Pageable pageable);

}
