package com.example.mobdevvk.ui.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mobdevvk.R
import com.example.mobdevvk.data.AppInfo
import kotlinx.coroutines.launch

@Composable
fun AppDetailsScreen(
    app: AppInfo,
    similarApps: List<AppInfo>,
    showcaseApps: List<AppInfo>,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppInfo) -> Unit,
) {
    var descriptionCollapsed by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val underDevelopmentText = stringResource(R.string.under_development)
    val showUnderDevelopmentMessage: () -> Unit = {
        scope.launch { snackbarHostState.showSnackbar(underDevelopmentText) }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        AppDetailsContent(
            app = app,
            similarApps = similarApps,
            showcaseApps = showcaseApps,
            descriptionCollapsed = descriptionCollapsed,
            onBackClick = onBackClick,
            onShareClick = showUnderDevelopmentMessage,
            onInstallClick = showUnderDevelopmentMessage,
            onReadMoreClick = { descriptionCollapsed = true },
            onDeveloperClick = showUnderDevelopmentMessage,
            onSimilarAppDownloadClick = { showUnderDevelopmentMessage() },
            onShowcaseAppClick = onShowcaseAppClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        )
    }
}
