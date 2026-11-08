package com.example.mobdevvk.presentation.common

import androidx.annotation.StringRes
import com.example.mobdevvk.R
import com.example.mobdevvk.domain.Category

@StringRes
fun Category.titleRes(): Int = when (this) {
    Category.BUSINESS -> R.string.category_business
    Category.FOOD -> R.string.category_food
    Category.HEALTH -> R.string.category_health
    Category.GAME -> R.string.category_game
    Category.BOOKS -> R.string.category_books
    Category.MUSIC -> R.string.category_music
    Category.NAVIGATION -> R.string.category_navigation
    Category.NEWS -> R.string.category_news
    Category.LIFESTYLE -> R.string.category_lifestyle
    Category.EDUCATION -> R.string.category_education
    Category.COMMUNICATION -> R.string.category_communication
    Category.WEATHER -> R.string.category_weather
    Category.PRODUCTIVITY -> R.string.category_productivity
    Category.ENTERTAINMENT -> R.string.category_entertainment
    Category.UTILITIES -> R.string.category_utilities
    Category.FINANCE -> R.string.category_finance
    Category.PHOTO_VIDEO -> R.string.category_photo_video
    Category.SHOPPING -> R.string.category_shopping
    Category.OTHER -> R.string.category_other
}
