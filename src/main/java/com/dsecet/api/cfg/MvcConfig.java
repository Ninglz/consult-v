package com.dsecet.api.cfg;

import com.dsecet.AbstractMvcConfig;
import com.dsecet.api.controller.RespondableExceptionResolver;
import com.dsecet.api.security.SessionToken;
import com.dsecet.api.security.SessionTokenFactory;
import com.dsecet.api.security.SessionTokenInterceptor;
import com.dsecet.api.security.SessionTokenValidator;
import com.dsecet.core.service.RedisService;
import com.dsecet.foundation.model.TokenFactory;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;
import java.util.Properties;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = "com.dsecet.api", includeFilters = @Filter({Controller.class}), useDefaultFilters = false)
public class MvcConfig extends AbstractMvcConfig{

    @Autowired
    private RedisService redisService;

    @Override
    protected String resourceContext(){
        return Constants.SERVLET_NAME;
    }

    @Autowired
    @Qualifier("sysProperties")
    private Properties properties;

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        SessionTokenInterceptor tokenInterceptor = new SessionTokenInterceptor();
        tokenInterceptor.setSessionTokenValidator(configSessionTokenValidator());
        tokenInterceptor.setTokenFactory(configTokenFactory());
        tokenInterceptor.setExpiredTime(Long.parseLong((String)properties.get("session.token.expired")));
        List<String> interceptorUrls = Lists.newArrayList();
        interceptorUrls.add("/security/**");
        List<String> excludeUrls = Lists.newArrayList();
        excludeUrls.add("/insecure/**");
        excludeUrls.add("/version/**");

        registry.addInterceptor(tokenInterceptor).addPathPatterns(interceptorUrls.stream().toArray(String[]::new)).excludePathPatterns(excludeUrls.stream().toArray(String[]::new));

        super.addInterceptors(registry);
    }

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers){
        RespondableExceptionResolver respondableExceptionResolver = new RespondableExceptionResolver();

        exceptionResolvers.add(respondableExceptionResolver);

        super.configureHandlerExceptionResolvers(exceptionResolvers);
    }

    @Bean
    public MessageSource messageSource(){
        return super.messageSource();
    }

    @Bean(name = "configTokenFactory")
    public TokenFactory<Long> configTokenFactory(){
        return new SessionTokenFactory(redisService);
    }

    @Bean
    public SessionTokenValidator configSessionTokenValidator(){
        return new SessionTokenValidator();
    }
}
