package com.example.mobdevvk.testutil

import com.example.mobdevvk.data.AppApi
import com.example.mobdevvk.data.AppDetailsDto
import com.example.mobdevvk.data.AppItemDto
import com.example.mobdevvk.data.RatingApi
import com.example.mobdevvk.domain.AppDetails
import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.domain.AppRepository
import kotlinx.coroutines.delay

class FakeAppApi(
    var catalog: List<AppItemDto> = emptyList(),
    var details: Map<String, AppDetailsDto> = emptyMap(),
) : AppApi {

    var error: Throwable? = null
    var catalogCalls = 0
    var detailsCalls = 0

    override suspend fun getCatalog(): List<AppItemDto> {
        catalogCalls++
        error?.let { throw it }
        return catalog
    }

    override suspend fun getAppDetails(id: String): AppDetailsDto {
        detailsCalls++
        error?.let { throw it }
        return details[id] ?: throw NoSuchElementException(id)
    }
}

class FakeRatingApi(
    private val delayMillis: Long = 1500,
    private val failingIds: Set<String> = emptySet(),
) : RatingApi {

    val requestedIds = mutableListOf<String>()
    val completedIds = mutableListOf<String>()

    override suspend fun getRating(id: String): Float {
        requestedIds += id
        if (id in failingIds) throw IllegalStateException("Rating failed for $id")
        delay(delayMillis)
        completedIds += id
        return ratingOf(id)
    }

    companion object {
        fun ratingOf(id: String): Float = 3f + (id.hashCode().mod(21)) / 10f
    }
}

class FakeAppRepository(
    var apps: List<AppInfo> = emptyList(),
    var showcaseApps: List<AppInfo> = emptyList(),
) : AppRepository {

    var error: Throwable? = null
    var getAppsCalls = 0

    override suspend fun getApps(): List<AppInfo> {
        getAppsCalls++
        error?.let { throw it }
        return apps
    }

    override suspend fun getApp(id: String): AppInfo? {
        error?.let { throw it }
        return apps.firstOrNull { it.id == id }
    }

    override suspend fun getShowcaseApps(): List<AppInfo> {
        error?.let { throw it }
        return showcaseApps
    }

    override suspend fun getAppDetails(id: String): AppDetails =
        throw UnsupportedOperationException()

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> =
        throw UnsupportedOperationException()

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> =
        throw UnsupportedOperationException()
}

suspend inline fun <reified T : Throwable> assertFailsWith(block: suspend () -> Unit): T {
    try {
        block()
    } catch (e: Throwable) {
        if (e is T) return e
        throw e
    }
    throw AssertionError("Expected ${T::class.simpleName} to be thrown")
}
