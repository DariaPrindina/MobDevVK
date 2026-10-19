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
        description = "Общение с друзьями, сообщества, музыка и видео в одном приложении",
        category = Category.SOCIAL,
        icon = Icons.Filled.Groups,
        iconColor = Color(0xFF0077FF),
    ),
    AppInfo(
        id = "vk-music",
        name = "VK Музыка",
        description = "Миллионы треков, подкасты и персональные рекомендации",
        category = Category.MUSIC,
        icon = Icons.Filled.MusicNote,
        iconColor = Color(0xFFFF3985),
    ),
    AppInfo(
        id = "vk-video",
        name = "VK Видео",
        description = "Фильмы, сериалы, шоу и прямые трансляции",
        category = Category.VIDEO,
        icon = Icons.Filled.PlayCircle,
        iconColor = Color(0xFF2C2D2E),
    ),
    AppInfo(
        id = "mail",
        name = "Почта Mail",
        description = "Быстрая и безопасная почта со всеми ящиками в одном месте",
        category = Category.PRODUCTIVITY,
        icon = Icons.Filled.Email,
        iconColor = Color(0xFF005FF9),
    ),
    AppInfo(
        id = "maps",
        name = "Карты и навигатор",
        description = "Маршруты на машине, пешком и на общественном транспорте",
        category = Category.MAPS,
        icon = Icons.Filled.Map,
        iconColor = Color(0xFF34A853),
    ),
    AppInfo(
        id = "heroes",
        name = "Гильдия Героев",
        description = "Экшен ММО РПГ: собери команду и сражайся с боссами",
        category = Category.GAME,
        icon = Icons.Filled.SportsEsports,
        iconColor = Color(0xFF8E44AD),
    ),
    AppInfo(
        id = "books",
        name = "Читалка",
        description = "Электронные книги и аудиокниги в удобном формате",
        category = Category.BOOKS,
        icon = Icons.AutoMirrored.Filled.MenuBook,
        iconColor = Color(0xFFE67E22),
    ),
    AppInfo(
        id = "bank",
        name = "Мой банк",
        description = "Переводы, платежи и контроль расходов",
        category = Category.FINANCE,
        icon = Icons.Filled.AccountBalance,
        iconColor = Color(0xFF16A085),
    ),
    AppInfo(
        id = "market",
        name = "Маркет",
        description = "Покупки с доставкой на дом и в пункты выдачи",
        category = Category.SHOPPING,
        icon = Icons.Filled.ShoppingCart,
        iconColor = Color(0xFFF1C40F),
    ),
    AppInfo(
        id = "fitness",
        name = "Фитнес",
        description = "Тренировки дома и в зале, трекер активности",
        category = Category.HEALTH,
        icon = Icons.Filled.FitnessCenter,
        iconColor = Color(0xFFE74C3C),
    ),
    AppInfo(
        id = "school",
        name = "Учёба",
        description = "Онлайн-курсы, расписание и домашние задания",
        category = Category.EDUCATION,
        icon = Icons.Filled.School,
        iconColor = Color(0xFF2980B9),
    ),
    AppInfo(
        id = "news",
        name = "Новости",
        description = "Главные события дня из проверенных источников",
        category = Category.NEWS,
        icon = Icons.Filled.Newspaper,
        iconColor = Color(0xFF7F8C8D),
    ),
    AppInfo(
        id = "tasks",
        name = "Задачи",
        description = "Списки дел, напоминания и планирование дня",
        category = Category.APP,
        icon = Icons.Filled.TaskAlt,
        iconColor = Color(0xFF27AE60),
    ),
)
