package com.example.productstarter.api.server

import com.example.productstarter.api.model.ProductResponse

interface ActivityServer {

    fun activate(productId: String)

    fun product(productId: String): ProductResponse
}
