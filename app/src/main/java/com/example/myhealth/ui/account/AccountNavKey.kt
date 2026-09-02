package com.example.myhealth.ui.account

import androidx.navigation3.runtime.NavKey
import com.example.myhealth.core.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
object AccountNavKey : NavKey {}

fun Navigator.navigateToAccount() {
    navigate(AccountNavKey)
}