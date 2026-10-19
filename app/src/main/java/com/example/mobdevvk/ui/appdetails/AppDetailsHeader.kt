package com.example.mobdevvk.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobdevvk.R
import com.example.mobdevvk.data.AppInfo
import com.example.mobdevvk.data.apps
import com.example.mobdevvk.ui.applist.AppIcon
import com.example.mobdevvk.ui.theme.MobDevVKTheme
import kotlin.math.roundToInt

@Composable
fun AppDetailsHeader(
    app: AppInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppIcon(app = app, size = 128.dp)
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = app.category.title,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = app.developer,
                fontSize = 12.sp,
            )
            Spacer(Modifier.height(4.dp))
            Row {
                Column(Modifier.width(IntrinsicSize.Max)) {
                    Text(
                        text = "${app.ageRating}+",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = stringResource(R.string.app_details_age))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(text = "${app.size.roundToInt()} MB")
                    Spacer(Modifier.height(4.dp))
                    Text(text = stringResource(R.string.app_details_size))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MobDevVKTheme {
        AppDetailsHeader(app = apps.first(), modifier = Modifier.fillMaxWidth())
    }
}
