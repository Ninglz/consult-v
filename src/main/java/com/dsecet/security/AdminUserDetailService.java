package com.dsecet.security;

import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.service.admin.StaffService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxl on 14-8-26.
 */
@Component
public class AdminUserDetailService implements UserDetailsService {
    @Autowired
    private StaffService staffService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Staff c = staffService.getByUserName(username);
        if(c == null){ throw new UsernameNotFoundException(
                "Unable to find admin user: " + username); }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : c.getRoles()) {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                    role.getRoleCode());
            authorities.add(grantedAuthority);
        }
        User user = new User(c.getUsername(), c.getPassword(), c.isActive(),
                true, true, !c.isLocked(), authorities);
        return user;
    }

    /**
     * @author: lxl
     */
    @Getter
    @Setter
    public static class AdminSecurityFilter extends AbstractSecurityInterceptor implements Filter{

        private InvocationSecurityMetadataSourceService securityMetadataSource;

        private AuthenticationManager authenticationManager;


        public AdminSecurityFilter() {
        }

        public AdminSecurityFilter(AuthenticationManager authenticationManager, InvocationSecurityMetadataSourceService securityMetadataSource) {
            this.securityMetadataSource = securityMetadataSource;
            this.authenticationManager = authenticationManager;
        }

        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
            FilterInvocation fi = new FilterInvocation(request, response, chain);
            invoke(fi);

        }

        public FilterInvocationSecurityMetadataSource getSecurityMetadataSource(){
            return this.securityMetadataSource;
        }

        public Class<? extends Object> getSecureObjectClass(){
            return FilterInvocation.class;
        }

        public void invoke(FilterInvocation fi) throws IOException, ServletException{
            InterceptorStatusToken token = super.beforeInvocation(fi);
            try{
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            }finally{
                super.afterInvocation(token, null);
            }
        }

        @Override
        public SecurityMetadataSource obtainSecurityMetadataSource(){
            return this.securityMetadataSource;
        }

        public void destroy(){
            // TODO Auto-generated method stub
        }

        public void init(FilterConfig filterconfig) throws ServletException{
            // TODO Auto-generated method stub
        }

    }
}
