package com.example.productstarter.api.client

import com.example.productstarter.api.dto.ProductDto

interface ProductConfigClient {

    fun fetchBy(productId: String): ProductDto
}
