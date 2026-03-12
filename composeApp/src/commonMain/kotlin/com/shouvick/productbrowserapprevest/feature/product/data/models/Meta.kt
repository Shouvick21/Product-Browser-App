package com.shouvick.productbrowserapprevest.feature.product.data.models
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val barcode: String? = null,
    val qrCode: String? = null
)