package com.shouvick.productbrowserapprevest.feature.product.ui.di

import com.shouvick.productbrowserapprevest.feature.product.data.apiService.ProductApiService
import com.shouvick.productbrowserapprevest.feature.product.data.repository.ProductRepositoryImpl
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import com.shouvick.productbrowserapprevest.feature.product.ui.home.HomeViewModel
import com.shouvick.productbrowserapprevest.feature.product.ui.productDetails.ProductDetailsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun productUiModule() = module {
  viewModelOf(::HomeViewModel)
  viewModelOf(::ProductDetailsViewModel)
}