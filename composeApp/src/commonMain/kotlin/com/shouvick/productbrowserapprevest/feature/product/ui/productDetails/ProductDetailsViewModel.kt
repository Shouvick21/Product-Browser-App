package com.shouvick.productbrowserapprevest.feature.product.ui.productDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouvick.productbrowserapprevest.core.logging.Log
import com.shouvick.productbrowserapprevest.feature.product.data.models.Product
import com.shouvick.productbrowserapprevest.feature.product.data.models.ProductResponse
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetSingleProductUseCase
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductDetailsState(
    val productId: Int?,
    val product: Product?
)

class ProductDetailsViewModel(
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(
        ProductDetailsState(
            productId = savedStateHandle["id"],
            product = null
        )
    )
    val state = _state.asStateFlow()

    init {
        getSingleProduct()
    }

    fun getSingleProduct() {

        viewModelScope.launch {

            try {

                _state.value.productId?.let { id ->

                    val product = getSingleProductUseCase(id)

                    _state.update {
                        it.copy(product = product)
                    }

                }

            } catch (e: Exception) {

                Log.d("tag", "message error -> $e")

            }

        }

    }

}