package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.repository.DomainRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: lxl
 */
public interface RoleRepository extends DomainRepository<Role>{

    List findByActive(Boolean active);

    @Query(value = "select count(1) from admin_staff_role where role_id = ?1", nativeQuery = true)
    Long isDelRole(Long roleId);

}
