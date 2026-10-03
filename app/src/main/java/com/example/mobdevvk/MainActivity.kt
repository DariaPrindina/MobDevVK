package com.example.mobdevvk

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobdevvk.ui.theme.MobDevVKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobDevVKTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        onOpenSecond = ::openSecondActivity,
                        onCall = ::dialNumber,
                        onShare = ::shareText,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun openSecondActivity(text: String): Int? {
        if (text.isBlank()) return R.string.error_empty_text
        val intent = Intent(this, SecondActivity::class.java)
            .putExtra(SecondActivity.EXTRA_TEXT, text.trim())
        startActivity(intent)
        return null
    }

    private fun dialNumber(text: String): Int? {
        val phone = text.trim()
        if (phone.isEmpty()) return R.string.error_empty_phone
        if (!PhoneValidator.isValid(phone)) return R.string.error_invalid_phone
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        return launchExternal(intent, R.string.error_no_dialer)
    }

    private fun shareText(text: String): Int? {
        if (text.isBlank()) return R.string.error_empty_text
        val sendIntent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, text.trim())
        val chooser = Intent.createChooser(sendIntent, getString(R.string.share_chooser_title))
        return launchExternal(chooser, R.string.error_no_share_app)
    }

    private fun launchExternal(intent: Intent, @StringRes errorRes: Int): Int? {
        return try {
            startActivity(intent)
            null
        } catch (e: ActivityNotFoundException) {
            errorRes
        }
    }
}

object PhoneValidator {
    private val allowedChars = Regex("""^\+?[\d\s\-()]+$""")

    fun isValid(phone: String): Boolean {
        val digits = phone.count { it.isDigit() }
        return allowedChars.matches(phone) && digits in 3..15
    }
}

@Composable
fun MainScreen(
    onOpenSecond: (String) -> Int?,
    onCall: (String) -> Int?,
    onShare: (String) -> Int?,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }
    var errorRes by rememberSaveable { mutableStateOf<Int?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                errorRes = null
            },
            label = { Text(stringResource(R.string.input_hint)) },
            isError = errorRes != null,
            supportingText = errorRes?.let { res -> { Text(stringResource(res)) } },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { errorRes = onOpenSecond(text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.open_second_activity))
        }
        Button(
            onClick = { errorRes = onCall(text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.call_friend))
        }
        Button(
            onClick = { errorRes = onShare(text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.share_text))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MobDevVKTheme {
        MainScreen(onOpenSecond = { null }, onCall = { null }, onShare = { null })
    }
}
