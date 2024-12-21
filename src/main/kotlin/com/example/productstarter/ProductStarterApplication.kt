package com.example.productstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductStarterApplication

fun main(args: Array<String>) {
    runApplication<ProductStarterApplication>(*args)
}
