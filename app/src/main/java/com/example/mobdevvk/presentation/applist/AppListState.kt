package com.example.mobdevvk.presentation.applist

import androidx.compose.runtime.Immutable
import com.example.mobdevvk.domain.AppInfo

@Immutable
data class AppListState(
    val apps: List<AppInfo> = emptyList(),
)
