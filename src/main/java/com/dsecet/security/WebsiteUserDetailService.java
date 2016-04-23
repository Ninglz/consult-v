package com.dsecet.security;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class WebsiteUserDetailService implements UserDetailsService{

    @Autowired
    private ConsumerService consumerService;

    @Override
    public UserDetails loadUserByUsername(String cellNo)
            throws UsernameNotFoundException{
        Consumer consumer = consumerService.identify(cellNo);
        if(Objects.isNull(consumerService)){
            throw new RespondableException(ErrorCode.USER_NO_SUCH, "Unable to find consumer " + cellNo);
        }
        List authorities = Lists.newArrayList();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CONSUMER");
        authorities.add(authority);
        User user = new User(consumer.getUsername(), consumer.getPassword(), consumer.isActive(), true, true, !consumer.isLocked(), authorities);
        return user;
    }

}
