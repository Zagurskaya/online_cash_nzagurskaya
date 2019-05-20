package com.gmail.zagurskaya.online.cash.repository.connection;

import com.gmail.zagurskaya.online.cash.repository.properties.DatabaseProperties;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class LiguibaseConfig {
    private static Logger logger = LoggerFactory.getLogger(LiguibaseConfig.class);

    @Bean
    public SpringLiquibase liquiBase(DataSource dataSource) {
        SpringLiquibase liquiBase = new SpringLiquibase();
        liquiBase.setChangeLog("classpath:migrations/db-changelog.xml");
        liquiBase.setDataSource(dataSource);
        return liquiBase;
    }
}
