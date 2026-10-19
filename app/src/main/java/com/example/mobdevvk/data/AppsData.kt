package com.example.mobdevvk.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.ui.graphics.Color

val apps = listOf(
    AppInfo(
        id = "vk",
        name = "ВКонтакте",
        developer = "VK",
        description = "Общение с друзьями, сообщества, музыка и видео в одном приложении",
        category = Category.SOCIAL,
        ageRating = 12,
        size = 152.4f,
        icon = Icons.Filled.Groups,
        iconColor = Color(0xFF0077FF),
    ),
    AppInfo(
        id = "vk-music",
        name = "VK Музыка",
        developer = "VK",
        description = "Миллионы треков, подкасты и персональные рекомендации",
        category = Category.MUSIC,
        ageRating = 12,
        size = 98.1f,
        icon = Icons.Filled.MusicNote,
        iconColor = Color(0xFFFF3985),
    ),
    AppInfo(
        id = "vk-video",
        name = "VK Видео",
        developer = "VK",
        description = "Фильмы, сериалы, шоу и прямые трансляции",
        category = Category.VIDEO,
        ageRating = 16,
        size = 87.6f,
        icon = Icons.Filled.PlayCircle,
        iconColor = Color(0xFF2C2D2E),
    ),
    AppInfo(
        id = "mail",
        name = "Почта Mail",
        developer = "VK",
        description = "Быстрая и безопасная почта со всеми ящиками в одном месте",
        category = Category.PRODUCTIVITY,
        ageRating = 0,
        size = 120.3f,
        icon = Icons.Filled.Email,
        iconColor = Color(0xFF005FF9),
    ),
    AppInfo(
        id = "maps",
        name = "Карты и навигатор",
        developer = "Карты и навигация",
        description = "Маршруты на машине, пешком и на общественном транспорте",
        category = Category.MAPS,
        ageRating = 0,
        size = 210.9f,
        icon = Icons.Filled.Map,
        iconColor = Color(0xFF34A853),
    ),
    AppInfo(
        id = "heroes",
        name = "Гильдия Героев",
        developer = "VK Play",
        description = "Экшен ММО РПГ: собери команду и сражайся с боссами",
        category = Category.GAME,
        ageRating = 12,
        size = 223.7f,
        icon = Icons.Filled.SportsEsports,
        iconColor = Color(0xFF8E44AD),
        screenshotUrlList = listOf(
            "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
            "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
            "https://static.rustore.ru/imgproxy/g5whSI1uNqaL2TUO7TFfM8M63vXpWXNCm2vlX4Ahvc4/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/c2dde8bc-c4ab-482a-80a5-2789149f598d.jpg@webp",
            "https://static.rustore.ru/imgproxy/TjeurtC7BczOVJt74XhjGYuQnG1l4rx6zpDqyMb00GY/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/08318f76-7a9c-43aa-b4a7-1aa878d00861.jpg@webp",
        ),
    ),
    AppInfo(
        id = "books",
        name = "Читалка",
        developer = "Читалка",
        description = "Электронные книги и аудиокниги в удобном формате",
        category = Category.BOOKS,
        ageRating = 12,
        size = 45.2f,
        icon = Icons.AutoMirrored.Filled.MenuBook,
        iconColor = Color(0xFFE67E22),
    ),
    AppInfo(
        id = "bank",
        name = "Мой банк",
        developer = "Мой банк",
        description = "Переводы, платежи и контроль расходов",
        category = Category.FINANCE,
        ageRating = 0,
        size = 180.5f,
        icon = Icons.Filled.AccountBalance,
        iconColor = Color(0xFF16A085),
    ),
    AppInfo(
        id = "market",
        name = "Маркет",
        developer = "Маркет",
        description = "Покупки с доставкой на дом и в пункты выдачи",
        category = Category.SHOPPING,
        ageRating = 0,
        size = 95.8f,
        icon = Icons.Filled.ShoppingCart,
        iconColor = Color(0xFFF1C40F),
    ),
    AppInfo(
        id = "fitness",
        name = "Фитнес",
        developer = "Фитнес",
        description = "Тренировки дома и в зале, трекер активности",
        category = Category.HEALTH,
        ageRating = 0,
        size = 64.3f,
        icon = Icons.Filled.FitnessCenter,
        iconColor = Color(0xFFE74C3C),
    ),
    AppInfo(
        id = "school",
        name = "Учёба",
        developer = "Учёба",
        description = "Онлайн-курсы, расписание и домашние задания",
        category = Category.EDUCATION,
        ageRating = 6,
        size = 72.0f,
        icon = Icons.Filled.School,
        iconColor = Color(0xFF2980B9),
    ),
    AppInfo(
        id = "news",
        name = "Новости",
        developer = "Новости",
        description = "Главные события дня из проверенных источников",
        category = Category.NEWS,
        ageRating = 16,
        size = 38.4f,
        icon = Icons.Filled.Newspaper,
        iconColor = Color(0xFF7F8C8D),
    ),
    AppInfo(
        id = "tasks",
        name = "Задачи",
        developer = "Задачи",
        description = "Списки дел, напоминания и планирование дня",
        category = Category.APP,
        ageRating = 0,
        size = 22.6f,
        icon = Icons.Filled.TaskAlt,
        iconColor = Color(0xFF27AE60),
    ),
)

val showcaseApps = apps.take(9)
