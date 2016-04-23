package com.dsecet.core.service.admin.impl;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.repository.admin.StaffListRepository;
import com.dsecet.core.repository.admin.StaffListRepositoryImpl;
import com.dsecet.core.repository.admin.StaffRepository;
import com.dsecet.core.service.admin.RoleService;
import com.dsecet.core.service.admin.StaffService;
import com.dsecet.foundation.model.query.StaffVo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author: lxl
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffListRepository staffListRepository;

    @Autowired
    private RoleService roleService;

    public static final String DEFAULT_PASSWORD = "123456";

    @Override
    @Transactional(readOnly = true)
    public Staff getByUserName(@NotNull String name){
        return staffRepository.findByAccountUsername(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Staff> getByConditions(StaffVo vo, Pageable pageable) {
        StaffListRepositoryImpl.StaffQueryBuilder builder = StaffListRepositoryImpl.StaffQueryBuilder.newBuilder();
        builder.usernameCondiftion(vo.getUsername())
                .activeCondiftion(vo.getActive());
        return staffListRepository.getByConditions(builder,pageable);
    }

    @Override
    public Staff save(@NotBlank String userName, @NotBlank String password, String cellNo, String realName, String email, String idCard, @NotNull Long[] roleIds) {
        Staff staff = new Staff();
        staff.username(userName);
        staff.password(password);
        staff.setActive(true);
        staff.setRoot(false);
        Arrays.asList(roleIds).stream().forEach(e -> {
            staff.getRoles().add(roleService.getById(e));
        });
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(@NotNull Staff s){
        return staffRepository.save(s);
    }

    @Override
    @Transactional(readOnly = true)
    public Staff getById(@NotNull Long id){
        return staffRepository.findOne(id);
    }

    @Override
    public void del(@NotNull Long id){
         staffRepository.delete(id);
    }

    @Override
    public void delStaffRoles(Long id){

    }
}
