package com.example.mobdevvk.presentation.applist

import androidx.compose.runtime.Immutable
import com.example.mobdevvk.domain.AppInfo

@Immutable
sealed interface AppListState {
    data object Loading : AppListState
    data object Error : AppListState
    data class Content(
        val apps: List<AppInfo>,
        val ratings: Map<String, Float> = emptyMap(),
        val ratingsLoading: Boolean = false,
        val ratingsResult: RatingsResult? = null,
    ) : AppListState
}

@Immutable
data class RatingsResult(
    val mode: RatingsLoadMode,
    val timeMillis: Long,
)
