package com.example.mobdevvk.data

import com.example.mobdevvk.domain.AppDetails
import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.Category
import javax.inject.Inject

class AppMapper @Inject constructor() {

    fun toDomain(dto: AppItemDto): AppInfo = AppInfo(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = toCategory(dto.category),
        iconUrl = dto.iconUrl,
    )

    fun toDomain(dto: AppDetailsDto): AppDetails = AppDetails(
        id = dto.id,
        name = dto.name,
        developer = dto.developer,
        category = toCategory(dto.category),
        ageRating = dto.ageRating,
        size = dto.size.toFloat(),
        iconUrl = dto.iconUrl,
        screenshotUrlList = dto.screenshotUrlList,
        description = dto.description,
    )

    private fun toCategory(category: String): Category = when (category) {
        "Бизнес" -> Category.BUSINESS
        "Еда и напитки" -> Category.FOOD
        "Здоровье и фитнес" -> Category.HEALTH
        "Игры" -> Category.GAME
        "Книги и справочники" -> Category.BOOKS
        "Музыка" -> Category.MUSIC
        "Навигация" -> Category.NAVIGATION
        "Новости" -> Category.NEWS
        "Образ жизни" -> Category.LIFESTYLE
        "Образование" -> Category.EDUCATION
        "Общение" -> Category.COMMUNICATION
        "Погода" -> Category.WEATHER
        "Производительность" -> Category.PRODUCTIVITY
        "Развлечения" -> Category.ENTERTAINMENT
        "Утилиты" -> Category.UTILITIES
        "Финансы" -> Category.FINANCE
        "Фото и видео" -> Category.PHOTO_VIDEO
        "Шопинг" -> Category.SHOPPING
        else -> Category.OTHER
    }
}
