package com.example.mobdevvk.domain

interface AppRepository {
    suspend fun getApps(): List<AppInfo>
    suspend fun getApp(id: String): AppInfo?
    suspend fun getShowcaseApps(): List<AppInfo>
    suspend fun getAppDetails(id: String): AppDetails
}
