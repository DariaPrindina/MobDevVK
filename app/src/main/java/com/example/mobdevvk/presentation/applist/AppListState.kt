package com.example.mobdevvk.presentation.applist

import androidx.compose.runtime.Immutable
import com.example.mobdevvk.domain.AppInfo

@Immutable
sealed interface AppListState {
    data object Loading : AppListState
    data object Error : AppListState
    data class Content(val apps: List<AppInfo>) : AppListState
}
