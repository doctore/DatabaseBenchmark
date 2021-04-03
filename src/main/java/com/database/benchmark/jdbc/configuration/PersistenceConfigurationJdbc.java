package com.database.benchmark.jdbc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration(value = "PersistenceConfigurationJdbc")
@EnableJdbcRepositories(basePackages = Constants.PATH.REPOSITORY)
public class PersistenceConfigurationJdbc {

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
