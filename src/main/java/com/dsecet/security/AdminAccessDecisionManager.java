package com.dsecet.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author: lxl
 */
@Component
public class AdminAccessDecisionManager implements AccessDecisionManager{

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException{

        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca = ite.next();
            String needRole = ca.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()){
                if(ga.getAuthority().equals(needRole)){ // ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("forbidden");
    }

    @Override
    public boolean supports(ConfigAttribute attribute){
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz){
        return true;
    }
}
