package com.shouvick.productbrowserapprevest.feature.product.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ProductResponse(
    val products: List<Product>? = null,
    val total: Int? = null,
    val skip: Int? = null,
    val limit: Int? = null
)