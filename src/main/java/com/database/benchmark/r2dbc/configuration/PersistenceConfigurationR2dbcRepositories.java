package com.database.benchmark.r2dbc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration(value = "PersistenceConfigurationR2dbcRepositories")
@EnableR2dbcRepositories(basePackages = Constants.PATH.REPOSITORY)
public class PersistenceConfigurationR2dbcRepositories {
}
