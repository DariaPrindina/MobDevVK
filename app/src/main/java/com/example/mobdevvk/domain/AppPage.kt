package com.example.mobdevvk.domain

data class AppPage(
    val app: AppInfo,
    val similarApps: List<AppInfo>,
    val showcaseApps: List<AppInfo>,
)
