package com.example.productstarter.service

import com.example.productstarter.variable.PRODUCT_ID
import com.example.productstarter.variable.PRODUCT_PROCESS_KEY
import org.flowable.engine.RuntimeService
import org.springframework.stereotype.Service

@Service
class ProductProcessService(
    private val runtimeService: RuntimeService,
) {

    fun startWith(productId: String) {
        runtimeService.startProcessInstanceByKey(PRODUCT_PROCESS_KEY, mapOf(PRODUCT_ID to productId))
    }
}
