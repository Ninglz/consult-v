package com.dsecet.core.service.admin;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.foundation.model.query.StaffVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: lxl
 */
public interface StaffService{

    Staff getById(Long id);

    Staff getByUserName(String name);

    Page<Staff> getByConditions(StaffVo vo, Pageable pageable);

    Staff save(String userName, String password, String cellNo, String realName, String email, String idCard, Long[] roleIds);

    Staff update(Staff s);

    void del(Long id);

    void delStaffRoles(Long id);
}
