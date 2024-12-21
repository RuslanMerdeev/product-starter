package com.example.productstarter.delegate

import com.example.productstarter.api.client.ProductRepository
import com.example.productstarter.model.ProductEntity
import com.example.productstarter.variable.BRAND
import org.flowable.engine.delegate.DelegateExecution
import org.flowable.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
@Suppress("unused")
class SaveBrandDelegate(
    private val productRepository: ProductRepository,
) : JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        val brand = execution.getVariable(BRAND) as String
        productRepository.save(ProductEntity(brand = brand))
    }
}
