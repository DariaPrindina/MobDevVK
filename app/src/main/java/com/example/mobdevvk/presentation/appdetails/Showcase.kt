package com.example.mobdevvk.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobdevvk.R
import com.example.mobdevvk.domain.AppInfo
import com.example.mobdevvk.presentation.applist.AppListItem
import com.example.mobdevvk.presentation.common.previewApps
import com.example.mobdevvk.presentation.theme.MobDevVKTheme

private const val APPS_PER_PAGE = 3

@Composable
fun Showcase(
    apps: List<AppInfo>,
    onAppClick: (AppInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pages = apps.chunked(APPS_PER_PAGE)

    Column(modifier) {
        Text(
            text = stringResource(R.string.app_details_showcase),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(4.dp))
        HorizontalPager(
            state = rememberPagerState { pages.size },
            contentPadding = PaddingValues(end = 32.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            Column {
                pages[page].forEach { app ->
                    AppListItem(app = app, onClick = { onAppClick(app) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MobDevVKTheme {
        Showcase(
            apps = previewApps,
            onAppClick = {},
        )
    }
}
