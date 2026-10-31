package com.example.mobdevvk.data

data class AppDto(
    val id: String,
    val name: String,
    val developer: String,
    val description: String,
    val category: String,
    val ageRating: Int,
    val sizeMb: Double,
    val iconName: String,
    val iconColor: String,
    val screenshotUrls: List<String>? = null,
)
