package com.shouvick.productbrowserapprevest.feature.product.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouvick.productbrowserapprevest.feature.product.data.models.Category
import com.shouvick.productbrowserapprevest.feature.product.data.models.Product
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetAllCategoryUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetAllProductsUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetProductsByCategoryUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.SearchProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeState(
    val allProducts: List<Product>? = null,
    val searchValue: String = "",
    val allCategory: List<Category>? = null,
    val selectedCategory: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class HomeViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object{
        const val SEARCH_VALUE = "searchValue"
        const val SELECTED_CATEGORY = "selectedCategory"
    }

    private val _state = MutableStateFlow(HomeState(
        allProducts = null,
        searchValue =  savedStateHandle[SEARCH_VALUE] ?:"" ,
        allCategory = null,
        selectedCategory = savedStateHandle[SELECTED_CATEGORY] ?: ""
    ))
    val state = _state.asStateFlow()

    init {
        getAllProduct()
        getAllCategory()
        observeSearch()
    }

    fun updateSearchValue(value: String) {
        savedStateHandle[SEARCH_VALUE] = value
        _state.update {
            it.copy(searchValue = value)
        }
    }

    fun updateSelectedCategory(value: String) {
        savedStateHandle[SELECTED_CATEGORY] = value
        _state.update {
            it.copy(selectedCategory = value)
        }

        if (value.isBlank()) {
            getAllProduct()
        } else {
            getProductByCategory()
        }
    }

    private fun observeSearch() {
        viewModelScope.launch {
            _state
                .map { it.searchValue }
                .debounce(1000)
                .distinctUntilChanged()
                .collect { query ->

                    if (query.isBlank()) {
                        getAllProduct()
                    } else {
                        getSearchProduct()
                    }

                }

        }
    }


    fun getAllProduct() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {

                val products = getAllProductsUseCase()

                _state.update {
                    it.copy(
                        allProducts = products,
                        isLoading = false,
                        errorMessage = null
                    )
                }

            } catch (e: Exception) {

                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }

            }
        }
    }


    fun getSearchProduct() {
        viewModelScope.launch {

            try {

                val products = searchProductsUseCase(_state.value.searchValue)

                _state.update {
                    it.copy(allProducts = products)
                }

            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = e.message) }
            }
        }
    }

    fun getAllCategory() {

        viewModelScope.launch {

            _state.update { it.copy(isLoading = true) }

            try {

                val categories = getAllCategoryUseCase()

                _state.update {
                    it.copy(
                        allCategory = categories,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {

                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message
                    )
                }

            }

        }
    }

    fun getProductByCategory() {
        viewModelScope.launch {

            try {

                val products =
                    getProductsByCategoryUseCase(_state.value.selectedCategory)

                _state.update {
                    it.copy(allProducts = products)
                }

            } catch (e: Exception) {
                _state.update { it.copy(errorMessage = e.message) }
            }
        }
    }

}