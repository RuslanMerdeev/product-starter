package com.example.productstarter.configuration

import com.example.productstarter.api.server.ActivityServer
import com.example.productstarter.service.ActivityServerImpl
import com.example.productstarter.service.ProductConfigService
import com.example.productstarter.service.ProductProcessService
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Suppress("unused")
class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun activityServer(
        productProcessService: ProductProcessService,
        productConfigService: ProductConfigService,
    ): ActivityServer = ActivityServerImpl(productProcessService, productConfigService)
}
