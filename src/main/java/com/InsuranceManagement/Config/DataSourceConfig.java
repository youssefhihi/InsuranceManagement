package com.InsuranceManagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    private static final DriverManagerDataSource  dataSource;

    static {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/InsuranceManagement");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
    }

    @Bean
    public DataSource getDataSource() {
        return dataSource;
    }

}
