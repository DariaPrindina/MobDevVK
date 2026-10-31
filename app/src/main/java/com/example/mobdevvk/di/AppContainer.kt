package com.example.mobdevvk.di

import com.example.mobdevvk.data.AppLocalDataSource
import com.example.mobdevvk.data.AppMapper
import com.example.mobdevvk.data.AppRepositoryImpl
import com.example.mobdevvk.domain.AppRepository

object AppContainer {
    val appRepository: AppRepository by lazy {
        AppRepositoryImpl(
            dataSource = AppLocalDataSource(),
            mapper = AppMapper(),
        )
    }
}
