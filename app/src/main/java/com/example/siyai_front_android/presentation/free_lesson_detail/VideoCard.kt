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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoCard(
    isShowVideo: Boolean,
    setIsShowVideo: (Boolean) -> Unit,
    setIsPlayAudio: (Boolean) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 8.dp)
    ) {
        if (isShowVideo) {

            setIsPlayAudio(false)

            val exoPlayer = remember {
                ExoPlayer.Builder(context).build().apply {
                    val uri =
                        "asset:///".plus(context.getString(R.string.lesson_one_video_path)).toUri()

                    try {
                        setMediaItem(MediaItem.fromUri(uri))
                    } catch (e: Exception) {
                        e.printStackTrace()
                        isLoading = false
                    }

                    prepare()

                    playWhenReady = true

                    addListener(object: Player.Listener {
                        override fun onPlaybackStateChanged(playbackState: Int) {
                            isLoading = playbackState == Player.STATE_BUFFERING
                            if (playbackState == Player.STATE_READY) {
                                isLoading = false
                            }
                        }
                    })
                }
            }

            DisposableEffect(Unit) {
                onDispose {
                    exoPlayer.release()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AndroidView(
                    factory = { context ->
                        PlayerView(context).apply {
                            player = exoPlayer
                            useController = false
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                        }
                    }
                )

                IconButton(
                    onClick = {
                        setIsShowVideo(false)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .align(Alignment.TopEnd)
                        .background(
                            color = Color.Black.copy(alpha = 0.6F),
                            shape = CircleShape
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = SiyAiIcons.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        } else {
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
                    onClick = {
                        setIsShowVideo(true)
                    },
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