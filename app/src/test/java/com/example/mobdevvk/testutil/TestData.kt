package com.example.mobdevvk.testutil

import com.example.mobdevvk.data.AppDetailsDto
import com.example.mobdevvk.data.AppItemDto
import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.Category

fun appItemDto(
    id: String,
    name: String = "App $id",
    description: String = "Description of $id",
    category: String = "Игры",
    iconUrl: String = "https://example.com/$id.png",
) = AppItemDto(
    id = id,
    name = name,
    description = description,
    category = category,
    iconUrl = iconUrl,
)

fun appDetailsDto(
    id: String,
    name: String = "App $id",
    developer: String = "Developer",
    category: String = "Игры",
    ageRating: Int = 12,
    size: Double = 8.2,
    iconUrl: String = "https://example.com/$id.png",
    screenshotUrlList: List<String> = emptyList(),
    description: String = "Description of $id",
) = AppDetailsDto(
    id = id,
    name = name,
    developer = developer,
    category = category,
    ageRating = ageRating,
    size = size,
    iconUrl = iconUrl,
    screenshotUrlList = screenshotUrlList,
    description = description,
)

fun appInfo(
    id: String,
    category: Category = Category.GAME,
) = AppInfo(
    id = id,
    name = "App $id",
    description = "Description of $id",
    category = category,
    iconUrl = "https://example.com/$id.png",
)

fun ids(count: Int): List<String> = List(count) { "app-$it" }
