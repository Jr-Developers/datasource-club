package io.github.jr.developers.club.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "io.github.jr.developers.club.jpa.club", // 해당 설정이 적용될 JPA Package
        entityManagerFactoryRef = "clubEntityManagerFactory",
        transactionManagerRef = "clubTransactionManager"
)
@EnableTransactionManagement
public class ClubDataSourceConfig {
    @Bean
    @Primary // 메인으로 사용될 DataSource
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties clubDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary // 메인으로 사용될 DataSource
    @ConfigurationProperties("spring.datasource1.hikari")
    public HikariDataSource clubDataSource() {
        return clubDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Bean
    @Primary // 메인으로 사용될 DataSource
    public LocalContainerEntityManagerFactoryBean clubEntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
//        Properties props = new Properties();  // Properties에 Hibernate Config 설정 추가
//        props.setProperty("hibernate.format_sql", String.valueOf(true));
//        props.setProperty("hibernate.hbm2ddl.auto", "create");
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("io.github.jr.developers.club.jpa.club");
        factory.setDataSource(clubDataSource());
//        factory.setJpaProperties(props);
        return factory;
    }

    @Bean
    @Primary // 메인으로 사용될 DataSource
    public PlatformTransactionManager clubTransactionManager(@Qualifier("clubEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
