package com.example.siyai_front_android.ui.components.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.SecondaryButton
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun BasicDialog(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    icon: Painter? = null,
    title: String,
    text: String,
    confirmButtonText: String,
    dismissButtonText: String = stringResource(R.string.cancel)
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                icon?.let { dialogIcon ->
                    Icon(
                        painter = dialogIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .padding(bottom = 16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = text,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )

                SecondaryButton(
                    onClick = onConfirm,
                    text = confirmButtonText,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )

                SecondaryButton(
                    onClick = onDismissRequest,
                    text = dismissButtonText,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExitFromAppDialog_Preview() {
    SiyaifrontandroidTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            BasicDialog(
                onDismissRequest = {},
                onConfirm = {},
                confirmButtonText = "Ok",
                dismissButtonText = "Cancel",
                title = "Title",
                text = "MainText"
            )
        }
    }
}