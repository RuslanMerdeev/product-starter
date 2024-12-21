package com.example.productstarter.service

import com.example.productstarter.api.client.ProductConfigClient
import org.springframework.stereotype.Service

@Service
class ProductConfigService(
    private val client: ProductConfigClient,
) {

    fun fetchBy(productId: String) = client.fetchBy(productId)
}
