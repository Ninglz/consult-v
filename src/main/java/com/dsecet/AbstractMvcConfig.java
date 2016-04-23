package com.dsecet;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public abstract class AbstractMvcConfig extends WebMvcConfigurationSupport{

    private static final String BASE_MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
    private static final String BASE_RESOURCES_LOCATION = "/resources/";
    private static final String BASE_RESOURCES_HANDLER = BASE_RESOURCES_LOCATION + "**";

    private static final String MESSAGE_SOURCE = "/WEB-INF/%s/i18n/messages";
    private static final String VIEWS = "/WEB-INF/%s/views/";
    private static final String RESOURCES_LOCATION = "/resources/%s/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    protected abstract String resourceContext();

    public ViewResolver jspResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(String.format(VIEWS, resourceContext()));
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
        return requestMappingHandlerMapping;
    }

    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(BASE_MESSAGE_SOURCE, String.format(MESSAGE_SOURCE, resourceContext()));
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Override
    public Validator getValidator(){
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/alipay/**",BASE_RESOURCES_HANDLER, String.format(RESOURCES_HANDLER, resourceContext()))
                .addResourceLocations("/alipay/",BASE_RESOURCES_LOCATION, String.format(RESOURCES_LOCATION, resourceContext()))
                .setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer config){
        config.enable();
    }

}
