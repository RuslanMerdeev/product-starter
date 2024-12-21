package com.example.productstarter.api.client

import com.example.productstarter.api.model.ProductResponse

interface ProductConfigClient {

    fun fetchBy(productId: String): ProductResponse
}
