package com.shouvick.productbrowserapprevest.feature.product.data.repository

import com.shouvick.productbrowserapprevest.feature.product.data.apiService.ProductApiService
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import io.ktor.client.statement.HttpResponse

class ProductRepositoryImpl(
    private val apiService: ProductApiService
) : ProductRepository {
    override suspend fun getAllProducts(): HttpResponse {
        return apiService.getAllProduct()
    }

    override suspend fun getSelectedProduct(id: Int): HttpResponse {
        return apiService.getSelectedProduct(id)
    }

    override suspend fun getSearchProduct(searchValue: String): HttpResponse {
        return apiService.getSearchProduct(searchValue)
    }

    override suspend fun getAllCategory(): HttpResponse {
        return apiService.getAllCategory()
    }

    override suspend fun getProductByCategory(category: String): HttpResponse {
        return apiService.getProductByCategory(category)
    }
}