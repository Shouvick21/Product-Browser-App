package com.shouvick.productbrowserapprevest.feature.product.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val rating: Int? = null,
    val comment: String? = null,
    val date: String? = null,
    val reviewerName: String? = null,
    val reviewerEmail: String? = null
)