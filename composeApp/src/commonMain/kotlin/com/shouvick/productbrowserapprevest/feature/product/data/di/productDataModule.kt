package com.shouvick.productbrowserapprevest.feature.product.data.di

import com.shouvick.productbrowserapprevest.feature.product.data.apiService.ProductApiService
import com.shouvick.productbrowserapprevest.feature.product.data.repository.ProductRepositoryImpl
import com.shouvick.productbrowserapprevest.feature.product.domain.repository.ProductRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun productDataModule() = module {
    singleOf(::ProductApiService)
    singleOf(::ProductRepositoryImpl).bind<ProductRepository>()
}