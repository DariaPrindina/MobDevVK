package com.example.mobdevvk.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.presentation.common.previewApp
import com.example.mobdevvk.presentation.common.previewApps
import com.example.mobdevvk.presentation.theme.MobDevVKTheme

@Composable
fun AppDetailsContent(
    app: AppInfo,
    similarApps: List<AppInfo>,
    showcaseApps: List<AppInfo>,
    descriptionCollapsed: Boolean,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onInstallClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onDeveloperClick: () -> Unit,
    onSimilarAppDownloadClick: (AppInfo) -> Unit,
    onShowcaseAppClick: (AppInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = onShareClick,
        )
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            app = app,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        if (similarApps.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            SimilarApps(
                apps = similarApps,
                onDownloadClick = onSimilarAppDownloadClick,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
        if (app.screenshotUrlList.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            ScreenshotsList(
                screenshotUrlList = app.screenshotUrlList,
                contentPadding = PaddingValues(horizontal = 16.dp),
            )
        }
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = app.description,
            collapsed = descriptionCollapsed,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(12.dp))
        Developer(
            name = app.developer,
            onClick = onDeveloperClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        Showcase(
            apps = showcaseApps,
            onAppClick = onShowcaseAppClick,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MobDevVKTheme {
        AppDetailsContent(
            app = previewApp,
            similarApps = previewApps,
            showcaseApps = previewApps,
            descriptionCollapsed = false,
            onBackClick = {},
            onShareClick = {},
            onInstallClick = {},
            onReadMoreClick = {},
            onDeveloperClick = {},
            onSimilarAppDownloadClick = {},
            onShowcaseAppClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
