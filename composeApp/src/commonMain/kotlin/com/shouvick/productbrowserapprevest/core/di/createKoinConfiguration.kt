package com.shouvick.productbrowserapprevest.core.di

import com.shouvick.productbrowserapprevest.core.network.di.coreNetworkModule
import com.shouvick.productbrowserapprevest.feature.product.data.di.productDataModule
import com.shouvick.productbrowserapprevest.feature.product.domain.di.productDomainModule
import com.shouvick.productbrowserapprevest.feature.product.ui.di.productUiModule
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.koinConfiguration

fun createKoinConfiguration(): KoinConfiguration = koinConfiguration {
    modules(
        coreNetworkModule(),
        productDataModule(), productUiModule(), productDomainModule()
    )
}