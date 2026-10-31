package com.example.mobdevvk.presentation.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Apps
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
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mobdevvk.R
import com.example.mobdevvk.domain.Category

fun appIcon(iconName: String): ImageVector = when (iconName) {
    "groups" -> Icons.Filled.Groups
    "music" -> Icons.Filled.MusicNote
    "video" -> Icons.Filled.PlayCircle
    "mail" -> Icons.Filled.Email
    "map" -> Icons.Filled.Map
    "game" -> Icons.Filled.SportsEsports
    "book" -> Icons.AutoMirrored.Filled.MenuBook
    "bank" -> Icons.Filled.AccountBalance
    "shopping" -> Icons.Filled.ShoppingCart
    "fitness" -> Icons.Filled.FitnessCenter
    "school" -> Icons.Filled.School
    "news" -> Icons.Filled.Newspaper
    "tasks" -> Icons.Filled.TaskAlt
    else -> Icons.Filled.Apps
}

@StringRes
fun Category.titleRes(): Int = when (this) {
    Category.APP -> R.string.category_app
    Category.GAME -> R.string.category_game
    Category.PRODUCTIVITY -> R.string.category_productivity
    Category.SOCIAL -> R.string.category_social
    Category.EDUCATION -> R.string.category_education
    Category.MUSIC -> R.string.category_music
    Category.VIDEO -> R.string.category_video
    Category.HEALTH -> R.string.category_health
    Category.NEWS -> R.string.category_news
    Category.BOOKS -> R.string.category_books
    Category.FINANCE -> R.string.category_finance
    Category.MAPS -> R.string.category_maps
    Category.SHOPPING -> R.string.category_shopping
}
