package com.shouvick.productbrowserapprevest.feature.product.domain.useCases

import com.shouvick.productbrowserapprevest.feature.product.data.models.Product
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class GetSingleProductUseCase(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(productId: Int): Product {

        val response = repository.getSelectedProduct(productId)

        if (response.status.isSuccess()) {

            return response.body<Product>()

        } else {
            throw Exception("Failed to load product")
        }
    }
}