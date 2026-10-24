package com.example.mobdevvk.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class AppInfo(
    val id: String,
    val name: String,
    val developer: String,
    val description: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val icon: ImageVector,
    val iconColor: Color,
    val screenshotUrlList: List<String> = emptyList(),
)

enum class Category(val title: String) {
    APP("Приложения"),
    GAME("Игры"),
    PRODUCTIVITY("Производительность"),
    SOCIAL("Социальные сети"),
    EDUCATION("Образование"),
    MUSIC("Музыка"),
    VIDEO("Видео"),
    HEALTH("Здоровье"),
    NEWS("Новости"),
    BOOKS("Книги"),
    FINANCE("Финансы"),
    MAPS("Карты"),
    SHOPPING("Покупки"),
}
