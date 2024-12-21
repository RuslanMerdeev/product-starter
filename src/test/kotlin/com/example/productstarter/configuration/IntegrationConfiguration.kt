package com.example.productstarter.configuration

import com.example.productstarter.api.client.ProductConfigClient
import com.example.productstarter.api.client.ProductRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean

@TestConfiguration
@Suppress("unused")
class IntegrationConfiguration {

    @MockBean lateinit var productConfigClient: ProductConfigClient
    @MockBean lateinit var productRepository: ProductRepository
}
