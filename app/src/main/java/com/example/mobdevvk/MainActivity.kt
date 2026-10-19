package com.example.mobdevvk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobdevvk.data.apps
import com.example.mobdevvk.ui.applist.AppListScreen
import com.example.mobdevvk.ui.theme.MobDevVKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobDevVKTheme {
                AppListScreen(apps = apps)
            }
        }
    }
}
