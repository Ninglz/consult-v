package com.dsecet.util;

import com.dsecet.core.service.admin.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by lxl on 14-8-27.
 */
@Component("adminSaltSource")
public class StaffSaltSource implements SaltSource{

    @Autowired
    StaffService staffService;


    @Override
    public Object getSalt(UserDetails user) {
        return staffService.getByUserName(user.getUsername()).getSalt();
    }
}
