package com.zanox.rest.file.upload.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zanox.rest.file.upload.util.RestUploadConstants;

@Configuration
@EnableWebMvc
@Profile("production")
@EnableAsync()
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.zanox.rest.file.upload", excludeFilters = { @ComponentScan.Filter(Configuration.class) })
// @Import({ HibernateConfig.class})
@PropertySource({ "classpath:upload-webapp-config.properties" })
public class WebAppConfig extends WebMvcConfigurationSupport {
	
	@Value(RestUploadConstants.HIBERNATE_DIALECT)
    private String hibernateDialect;

    @Bean(name = "appProperty")
    public static PropertySourcesPlaceholderConfigurer appProperty() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public DataSource dataSource() {
    	/*BasicDataSource datasource = new BasicDataSource();
    	datasource.setDriverClassName(jdbcDriver.class.getName());
    	datasource.setUrl("jdbc:hsqldb:mem:testdb");
    	datasource.setUsername("sa");
    	datasource.setPassword("");
    	datasource.setDefaultAutoCommit(false);
    	return datasource;*/
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:user_profile_schema.sql").build();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory() throws Exception {

        Properties properties = new Properties();
        properties.put(RestUploadConstants.HIBERNATE_DIALECT_PROPERTY, hibernateDialect);
        properties.put(RestUploadConstants.HIBERNATE_SHOW_SQL_PROPERTY, "false");
        properties.put(RestUploadConstants.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS_PROPERTY, "thread");
        properties.put("dynamic-update", "true");
        properties.put("shutdown", "false");
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setPackagesToScan(new String[] { RestUploadConstants.ENTITIES_PACKAGE });
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(properties);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() throws Exception {
        return new HibernateTransactionManager(getSessionFactory());
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true).useJaf(false)
            .defaultContentType(MediaType.MULTIPART_FORM_DATA).mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("json", MediaType.APPLICATION_JSON);
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver(){
    	CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(50000000);
        return commonsMultipartResolver;
    }
    
    @Bean
    public MessageSource messageSource(){
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("upload-webapp-config");
        return messageSource;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver viewResolver() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}
