package com.example.siyai_front_android.presentation.free_lesson_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import androidx.media3.common.util.UnstableApi

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeLessonDetailScreen(
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current

    var isPlayAudio by rememberSaveable { mutableStateOf(false) }

    var isShowVideo by rememberSaveable { mutableStateOf(false) }

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

            AudioCard(
                isPlayAudio = isPlayAudio,
                setIsPlayAudio = { isPlayAudio = it },
                setIsShowVideo = { isShowVideo = it },
                context = context
            )

            Text(
                text = stringResource(R.string.lesson_one_video_title),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 16.dp)
            )

            VideoCard(
                isShowVideo = isShowVideo,
                setIsShowVideo = { isShowVideo = it },
                setIsPlayAudio = { isPlayAudio = it },
                context = context
            )

            Text(
                text = stringResource(R.string.enjoy_your_viewing),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
    }
}