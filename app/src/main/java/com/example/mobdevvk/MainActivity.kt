package com.example.mobdevvk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobdevvk.ui.navigation.AppNavHost
import com.example.mobdevvk.ui.theme.MobDevVKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobDevVKTheme {
                AppNavHost()
            }
        }
    }
}
