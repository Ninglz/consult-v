package com.dsecet.admin.cfg;

import com.dsecet.AbstractMvcConfig;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;

import java.util.List;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = "com.dsecet.admin", includeFilters = @Filter(Controller.class), useDefaultFilters = false)
@Import(AopConfig.class)
public class MvcConfig extends AbstractMvcConfig{

    @Override
    protected String resourceContext(){
        return Constants.SERVLET_NAME;
    }

    @Bean(name = Constants.SERVLET_NAME + "ViewResolver")
    public ViewResolver jspResolver(){
        return super.jspResolver();
    }

    @Bean
    public MessageSource messageSource(){
        return super.messageSource();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(sortResolver());
        argumentResolvers.add(pageableResolver());
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        return new PageableHandlerMethodArgumentResolver(sortResolver());
    }

    @Bean
    public SortHandlerMethodArgumentResolver sortResolver() {
        return new SortHandlerMethodArgumentResolver();
    }
}
