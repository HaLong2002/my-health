package com.example.myhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myhealth.core.designsystem.theme.MyHealthTheme
import com.example.myhealth.ui.MyHealthApp
import com.example.myhealth.ui.rememberMHAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val appState = rememberMHAppState()

            MyHealthTheme {
                MyHealthApp(appState)
            }
        }
    }
}