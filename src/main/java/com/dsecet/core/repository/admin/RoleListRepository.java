package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.repository.DomainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * User: lxl
 * Date: 14/12/11
 */
@NoRepositoryBean
public interface RoleListRepository extends DomainRepository<Role>{
    Page<Role> getByConditions(RoleListRepositoryImpl.RoleQueryBuilder builder, Pageable pageable);
}
