package com.example.mobdevvk.ui.applist

sealed interface AppListEvent {
    data object ShowToolbarMessage : AppListEvent
}
