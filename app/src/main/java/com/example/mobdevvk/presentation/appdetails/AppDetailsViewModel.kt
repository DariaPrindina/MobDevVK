package com.example.mobdevvk.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mobdevvk.domain.AppRepository
import com.example.mobdevvk.presentation.navigation.AppDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
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
}
