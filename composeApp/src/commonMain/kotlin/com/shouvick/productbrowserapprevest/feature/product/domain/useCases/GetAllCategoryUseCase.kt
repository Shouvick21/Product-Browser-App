package com.shouvick.productbrowserapprevest.feature.product.domain.useCases

import com.shouvick.productbrowserapprevest.feature.product.data.models.Category
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import io.ktor.client.call.body
import io.ktor.http.isSuccess

class GetAllCategoryUseCase(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): List<Category> {

        val response = repository.getAllCategory()

        if (response.status.isSuccess()) {

            return response.body<List<Category>>()

        } else {
            throw Exception("Failed to load categories")
        }
    }
}