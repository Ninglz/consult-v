package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.repository.DomainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * User: lxl
 * Date: 14/12/11
 */
@NoRepositoryBean
public interface StaffListRepository extends DomainRepository<Staff>{
    Page<Staff> getByConditions(StaffListRepositoryImpl.StaffQueryBuilder builder, Pageable pageable);
}
