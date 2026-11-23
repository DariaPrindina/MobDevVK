package com.example.mobdevvk.domain

import javax.inject.Inject

class GetAppPageUseCase @Inject constructor(
    private val repository: AppRepository,
) {
    suspend operator fun invoke(id: String): AppPage {
        val app = repository.getApp(id) ?: throw NoSuchElementException("App $id not found")
        return AppPage(
            app = app,
            similarApps = repository.getApps().filter { it.id != id },
            showcaseApps = repository.getShowcaseApps(),
        )
    }
}
