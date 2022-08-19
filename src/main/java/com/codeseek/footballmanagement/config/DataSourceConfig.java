package com.codeseek.footballmanagement.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

import static com.codeseek.footballmanagement.util.ConstantClass.HIBERNATE_DIALECT;
import static com.codeseek.footballmanagement.util.ConstantClass.PACKAGE_TO_SCAN;

@Configuration
public class DataSourceConfig {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${hibernate.datasource.url}")
    private String databaseConnectionUrl;

    @Value("${hibernate.datasource.username}")
    private String databaseConnectionUsername;

    @Value("${hibernate.datasource.password}")
    private String databaseConnectionPassword;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseConnectionUrl);
        dataSource.setUsername(databaseConnectionUsername);
        dataSource.setPassword(databaseConnectionPassword);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());

        return hibernateTransactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(HIBERNATE_DIALECT, hibernateDialect);
        return hibernateProperties;
    }
}