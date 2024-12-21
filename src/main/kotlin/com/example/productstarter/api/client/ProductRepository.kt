package com.example.productstarter.api.client

import com.example.productstarter.model.ProductEntity

interface ProductRepository {

    fun save(entity: ProductEntity): ProductEntity
}
