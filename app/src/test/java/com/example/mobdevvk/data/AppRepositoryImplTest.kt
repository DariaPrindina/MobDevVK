package com.example.mobdevvk.data

import com.example.mobdevvk.domain.Category
import com.example.mobdevvk.testutil.FakeAppApi
import com.example.mobdevvk.testutil.FakeRatingApi
import com.example.mobdevvk.testutil.appDetailsDto
import com.example.mobdevvk.testutil.appItemDto
import com.example.mobdevvk.testutil.assertFailsWith
import com.example.mobdevvk.testutil.ids
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class AppRepositoryImplTest {

    private val api = FakeAppApi()
    private val ratingApi = FakeRatingApi()
    private val repository = AppRepositoryImpl(api, ratingApi, AppMapper())

    @Test
    fun getApps_mapsCatalogToDomain() = runTest {
        api.catalog = listOf(
            appItemDto("a", name = "Alpha", category = "Игры"),
            appItemDto("b", name = "Beta", category = "Музыка"),
        )

        val apps = repository.getApps()

        assertEquals(listOf("a", "b"), apps.map { it.id })
        assertEquals(listOf("Alpha", "Beta"), apps.map { it.name })
        assertEquals(listOf(Category.GAME, Category.MUSIC), apps.map { it.category })
    }

    @Test
    fun getApps_emptyCatalog_returnsEmptyList() = runTest {
        assertTrue(repository.getApps().isEmpty())
    }

    @Test
    fun getApps_cachesResult() = runTest {
        api.catalog = listOf(appItemDto("a"))

        repository.getApps()
        repository.getApps()

        assertEquals(1, api.catalogCalls)
    }

    @Test
    fun getApps_apiError_propagates() = runTest {
        api.error = IOException("no network")

        val error = assertFailsWith<IOException> { repository.getApps() }

        assertEquals("no network", error.message)
    }

    @Test
    fun getApps_afterError_retriesRequest() = runTest {
        api.catalog = listOf(appItemDto("a"))
        api.error = IOException("no network")
        assertFailsWith<IOException> { repository.getApps() }

        api.error = null
        val apps = repository.getApps()

        assertEquals(listOf("a"), apps.map { it.id })
        assertEquals(2, api.catalogCalls)
    }

    @Test
    fun getApp_returnsAppWithMatchingId() = runTest {
        api.catalog = listOf(appItemDto("a"), appItemDto("b", name = "Beta"))

        val app = repository.getApp("b")

        assertEquals("Beta", app?.name)
    }

    @Test
    fun getApp_unknownId_returnsNull() = runTest {
        api.catalog = listOf(appItemDto("a"))

        assertNull(repository.getApp("missing"))
    }

    @Test
    fun getApp_usesCachedCatalog() = runTest {
        api.catalog = listOf(appItemDto("a"), appItemDto("b"))

        repository.getApps()
        repository.getApp("a")
        repository.getApp("b")

        assertEquals(1, api.catalogCalls)
    }

    @Test
    fun getShowcaseApps_returnsFirstNineInCatalogOrder() = runTest {
        api.catalog = ids(12).map { appItemDto(it) }

        val showcase = repository.getShowcaseApps()

        assertEquals(ids(9), showcase.map { it.id })
    }

    @Test
    fun getShowcaseApps_smallCatalog_returnsAll() = runTest {
        api.catalog = ids(4).map { appItemDto(it) }

        assertEquals(ids(4), repository.getShowcaseApps().map { it.id })
    }

    @Test
    fun getAppDetails_mapsDtoToDomain() = runTest {
        api.details = mapOf(
            "a" to appDetailsDto("a", developer = "Dev", ageRating = 16, size = 5.5),
        )

        val details = repository.getAppDetails("a")

        assertEquals("a", details.id)
        assertEquals("Dev", details.developer)
        assertEquals(16, details.ageRating)
        assertEquals(5.5f, details.size, 0.0001f)
    }

    @Test
    fun getAppDetails_requestsGivenId() = runTest {
        api.details = mapOf("a" to appDetailsDto("a"), "b" to appDetailsDto("b", name = "Beta"))

        assertEquals("Beta", repository.getAppDetails("b").name)
        assertEquals(1, api.detailsCalls)
    }

    @Test
    fun getAppDetails_apiError_propagates() = runTest {
        api.error = IOException("boom")

        assertFailsWith<IOException> { repository.getAppDetails("a") }
    }

    @Test
    fun loadRatingsSequentially_returnsRatingForEveryId() = runTest {
        val requested = ids(5)

        val ratings = repository.loadRatingsSequentially(requested)

        assertEquals(requested.toSet(), ratings.keys)
        requested.forEach { assertEquals(FakeRatingApi.ratingOf(it), ratings[it]) }
    }

    @Test
    fun loadRatingsSequentially_requestsInGivenOrder() = runTest {
        val requested = ids(6)

        repository.loadRatingsSequentially(requested)

        assertEquals(requested, ratingApi.requestedIds)
    }

    @Test
    fun loadRatingsSequentially_takesSumOfDelays() = runTest {
        repository.loadRatingsSequentially(ids(10))

        assertEquals(10 * 1500L, testScheduler.currentTime)
    }

    @Test
    fun loadRatingsSequentially_emptyList_returnsEmptyMapWithoutDelay() = runTest {
        val ratings = repository.loadRatingsSequentially(emptyList())

        assertTrue(ratings.isEmpty())
        assertEquals(0L, testScheduler.currentTime)
    }

    @Test
    fun loadRatingsInParallel_returnsRatingForEveryId() = runTest {
        val requested = ids(5)

        val ratings = repository.loadRatingsInParallel(requested)

        assertEquals(requested.toSet(), ratings.keys)
        requested.forEach { assertEquals(FakeRatingApi.ratingOf(it), ratings[it]) }
    }

    @Test
    fun loadRatingsInParallel_takesSingleDelay() = runTest {
        repository.loadRatingsInParallel(ids(10))

        assertEquals(1500L, testScheduler.currentTime)
    }

    @Test
    fun loadRatingsInParallel_givesSameResultAsSequential() = runTest {
        val requested = ids(8)

        val sequential = repository.loadRatingsSequentially(requested)
        val parallel = repository.loadRatingsInParallel(requested)

        assertEquals(sequential, parallel)
    }

    @Test
    fun loadRatingsInParallel_emptyList_returnsEmptyMap() = runTest {
        assertTrue(repository.loadRatingsInParallel(emptyList()).isEmpty())
    }

    @Test
    fun loadRatingsInParallel_oneFailure_throwsAndCancelsOthers() = runTest {
        val failing = FakeRatingApi(failingIds = setOf("app-2"))
        val repository = AppRepositoryImpl(api, failing, AppMapper())

        assertFailsWith<IllegalStateException> { repository.loadRatingsInParallel(ids(5)) }

        assertTrue(failing.completedIds.isEmpty())
    }
}
