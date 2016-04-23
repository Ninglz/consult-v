package com.dsecet.core.repository.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.repository.DomainRepository;

@NoRepositoryBean
public interface IndustryRepository extends DomainRepository<Industry>{

	Page<Industry> queryByCondition(String keyword, Pageable pageable);

}
