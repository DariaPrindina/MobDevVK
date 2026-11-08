package com.example.mobdevvk.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobdevvk.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
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

    fun loadApps() {
        viewModelScope.launch {
            _state.value = AppListState.Loading
            _state.value = try {
                AppListState.Content(repository.getApps())
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                AppListState.Error
            }
        }
    }
}
