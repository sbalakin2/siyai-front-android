package com.example.siyai_front_android.presentation.sign_day

import android.content.Context
import android.graphics.BitmapFactory
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R

@Composable
fun SignOfTheDayScreen(
    onBackClicked: () -> Unit,
) {
    var signOfTheDay by remember { mutableStateOf<ImageBitmap?>(null) }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        loadSignOfTheDay(context = context, numberOfSign = dayOfMonth)
            .fold(
                onSuccess = { image ->
                    signOfTheDay = image
                },
                onFailure = {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        signOfTheDay?.let { image ->
            // blurred background
            Image(
                bitmap = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(20.dp)
            )

            // main image
            Image(
                bitmap = image,
                contentDescription = stringResource(R.string.sign_of_the_day_image),
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        IconButton(
            onClick = onBackClicked,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    shape = CircleShape
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = stringResource(R.string.close_sign_day_screen),
                tint = Color.White,
            )
        }
    }
}

private const val SIGN_OF_THE_DAYS_ASSETS = "sign_of_the_day"

private fun loadSignOfTheDay(
    context: Context,
    numberOfSign: Int,
): Result<ImageBitmap> = runCatching {
    val images = context.assets.list(SIGN_OF_THE_DAYS_ASSETS)
        ?: throw NoSuchElementException("Assets '$SIGN_OF_THE_DAYS_ASSETS' not found")

    val requiredImage = images[numberOfSign % images.size]
    val requiredImagePath = "$SIGN_OF_THE_DAYS_ASSETS/$requiredImage"

    return@runCatching context.assets
        .open(requiredImagePath)
        .use(BitmapFactory::decodeStream)
        .asImageBitmap()
}

@Preview
@Composable
private fun SignOfTheDayScreen_Preview() {
    SignOfTheDayScreen(
        onBackClicked = {}
    )
}