package com.shouvick.productbrowserapprevest.feature.product.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    val width: Double? = null,
    val height: Double? = null,
    val depth: Double? = null
)
