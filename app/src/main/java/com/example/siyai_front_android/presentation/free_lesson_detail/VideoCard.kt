package com.example.siyai_front_android.presentation.free_lesson_detail

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoCard(
    navigateToVideoPlayScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        onClick = navigateToVideoPlayScreen,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            getVideoThumbnailFromAssets(
                context,
                stringResource(R.string.lesson_one_video_path)
            )?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            IconButton(
                onClick = navigateToVideoPlayScreen,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.6F),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = SiyAiIcons.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    }
}

private fun getVideoThumbnailFromAssets(context: Context, fileName: String): Bitmap? {
    return try {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        val assetFile = context.assets.openFd(fileName)
        mediaMetadataRetriever.setDataSource(
            assetFile.fileDescriptor,
            assetFile.startOffset,
            assetFile.length
        )
        val frame = mediaMetadataRetriever.frameAtTime
        mediaMetadataRetriever.close()
        frame
    } catch (e: Exception) {
        null
    }
}