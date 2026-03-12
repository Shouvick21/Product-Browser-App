package com.shouvick.productbrowserapprevest.core.network.di

import com.shouvick.productbrowserapprevest.core.network.HttpClientFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun coreNetworkModule(): Module {
    return module {
        single { HttpClientFactory.create(OkHttp.create()) }

    }
}