package com.example.mobdevvk.presentation.common

import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.Category

val previewApp = AppInfo(
    id = "quicknote",
    name = "QuickNote",
    description = "Лёгкое приложение для заметок и напоминаний",
    category = Category.PRODUCTIVITY,
    iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
)

val previewApps = List(9) { index -> previewApp.copy(id = "app-$index", name = "Приложение ${index + 1}") }
