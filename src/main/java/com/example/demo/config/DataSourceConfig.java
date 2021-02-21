package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource mainDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "jobRepositoryDataSource")
	@BatchDataSource
	@ConfigurationProperties("spring.batch.datasource")
	public DataSource jobRepositoryDataSource() {
	    return DataSourceBuilder.create().build();
	}

}
