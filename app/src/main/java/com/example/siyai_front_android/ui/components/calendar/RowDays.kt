package com.example.siyai_front_android.ui.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.siyai_front_android.R

@Composable
fun RowDays(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val dayHeaders = remember {
        listOf(
            context.getString(R.string.day_mon),
            context.getString(R.string.day_tue),
            context.getString(R.string.day_wed),
            context.getString(R.string.day_thu),
            context.getString(R.string.day_fri),
            context.getString(R.string.day_sat),
            context.getString(R.string.day_sun),
        )
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        dayHeaders.forEach { day ->
            Text(
                text = day,
                color = Color.White.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}