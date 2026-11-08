package com.example.mobdevvk.presentation.appdetails

import androidx.compose.runtime.Immutable
import com.example.mobdevvk.domain.AppInfo

@Immutable
sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data object Error : AppDetailsState
    data class Content(
        val app: AppInfo,
        val similarApps: List<AppInfo>,
        val showcaseApps: List<AppInfo>,
        val descriptionCollapsed: Boolean = false,
    ) : AppDetailsState
}
