package com.example.mobdevvk.domain

interface AppRepository {
    suspend fun getApps(): List<AppInfo>
    suspend fun getApp(id: String): AppInfo?
    suspend fun getShowcaseApps(): List<AppInfo>
    suspend fun getAppDetails(id: String): AppDetails
    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}
