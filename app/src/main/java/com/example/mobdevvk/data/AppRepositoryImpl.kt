package com.example.mobdevvk.data

import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.AppRepository

class AppRepositoryImpl(
    private val dataSource: AppLocalDataSource,
    private val mapper: AppMapper,
) : AppRepository {

    override fun getApps(): List<AppInfo> =
        dataSource.getApps().map(mapper::toDomain)

    override fun getApp(id: String): AppInfo? =
        dataSource.getApps().firstOrNull { it.id == id }?.let(mapper::toDomain)

    override fun getShowcaseApps(): List<AppInfo> {
        val apps = getApps().associateBy { it.id }
        return dataSource.getShowcaseAppIds().mapNotNull { apps[it] }
    }
}
