package com.example.mobdevvk.ui.applist

import androidx.compose.runtime.Immutable
import com.example.mobdevvk.data.AppInfo

@Immutable
data class AppListState(
    val apps: List<AppInfo> = emptyList(),
)
