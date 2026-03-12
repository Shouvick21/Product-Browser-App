package com.shouvick.productbrowserapprevest.feature.product.domain.repository

import io.ktor.client.statement.HttpResponse

interface ProductRepository {
    suspend fun getAllProducts(): HttpResponse
    suspend fun getSelectedProduct(id: Int): HttpResponse
    suspend fun getSearchProduct(searchValue: String): HttpResponse
    suspend fun getAllCategory(): HttpResponse

    suspend fun getProductByCategory(category: String): HttpResponse


}