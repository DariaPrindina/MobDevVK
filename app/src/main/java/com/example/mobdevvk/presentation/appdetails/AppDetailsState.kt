package com.example.mobdevvk.presentation.appdetails

import androidx.compose.runtime.Immutable
import com.example.mobdevvk.domain.AppInfo

@Immutable
data class AppDetailsState(
    val app: AppInfo,
    val similarApps: List<AppInfo>,
    val showcaseApps: List<AppInfo>,
    val descriptionCollapsed: Boolean = false,
)
