package com.example.mobdevvk.domain

import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppRepository,
) {
    suspend operator fun invoke(): List<AppInfo> = repository.getApps()
}
