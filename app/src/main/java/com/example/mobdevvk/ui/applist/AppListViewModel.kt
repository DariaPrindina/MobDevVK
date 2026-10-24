package com.example.mobdevvk.ui.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobdevvk.data.apps
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {

    private val _state = MutableStateFlow(AppListState())
    val state = _state.asStateFlow()

    private val _events = Channel<AppListEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadApps()
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowToolbarMessage)
        }
    }

    private fun loadApps() {
        _state.value = AppListState(apps = apps)
    }
}
