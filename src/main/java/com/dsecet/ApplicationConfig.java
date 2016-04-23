package com.dsecet;

import com.dsecet.core.cfg.JpaConfig;
import com.dsecet.core.cfg.MongoConfig;
import com.dsecet.core.cfg.RedisConfig;
import com.dsecet.util.DetectionExpertsJob;
import com.google.common.collect.Lists;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.Session;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfig.class, excludeFilters = @Filter({
        Controller.class, Configuration.class}))
@Import({JpaConfig.class, RedisConfig.class/*, MongoConfig.class*/})
public class ApplicationConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        List<Resource> list = Lists.newArrayList();
        list.add(new ClassPathResource("/mail.properties"));
        list.add(new ClassPathResource("/persistence.properties"));
        list.add(new ClassPathResource("/system.properties"));
        //list.add(new ClassPathResource("/mongodb.properties"));
        list.add(new ClassPathResource("/redis.properties"));
        //list.add(new FileSystemResource(sysProperties().getProperty("sys.security.conf.path")));
        ppc.setLocations(list.stream().toArray(Resource[]::new));
        return ppc;
    }

    @Bean
    public static Properties sysProperties() throws IOException {
        return PropertiesLoaderUtils.loadProperties(new ClassPathResource("/system.properties"));
    }

    @Bean
    public MultipartResolver filterMultipartResolver() {
    	CommonsMultiparResolver commonsMultipartResolver = new CommonsMultiparResolver();
        try {
            Properties sysProperties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/system.properties"));
            commonsMultipartResolver.setUploadTempDir(new FileSystemResource(sysProperties.getProperty("upload.tmp.dir")));
        } catch (Exception e) {
        }
        return commonsMultipartResolver;
    }

    @Bean(name = "javaMailSender")
    public JavaMailSender configJavaMailSender() throws IOException {
        JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
        Properties jmp = new Properties();
        ResourcePatternResolver r = new PathMatchingResourcePatternResolver();
        Resource resource = r.getResource("classpath:/mail.properties");
        PropertiesLoaderUtils.fillProperties(jmp, resource);
        String defaultEncoding = jmp.getProperty("default.encoding");
        String host = jmp.getProperty("mail.host.name");
        String port = jmp.getProperty("mail.port");
        String userName = jmp.getProperty("mail.user.name");
        String password = jmp.getProperty("mail.user.password");
        javaMail.setDefaultEncoding(defaultEncoding);
        javaMail.setHost(host);
        javaMail.setPort(Integer.parseInt(port));
        javaMail.setUsername(userName);
        javaMail.setPassword(password);
        javaMail.setJavaMailProperties(jmp);
        javaMail.setSession(Session.getInstance(jmp));
        return javaMail;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws Exception {
        FreeMarkerConfigurer fmc = new FreeMarkerConfigurer();
        fmc.setDefaultEncoding("UTF-8");
        fmc.setTemplateLoaderPath("classpath:/template");
        Properties settings = new Properties();
        ResourcePatternResolver r = new PathMatchingResourcePatternResolver();
        Resource resource = r.getResource("classpath:/freemarker.properties");
        PropertiesLoaderUtils.fillProperties(settings, resource);
        fmc.setFreemarkerSettings(settings);
        return fmc;
    }

    
    @Bean
    public SchedulerFactoryBean quartz() throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(new SpringBeanJobFactory());
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setTransactionManager(transactionManager);
        //schedulerFactoryBean.setQuartzProperties(quartzProperties());
        Trigger[] triggers = {
                processMyJobTrigger().getObject()
        };
 
        schedulerFactoryBean.setTriggers(triggers);
        return schedulerFactoryBean;
    }
    
    @Bean
    public JobDetailFactoryBean processMyJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DetectionExpertsJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
 
    @Bean
    // Configure cron to fire trigger every 1 minute
    public CronTriggerFactoryBean processMyJobTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(processMyJob().getObject());
        cronTriggerFactoryBean.setCronExpression("0 */1 * * * ?");
        return cronTriggerFactoryBean;
    }
 
    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        Properties properties;
 
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to load quartz.properties", e);
        }
 
        return properties;
    }

    @Bean
    public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean() {
        return new Jackson2ObjectMapperFactoryBean();
    }
}