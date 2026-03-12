package com.shouvick.productbrowserapprevest.feature.product.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val slug: String? = null,
    val name: String? = null,
    val url: String? = null
)