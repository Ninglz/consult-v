package com.dsecet.core.service.admin;

import com.dsecet.core.domain.admin.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: lxl
 */
public interface RoleService{

    Role getById(Long id);

    Role save(Role role);

    Role save(String name, String code, Long[] resources);

    List<Role> getAllRoles();

    Page<Role> getRoles(Pageable pageable);

    void del(Long id);

    Boolean isDel(Long roleId);

}
