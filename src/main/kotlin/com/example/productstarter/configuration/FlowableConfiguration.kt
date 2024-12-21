package com.example.productstarter.configuration

import org.flowable.engine.ProcessEngine
import org.flowable.engine.ProcessEngineConfiguration
import org.flowable.engine.RuntimeService
import org.flowable.spring.ProcessEngineFactoryBean
import org.flowable.spring.SpringProcessEngineConfiguration
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@Suppress("unused")
class FlowableConfiguration {

    @Bean
    fun transactionManager(dataSource: DataSource): PlatformTransactionManager {
        val transactionManager = DataSourceTransactionManager()
        transactionManager.dataSource = dataSource
        return transactionManager
    }

    @Bean
    fun processEngineConfiguration(
        transactionManager: PlatformTransactionManager,
        dataSource: DataSource,
        context: ConfigurableApplicationContext,
    ): SpringProcessEngineConfiguration {
        val cfg = SpringProcessEngineConfiguration()
        cfg.deploymentResources =
            PathMatchingResourcePatternResolver().getResources("classpath*:processes/*.bpmn20.xml")
        cfg.deploymentMode = "single-resource"
        cfg.dataSource = dataSource
        cfg.transactionManager = transactionManager
        cfg.databaseSchemaUpdate = ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE
        cfg.applicationContext = context
        return cfg
    }

    @Bean
    fun processEngine(processEngineConfiguration: SpringProcessEngineConfiguration): ProcessEngineFactoryBean {
        val processEngineFactoryBean = ProcessEngineFactoryBean()
        processEngineFactoryBean.processEngineConfiguration = processEngineConfiguration
        return processEngineFactoryBean
    }

    @Bean
    fun runtimeService(processEngine: ProcessEngine): RuntimeService = processEngine.runtimeService
}
