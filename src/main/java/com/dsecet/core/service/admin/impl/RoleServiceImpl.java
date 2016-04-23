package com.dsecet.core.service.admin.impl;

import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.repository.admin.RoleListRepository;
import com.dsecet.core.repository.admin.RoleListRepositoryImpl;
import com.dsecet.core.repository.admin.RoleRepository;
import com.dsecet.core.service.admin.ResourcesService;
import com.dsecet.core.service.admin.RoleService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lxl
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleListRepository roleListRepository;

    @Autowired
    private ResourcesService resourcesService;

    @Override
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Role> getRoles(Pageable pageable){
        RoleListRepositoryImpl.RoleQueryBuilder builder = RoleListRepositoryImpl.RoleQueryBuilder.newBuilder();
        return roleListRepository.getByConditions(builder, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(@NotNull Long id){
        return roleListRepository.findOne(id);
    }

    @Override
    public Role save(@NotNull Role role){
        return roleListRepository.save(role);
    }

    @Override
    public Role save(@NotBlank String name, @NotBlank String code,@NotNull Long[] resources){
        Role role = new Role();
        role.setRoleName(name);
        role.setRoleCode(code);

        Arrays.asList(resources).stream().forEach(e -> {
            role.getResourceses().add(resourcesService.getById(e));
        });
        return roleRepository.save(role);
    }

    @Override
    public void del(@NotNull Long id){
        roleRepository.delete(id);
    }

    @Override
    public Boolean isDel(@NotNull Long roleId){
        return roleRepository.isDelRole(roleId) < 1;
    }


}
