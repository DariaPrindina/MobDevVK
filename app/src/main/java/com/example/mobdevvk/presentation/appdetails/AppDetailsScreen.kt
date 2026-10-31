package com.example.mobdevvk.presentation.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobdevvk.R
import com.example.mobdevvk.domain.AppInfo
import kotlinx.coroutines.flow.Flow

@Composable
fun AppDetailsScreen(
    viewModel: AppDetailsViewModel,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppInfo) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveEvents(
        events = viewModel.events,
        snackbarHostState = snackbarHostState,
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        AppDetailsContent(
            app = state.app,
            similarApps = state.similarApps,
            showcaseApps = state.showcaseApps,
            descriptionCollapsed = state.descriptionCollapsed,
            onBackClick = onBackClick,
            onShareClick = viewModel::showUnderDevelopmentMessage,
            onInstallClick = viewModel::showUnderDevelopmentMessage,
            onReadMoreClick = viewModel::collapseDescription,
            onDeveloperClick = viewModel::showUnderDevelopmentMessage,
            onSimilarAppDownloadClick = { viewModel.showUnderDevelopmentMessage() },
            onShowcaseAppClick = onShowcaseAppClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        )
    }
}

@Composable
private fun ObserveEvents(
    events: Flow<AppDetailsEvent>,
    snackbarHostState: SnackbarHostState,
) {
    val underDevelopmentText = stringResource(R.string.under_development)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppDetailsEvent.UnderDevelopment -> {
                    snackbarHostState.showSnackbar(underDevelopmentText)
                }
            }
        }
    }
}
