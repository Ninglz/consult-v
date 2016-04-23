package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Resources;
import com.dsecet.core.repository.DomainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * User: lxl
 * Date: 14/12/11
 */
@NoRepositoryBean
public interface ResourcesListRepository extends DomainRepository<Resources>{
    Page<Resources> getByConditions(ResourcesListRepositoryImpl.ResourcesQueryBuilder builder, Pageable pageable);
}
