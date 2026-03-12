package com.shouvick.productbrowserapprevest.feature.product.domain.useCases

import com.shouvick.productbrowserapprevest.feature.product.data.models.Product
import com.shouvick.productbrowserapprevest.feature.product.data.models.ProductResponse
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class GetProductsByCategoryUseCase(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(category: String): List<Product> {

        val response = repository.getProductByCategory(category)

        if (response.status.isSuccess()) {

            return response
                .body<ProductResponse>()
                .products
                ?: emptyList()

        } else {
            throw Exception("Category fetch failed")
        }
    }
}