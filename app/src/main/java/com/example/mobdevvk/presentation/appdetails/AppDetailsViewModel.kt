package com.example.mobdevvk.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mobdevvk.domain.AppRepository
import com.example.mobdevvk.presentation.navigation.AppDetailsRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    repository: AppRepository,
) : ViewModel() {

    private val appId = savedStateHandle.toRoute<AppDetailsRoute>().appId

    private val _state = MutableStateFlow(
        AppDetailsState(
            app = checkNotNull(repository.getApp(appId)),
            similarApps = repository.getApps().filter { it.id != appId },
            showcaseApps = repository.getShowcaseApps(),
        )
    )
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { it.copy(descriptionCollapsed = true) }
    }
}
