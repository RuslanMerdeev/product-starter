package com.example.productstarter.delegate

import com.example.productstarter.service.ProductConfigService
import com.example.productstarter.variable.BRAND
import com.example.productstarter.variable.PRODUCT_ID
import org.flowable.engine.delegate.DelegateExecution
import org.flowable.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class GetBrandDelegate(
    private val productConfigService: ProductConfigService,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val productId = execution.getVariable(PRODUCT_ID) as String
        val brand = productConfigService.fetchBy(productId).brand
        execution.setVariable(BRAND, brand)
    }
}
