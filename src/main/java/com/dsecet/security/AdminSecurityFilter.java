package com.dsecet.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author: lxl
 */

public class AdminSecurityFilter extends AbstractSecurityInterceptor implements Filter{

    private InvocationSecurityMetadataSourceService securityMetadataSource;

    public AdminSecurityFilter(InvocationSecurityMetadataSourceService securityMetadataSource,
                               AuthenticationManager authenticationManager,
                               AccessDecisionManager accessDecisionManager
                               ) {
        this.securityMetadataSource = securityMetadataSource;
        setAuthenticationManager(authenticationManager);
        setAccessDecisionManager(accessDecisionManager);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);

    }

    public Class<? extends Object> getSecureObjectClass(){
        return FilterInvocation.class;
    }

    public void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
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
