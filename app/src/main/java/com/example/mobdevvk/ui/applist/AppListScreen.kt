package com.example.mobdevvk.ui.applist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobdevvk.R
import com.example.mobdevvk.data.AppInfo
import com.example.mobdevvk.data.apps
import com.example.mobdevvk.ui.theme.MobDevVKTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun AppListScreen(
    onAppClick: (AppInfo) -> Unit,
    viewModel: AppListViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ObserveEvents(
        events = viewModel.events,
        snackbarHostState = snackbarHostState,
    )

    AppListContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onToolbarClick = viewModel::onToolbarClick,
        onAppClick = onAppClick,
    )
}

@Composable
private fun ObserveEvents(
    events: Flow<AppListEvent>,
    snackbarHostState: SnackbarHostState,
) {
    val toolbarMessage = stringResource(R.string.app_list_toolbar_message)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppListEvent.ShowToolbarMessage -> {
                    snackbarHostState.showSnackbar(toolbarMessage)
                }
            }
        }
    }
}

@Composable
fun AppListContent(
    state: AppListState,
    snackbarHostState: SnackbarHostState,
    onToolbarClick: () -> Unit,
    onAppClick: (AppInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { AppListToolbar(onClick = onToolbarClick) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.apps, key = { it.id }) { app ->
                AppListItem(app = app, onClick = { onAppClick(app) })
                HorizontalDivider(modifier = Modifier.padding(start = 88.dp))
            }
        }
    }
}

@Composable
private fun AppListToolbar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .clickable(onClick = onClick)
            .height(64.dp)
            .padding(horizontal = 16.dp),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
            )
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.app_name),
            )
        }
        Spacer(Modifier.width(16.dp))
        Text(
            text = stringResource(R.string.app_list_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun AppListItem(
    app: AppInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        AppIcon(app)
        Spacer(Modifier.width(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = app.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = app.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            CategoryChip(app.category.title)
        }
    }
}

@Composable
fun AppIcon(
    app: AppInfo,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(size / 4))
            .background(app.iconColor),
    ) {
        Icon(
            imageVector = app.icon,
            contentDescription = app.name,
            tint = Color.White,
            modifier = Modifier.size(size * 4 / 7),
        )
    }
}

@Composable
private fun CategoryChip(title: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppListScreenPreview() {
    MobDevVKTheme {
        AppListContent(
            state = AppListState(apps = apps),
            snackbarHostState = remember { SnackbarHostState() },
            onToolbarClick = {},
            onAppClick = {},
        )
    }
}
