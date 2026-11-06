package com.example.mobdevvk.domain

interface AppRepository {
    fun getApps(): List<AppInfo>
    fun getApp(id: String): AppInfo?
    fun getShowcaseApps(): List<AppInfo>
}
