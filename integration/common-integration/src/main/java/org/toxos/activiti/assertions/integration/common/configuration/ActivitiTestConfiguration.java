package org.toxos.activiti.assertions.integration.common.configuration;

import javax.sql.DataSource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
// @Import({ ActivitiTestDatasourceConfiguration.class })
@ComponentScan(basePackageClasses = { ActivitiTestConfiguration.class })
public class ActivitiTestConfiguration {

    @Autowired
    private DataSource dataSource;

    private static final String DIAGRAMS_BPMN = "diagrams/*.bpmn";

    @Value("classpath*:" + DIAGRAMS_BPMN)
    private Resource[] diagramResources;

    // @Bean
    // public DataSource dataSource() {
    // final SimpleDriverDataSource result = new SimpleDriverDataSource();
    // result.setDriverClass(Driver.class);
    // result.setUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000");
    // result.setUsername("sa");
    // result.setPassword("");
    // return result;
    // }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager result = new DataSourceTransactionManager();
        result.setDataSource(dataSource);
        return result;
    }

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        final SpringProcessEngineConfiguration result = new SpringProcessEngineConfiguration();
        result.setDataSource(dataSource);
        result.setTransactionManager(transactionManager());
        result.setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_TRUE);
        result.setJobExecutorActivate(false);
        result.setHistory(HistoryLevel.FULL.getKey());
        result.setDeploymentResources(diagramResources);
        return result;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() {
        final ProcessEngineFactoryBean result = new ProcessEngineFactoryBean();
        result.setProcessEngineConfiguration(processEngineConfiguration());
        return result;
    }

    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngine().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getObject().getTaskService();
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getObject().getHistoryService();
    }

    @Bean
    public ManagementService managementService() throws Exception {
        return processEngine().getObject().getManagementService();
    }

    @Bean
    public ActivitiRule activitiRule() throws Exception {
        final ActivitiRule result = new ActivitiRule();
        result.setProcessEngine(processEngine().getObject());
        return result;
    }
}