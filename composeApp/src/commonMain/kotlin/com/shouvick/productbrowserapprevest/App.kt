package com.shouvick.productbrowserapprevest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shouvick.productbrowserapprevest.core.di.createKoinConfiguration
import com.shouvick.productbrowserapprevest.navigation.Navigation
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinMultiplatformApplication

import productbrowserapprevest.composeapp.generated.resources.Res
import productbrowserapprevest.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    KoinMultiplatformApplication(
        config = createKoinConfiguration()
    ){
        Navigation()
    }

}