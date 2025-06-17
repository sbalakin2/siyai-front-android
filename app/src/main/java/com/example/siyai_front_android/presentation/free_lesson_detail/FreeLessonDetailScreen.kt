package com.example.siyai_front_android.presentation.free_lesson_detail

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import androidx.core.net.toUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeLessonDetailScreen(
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current

    val mediaPlayer = remember { MediaPlayer() }
    var isPlaying by rememberSaveable { mutableStateOf(false) }

    var showVideo by rememberSaveable { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.free_lesson_detail_title)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked
                    ) {
                        Icon(
                            imageVector = SiyAiIcons.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.lesson_one),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = stringResource(R.string.free_lesson_one_text),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Text(
                text = stringResource(R.string.meditation),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Card(
                onClick = {
                    try {
                        if (mediaPlayer.isPlaying) {
                            mediaPlayer.pause()
                            isPlaying = false
                        } else {
                            mediaPlayer.reset()
                            val assetFile =
                                context.assets.openFd(context.getString(R.string.lesson_one_audio_path))
                            mediaPlayer.setDataSource(
                                assetFile.fileDescriptor,
                                assetFile.startOffset,
                                assetFile.length
                            )
                            mediaPlayer.prepare()
                            mediaPlayer.start()
                            isPlaying = true
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = if (isPlaying) SiyAiIcons.Stop else SiyAiIcons.PlayArrow,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color.DarkGray,
                                shape = CircleShape
                            )
                            .padding(if (isPlaying) 12.dp else 8.dp)
                    )

                    Text(
                        text = if (isPlaying) {
                            stringResource(R.string.music_is_playing)
                        } else {
                            stringResource(R.string.lesson_one_audio)
                        },
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                    )
                }
            }

            Text(
                text = stringResource(R.string.lesson_one_video_title),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            Card(
                onClick = {
                    showVideo = true
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 8.dp)
            ) {
                if (showVideo) {
                    VideoPlayerLaunch(
                        context = context,
                        setShowVideo = { showVideo = false }
                    )
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

                        Icon(
                            imageVector = SiyAiIcons.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(64.dp)
                                .background(
                                    color = Color.Black.copy(alpha = 0.6F),
                                    shape = CircleShape
                                )
                                .padding(8.dp)
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.enjoy_your_viewing),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
    }
}

fun getVideoThumbnailFromAssets(context: Context, fileName: String): Bitmap? {
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

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoPlayerLaunch(
    context: Context,
    setShowVideo: () -> Unit
) {
    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            val uri = "asset:///".plus(context.getString(R.string.lesson_one_video_path)).toUri()
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
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
            onClick = setShowVideo,
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
    }
}