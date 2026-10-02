package com.example.myhealth.ui.home.navigation

import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object HomeNavKey : NavKey {}

fun Navigator.navigateToHome() {
    navigate(HomeNavKey)
}