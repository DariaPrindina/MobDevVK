package com.example.mobdevvk.domain

data class AppInfo(
    val id: String,
    val name: String,
    val developer: String,
    val description: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconName: String,
    val iconColor: Long,
    val screenshotUrlList: List<String>,
)
