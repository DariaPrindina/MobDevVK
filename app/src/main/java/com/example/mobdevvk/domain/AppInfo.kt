package com.example.mobdevvk.domain

data class AppInfo(
    val id: String,
    val name: String,
    val description: String,
    val category: Category,
    val iconUrl: String,
)
