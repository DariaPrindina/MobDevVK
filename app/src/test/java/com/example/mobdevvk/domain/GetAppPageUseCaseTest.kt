package com.example.mobdevvk.domain

import com.example.mobdevvk.testutil.FakeAppRepository
import com.example.mobdevvk.testutil.appInfo
import com.example.mobdevvk.testutil.assertFailsWith
import com.example.mobdevvk.testutil.ids
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GetAppPageUseCaseTest {

    private val repository = FakeAppRepository()
    private val useCase = GetAppPageUseCase(repository)

    @Test
    fun invoke_returnsRequestedApp() = runTest {
        repository.apps = ids(4).map { appInfo(it) }

        val page = useCase("app-2")

        assertEquals("app-2", page.app.id)
    }

    @Test
    fun invoke_similarAppsExcludeRequestedApp() = runTest {
        repository.apps = ids(4).map { appInfo(it) }

        val page = useCase("app-1")

        assertEquals(listOf("app-0", "app-2", "app-3"), page.similarApps.map { it.id })
    }

    @Test
    fun invoke_similarAppsKeepCatalogOrder() = runTest {
        repository.apps = listOf(appInfo("c"), appInfo("a"), appInfo("b"))

        val page = useCase("a")

        assertEquals(listOf("c", "b"), page.similarApps.map { it.id })
    }

    @Test
    fun invoke_onlyAppInCatalog_hasNoSimilarApps() = runTest {
        repository.apps = listOf(appInfo("only"))

        assertTrue(useCase("only").similarApps.isEmpty())
    }

    @Test
    fun invoke_showcaseComesFromRepository() = runTest {
        repository.apps = ids(12).map { appInfo(it) }
        repository.showcaseApps = ids(9).map { appInfo(it) }

        val page = useCase("app-11")

        assertEquals(ids(9), page.showcaseApps.map { it.id })
    }

    @Test
    fun invoke_showcaseIsNotFilteredByRequestedApp() = runTest {
        repository.apps = ids(3).map { appInfo(it) }
        repository.showcaseApps = ids(3).map { appInfo(it) }

        val page = useCase("app-0")

        assertEquals(ids(3), page.showcaseApps.map { it.id })
    }

    @Test
    fun invoke_unknownId_throwsNoSuchElement() = runTest {
        repository.apps = ids(2).map { appInfo(it) }

        assertFailsWith<NoSuchElementException> { useCase("missing") }
    }

    @Test
    fun invoke_repositoryError_propagates() = runTest {
        repository.error = IOException("no network")

        assertFailsWith<IOException> { useCase("app-0") }
    }
}
