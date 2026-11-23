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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

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

    fun loadRatings(mode: RatingsLoadMode) {
        val current = _state.value
        if (current !is AppListState.Content || current.ratingsLoading) return

        viewModelScope.launch {
            updateContent { it.copy(ratingsLoading = true) }
            try {
                val ids = current.apps.map { it.id }
                var ratings: Map<String, Float>
                val timeMillis = measureTimeMillis {
                    ratings = when (mode) {
                        RatingsLoadMode.SEQUENTIAL -> repository.loadRatingsSequentially(ids)
                        RatingsLoadMode.PARALLEL -> repository.loadRatingsInParallel(ids)
                    }
                }
                updateContent {
                    it.copy(
                        ratings = ratings,
                        ratingsLoading = false,
                        ratingsResult = RatingsResult(mode, timeMillis),
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                updateContent { it.copy(ratingsLoading = false) }
            }
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

    private fun updateContent(transform: (AppListState.Content) -> AppListState.Content) {
        _state.update { current ->
            if (current is AppListState.Content) transform(current) else current
        }
    }
}
