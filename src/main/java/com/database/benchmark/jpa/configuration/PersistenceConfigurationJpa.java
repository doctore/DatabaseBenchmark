package com.database.benchmark.jpa.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration(value = "PersistenceConfigurationJpa")
@EnableJpaRepositories(
        basePackages = Constants.PATH.REPOSITORY,
        entityManagerFactoryRef = PersistenceConfigurationJpa.ENTITY_MANAGER_FACTORY,
        transactionManagerRef= PersistenceConfigurationJpa.TRANSACTION_MANAGER)
public class PersistenceConfigurationJpa {

    public static final String DATASOURCE = "DataSourceJpa";
    public static final String ENTITY_MANAGER_FACTORY = "EntityManagerFactoryJpa";
    public static final String TRANSACTION_MANAGER = "TransactionManagerJpa";

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Primary
    @Bean(name = DATASOURCE)
    public DataSource dataSourceJpa() {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean EntityManagerFactoryJpa(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSourceJpa())
                .packages(Constants.PATH.MODEL)
                .persistenceUnit("persistenceUnitJpa")
                .build();
    }

    @Primary
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManagerJpa(
            final @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

}