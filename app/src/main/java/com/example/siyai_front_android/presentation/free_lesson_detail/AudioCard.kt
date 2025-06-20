package com.example.siyai_front_android.presentation.free_lesson_detail

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons

@Composable
fun AudioCard(
    isPlayAudio: Boolean,
    setIsPlayAudio: (Boolean) -> Unit,
    setIsShowVideo: (Boolean) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val mediaPlayer = remember { MediaPlayer() }

    var isLoading by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isPlayAudio) {
        if (isPlayAudio) {
            playAudio(
                mediaPlayer = mediaPlayer,
                setIsPlayAudio = setIsPlayAudio,
                setIsShowVideo = setIsShowVideo,
                setIsLoading = { isLoading = it },
                context = context
            )
        } else {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
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
            IconButton(
                onClick = {
                    if (isPlayAudio) {
                        setIsPlayAudio(false)
                    } else {
                        setIsPlayAudio(true)
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color.DarkGray,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = if (isPlayAudio) SiyAiIcons.Stop else SiyAiIcons.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(if (isPlayAudio) 16.dp else 24.dp)
                )
            }

            Text(
                text = if (isPlayAudio) {
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
                    .weight(1f)
            )
        }
    }
}

private fun playAudio(
    mediaPlayer: MediaPlayer,
    setIsPlayAudio: (Boolean) -> Unit,
    setIsShowVideo: (Boolean) -> Unit,
    setIsLoading: (Boolean) -> Unit,
    context: Context
) {
    setIsShowVideo(false)

    setIsLoading(true)

    try {
        mediaPlayer.reset()

        val assetFile = context.assets.openFd(context.getString(R.string.lesson_one_audio_path))

        mediaPlayer.setDataSource(
            assetFile.fileDescriptor,
            assetFile.startOffset,
            assetFile.length
        )

        mediaPlayer.prepareAsync()

        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
            setIsPlayAudio(true)
            setIsLoading(false)
        }

        mediaPlayer.setOnErrorListener { _, _, _ ->
            setIsLoading(false)
            false
        }
    } catch (e: Exception) {
        e.printStackTrace()
        setIsLoading(false)
    }
}