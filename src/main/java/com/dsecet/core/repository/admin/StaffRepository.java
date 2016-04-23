package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.repository.DomainRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * Created by lxl on 14-8-26.
 */
public interface StaffRepository extends DomainRepository<Staff> {

    Staff findByAccountUsername(String username);


    @Query("select distinct c from #{#entityName} c where c.account.username in (?1)")
    Page<Staff> findByRelatedUniqueFields(Set<String> relatedUniqueFields, Pageable pageable);
}
