package com.shouvick.productbrowserapprevest.feature.product.domain.di

import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetAllCategoryUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetAllProductsUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetProductsByCategoryUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.GetSingleProductUseCase
import com.shouvick.productbrowserapprevest.feature.product.domain.useCases.SearchProductsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun productDomainModule() = module {
    singleOf(::GetAllProductsUseCase)
    singleOf(::GetAllCategoryUseCase)
    singleOf(::GetProductsByCategoryUseCase)
    singleOf(::SearchProductsUseCase)
    singleOf(::GetSingleProductUseCase)
}