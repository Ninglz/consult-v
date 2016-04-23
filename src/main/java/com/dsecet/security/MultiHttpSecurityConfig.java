package com.dsecet.security;

import com.dsecet.core.service.admin.StaffService;
import com.dsecet.util.StaffPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

import javax.annotation.Resource;

/**
 * User: lxl Date: 14-8-14
 */
@Configuration
@EnableWebSecurity
public class MultiHttpSecurityConfig{
    @Configuration
    @Order(1)
    public static class UConfigurationAdapter extends
            WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN))
            .and().antMatcher("/resources/**").csrf().disable()
            .antMatcher("/alipay/**").csrf().disable();
        }
    }
    @Configuration
    @Order(2)
    public static class ApiSecurityConfigurationAdapter extends
            WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception{
        	http.antMatcher("/api/**").csrf().disable();
            //http.antMatcher("/news/**").antMatcher("/share/**").csrf().disable();
        }
    }

    @Configuration
    @Order(3)
    public static class AdminWebSecurityConfigurationAdapter extends
            WebSecurityConfigurerAdapter{

        @Autowired
        private AdminUserDetailService adminUserDetailService;

        @Autowired
        private StaffService staffService;

        @Resource(name = "staffSaltSource")
        public SaltSource staffSaltSource;

        @Autowired
        private InvocationSecurityMetadataSourceService securityMetadataSource;

        @Override
        protected void configure(AuthenticationManagerBuilder auth)
                throws Exception{
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            // using the same password encrypt way as customer.
            StaffPasswordEncoder encoder = new StaffPasswordEncoder();
            provider.setPasswordEncoder(encoder);
            provider.setSaltSource(staffSaltSource);
            provider.setUserDetailsService(adminUserDetailService);
            auth.authenticationProvider(provider);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception{
            return super.authenticationManagerBean();
        }

        @Bean
        public AdminAccessDecisionManager adminAccessDecisionManagerBean() throws Exception{
            return new AdminAccessDecisionManager();
        }

        @Bean
        public AdminSecurityFilter adminSecurityFilterBean() throws Exception{
            return new AdminSecurityFilter(securityMetadataSource, authenticationManagerBean(), adminAccessDecisionManagerBean());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
        	http.  
            authorizeRequests()
                    .antMatchers("/admin/error/**").permitAll()
                    .antMatchers("/admin/**").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/admin/login")
                    .failureUrl("/admin/login?error=ture")
                    .successHandler(new AdminAuthenticationSuccessHandler(staffService, "/admin/common/signed", true))
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/admin/login")
                    .and()
                    .addFilterBefore(adminSecurityFilterBean(), FilterSecurityInterceptor.class)
                    .exceptionHandling().accessDeniedPage("/admin/error/403");

        }
    }

    @Configuration
    @Order(4)
    public static class CallBackSecurityConfigurationAdapter extends
            WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.antMatcher("/news/**").antMatcher("/share/**").csrf().disable();
        }
    }
    
}
