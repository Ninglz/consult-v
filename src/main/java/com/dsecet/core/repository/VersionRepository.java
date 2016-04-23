package com.dsecet.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.version.Version;

@NoRepositoryBean
public interface VersionRepository extends DomainRepository<Version>{

	Page<Version> findCondition(String name, Pageable pageable);


}
