package org.toxos.activiti.assertions.integration.common.configuration;

import javax.sql.DataSource;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class ActivitiTestDatasourceConfiguration {

    @Bean
    public DataSource dataSource() {
        final SimpleDriverDataSource result = new SimpleDriverDataSource();
        result.setDriverClass(Driver.class);
        result.setUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000");
        result.setUsername("sa");
        result.setPassword("");
        return result;
    }

}