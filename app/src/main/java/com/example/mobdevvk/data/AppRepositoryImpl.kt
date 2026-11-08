package com.example.mobdevvk.data

import com.example.mobdevvk.domain.AppDetails
import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val mapper: AppMapper,
) : AppRepository {

    private var cachedApps: List<AppInfo>? = null

    override suspend fun getApps(): List<AppInfo> =
        cachedApps ?: api.getCatalog().map(mapper::toDomain).also { cachedApps = it }

    override suspend fun getApp(id: String): AppInfo? =
        getApps().firstOrNull { it.id == id }

    override suspend fun getShowcaseApps(): List<AppInfo> =
        getApps().take(SHOWCASE_SIZE)

    override suspend fun getAppDetails(id: String): AppDetails =
        mapper.toDomain(api.getAppDetails(id))

    private companion object {
        const val SHOWCASE_SIZE = 9
    }
}
