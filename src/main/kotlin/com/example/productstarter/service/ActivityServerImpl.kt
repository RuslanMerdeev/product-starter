package com.example.productstarter.service

import com.example.productstarter.api.server.ActivityServer
import org.springframework.stereotype.Service

@Service
@Suppress("unused")
class ActivityServerImpl(
    private val productProcessService: ProductProcessService,
    private val productConfigService: ProductConfigService,
) : ActivityServer {

    override fun activate(productId: String) = productProcessService.startWith(productId)

    override fun product(productId: String) = productConfigService.fetchBy(productId)
}
