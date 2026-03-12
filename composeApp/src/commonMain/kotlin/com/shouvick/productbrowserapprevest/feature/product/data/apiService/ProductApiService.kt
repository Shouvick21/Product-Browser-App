package com.shouvick.productbrowserapprevest.feature.product.data.apiService

import com.shouvick.productbrowserapprevest.core.logging.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class ProductApiService(
    private val httpClient: HttpClient
) {
    suspend fun getAllProduct(): HttpResponse {
        val response = httpClient.get("/products")
        return response
    }

    suspend fun getSelectedProduct(id: Int): HttpResponse {
        val response = httpClient.get("/products/$id")
        return response
    }

    suspend fun getSearchProduct(searchValue: String): HttpResponse {
        val response = httpClient.get("/products/search") {
            parameter("q", searchValue)
        }
        return response
    }

    suspend fun getAllCategory(): HttpResponse {
        val response = httpClient.get("products/categories") {
        }
        return response
    }

    suspend fun getProductByCategory(category: String): HttpResponse {
        val response = httpClient.get("products/category/$category")
        return response
    }

}