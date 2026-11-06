package com.example.mobdevvk.presentation.applist

sealed interface AppListEvent {
    data object ShowToolbarMessage : AppListEvent
}
