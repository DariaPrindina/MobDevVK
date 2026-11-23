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

class GetAppsUseCaseTest {

    private val repository = FakeAppRepository()
    private val useCase = GetAppsUseCase(repository)

    @Test
    fun invoke_returnsAppsFromRepository() = runTest {
        repository.apps = ids(3).map { appInfo(it) }

        assertEquals(ids(3), useCase().map { it.id })
    }

    @Test
    fun invoke_emptyCatalog_returnsEmptyList() = runTest {
        assertTrue(useCase().isEmpty())
    }

    @Test
    fun invoke_keepsRepositoryOrder() = runTest {
        repository.apps = listOf(appInfo("c"), appInfo("a"), appInfo("b"))

        assertEquals(listOf("c", "a", "b"), useCase().map { it.id })
    }

    @Test
    fun invoke_callsRepositoryOnce() = runTest {
        repository.apps = ids(2).map { appInfo(it) }

        useCase()

        assertEquals(1, repository.getAppsCalls)
    }

    @Test
    fun invoke_repositoryError_propagates() = runTest {
        repository.error = IOException("no network")

        val error = assertFailsWith<IOException> { useCase() }

        assertEquals("no network", error.message)
    }
}
