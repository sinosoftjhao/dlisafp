package com.dlis.afp.afp.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpirngJDBConfig {
        @Value(value="${spring.datasource.driver-class-name}")
        private String driverClassName;
        @Value(value="${spring.datasource.url}")
        private String url;
        @Value(value="${spring.datasource.username}")
        private String username;
        @Value(value="${spring.datasource.password}")
        private String password;

        @Bean
        public DriverManagerDataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            return jdbcTemplate;
        }
}
