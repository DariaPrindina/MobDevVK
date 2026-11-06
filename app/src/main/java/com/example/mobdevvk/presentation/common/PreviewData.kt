package com.example.mobdevvk.presentation.common

import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.Category

val previewApp = AppInfo(
    id = "heroes",
    name = "Гильдия Героев",
    developer = "VK Play",
    description = "Экшен ММО РПГ: собери команду и сражайся с боссами",
    category = Category.GAME,
    ageRating = 12,
    size = 223.7f,
    iconName = "game",
    iconColor = 0xFF8E44AD,
    screenshotUrlList = listOf(
        "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
    ),
)

val previewApps = List(9) { index -> previewApp.copy(id = "app-$index", name = "Приложение ${index + 1}") }
