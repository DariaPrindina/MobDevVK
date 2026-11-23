package com.example.mobdevvk.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mobdevvk.domain.AppRepository
import com.example.mobdevvk.presentation.navigation.AppDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AppRepository,
) : ViewModel() {

    private val appId = savedStateHandle.toRoute<AppDetailsRoute>().appId

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var installJob: Job? = null

    init {
        loadApp()
    }

    fun loadApp() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            _state.value = try {
                val app = checkNotNull(repository.getApp(appId))
                AppDetailsState.Content(
                    app = app,
                    similarApps = repository.getApps().filter { it.id != appId },
                    showcaseApps = repository.getShowcaseApps(),
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                AppDetailsState.Error
            }
        }
    }

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        when (current.installState) {
            is InstallState.Installing -> {
                installJob?.cancel()
                installJob = null
                setInstallState(InstallState.NotInstalled)
            }

            is InstallState.NotInstalled -> {
                installJob = viewModelScope.launch { emulateInstall() }
            }

            is InstallState.Installed -> Unit
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 0..100 step INSTALL_STEP) {
            setInstallState(InstallState.Installing(progress))
            delay(INSTALL_STEP_DELAY_MS)
        }
        setInstallState(InstallState.Installed)
    }

    private fun setInstallState(installState: InstallState) {
        _state.update { current ->
            if (current is AppDetailsState.Content) {
                current.copy(installState = installState)
            } else {
                current
            }
        }
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { current ->
            if (current is AppDetailsState.Content) {
                current.copy(descriptionCollapsed = true)
            } else {
                current
            }
        }
    }

    private companion object {
        const val INSTALL_STEP = 5
        const val INSTALL_STEP_DELAY_MS = 150L
    }
}
