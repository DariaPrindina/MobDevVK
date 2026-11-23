package com.example.mobdevvk.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobdevvk.R
import com.example.mobdevvk.presentation.theme.MobDevVKTheme

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Button(
            onClick = onClick,
            enabled = installState !is InstallState.Installed,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(
                    when (installState) {
                        is InstallState.NotInstalled -> R.string.install
                        is InstallState.Installing -> R.string.cancel_install
                        is InstallState.Installed -> R.string.installed
                    }
                )
            )
        }
        if (installState is InstallState.Installing) {
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { installState.progress / 100f },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNotInstalled() {
    MobDevVKTheme {
        InstallButton(
            installState = InstallState.NotInstalled,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun PreviewInstalling() {
    MobDevVKTheme {
        InstallButton(
            installState = InstallState.Installing(progress = 40),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
