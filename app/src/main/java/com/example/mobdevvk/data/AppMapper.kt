package com.example.mobdevvk.data

import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.Category

class AppMapper {

    fun toDomain(dto: AppDto): AppInfo = AppInfo(
        id = dto.id,
        name = dto.name,
        developer = dto.developer,
        description = dto.description,
        category = toCategory(dto.category),
        ageRating = dto.ageRating,
        size = dto.sizeMb.toFloat(),
        iconName = dto.iconName,
        iconColor = toColor(dto.iconColor),
        screenshotUrlList = dto.screenshotUrls.orEmpty(),
    )

    private fun toCategory(category: String): Category = when (category) {
        "Игры" -> Category.GAME
        "Производительность" -> Category.PRODUCTIVITY
        "Социальные сети" -> Category.SOCIAL
        "Образование" -> Category.EDUCATION
        "Музыка" -> Category.MUSIC
        "Видео" -> Category.VIDEO
        "Здоровье" -> Category.HEALTH
        "Новости" -> Category.NEWS
        "Книги" -> Category.BOOKS
        "Финансы" -> Category.FINANCE
        "Карты" -> Category.MAPS
        "Покупки" -> Category.SHOPPING
        else -> Category.APP
    }

    private fun toColor(hex: String): Long =
        (0xFF000000 or hex.removePrefix("#").toLong(16))
}
