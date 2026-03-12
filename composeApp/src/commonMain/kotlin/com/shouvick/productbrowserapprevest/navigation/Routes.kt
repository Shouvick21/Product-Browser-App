package com.shouvick.productbrowserapprevest.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object Home : Routes()

    @Serializable
    data class Details(val id : Int) : Routes()

}