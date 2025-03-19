package com.example.siyai_front_android.ui.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun SecondaryLoadingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(32.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

@Composable
@Preview(showBackground = false)
private fun SecondaryLoadingButton_Preview(){
    val isLoading by remember { mutableStateOf(false) }

    SiyaifrontandroidTheme {
        SecondaryLoadingButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "Кнопка",
            isLoading = isLoading,
            onClick = { }
        )
    }
}