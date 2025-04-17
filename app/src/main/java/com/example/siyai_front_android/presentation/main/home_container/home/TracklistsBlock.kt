package com.example.siyai_front_android.presentation.main.home_container.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.model.Regularity
import com.example.siyai_front_android.presentation.model.TrackList
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme
import com.example.siyai_front_android.utils.getDayWordForm

private val BoldBodyLarge
    @Composable get() = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)

fun LazyGridScope.TrackListsBlock(
    trackLists: List<TrackList>,
    onCheckTrack: (Int) -> Unit,
    onAddTrack: () -> Unit,
    goToArchive: () -> Unit
) {
    TrackListsHeader(onAddTrack = onAddTrack, goToArchive = goToArchive)
    if (trackLists.isEmpty()) {
        EmptyTrackLists(navigateToAddTrackScreen = onAddTrack)
    } else {
        TrackListsContent(trackLists = trackLists, onCheckTrack = onCheckTrack)
    }
}

private fun LazyGridScope.TrackListsHeader(onAddTrack: () -> Unit, goToArchive: () -> Unit) {
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.tracklists),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { goToArchive() },
                imageVector = SiyAiIcons.Archive,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.size(16.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onAddTrack() },
                imageVector = SiyAiIcons.Plus,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

private fun LazyGridScope.TrackListsContent(
    trackLists: List<TrackList>,
    onCheckTrack: (Int) -> Unit
) {
    items(trackLists, span = { GridItemSpan(maxCurrentLineSpan) }) { item ->
        TrackListItem(
            item = item,
            onCheckTrack = onCheckTrack
        )
    }
}

@Composable
fun TrackListItem(
    item: TrackList,
    onCheckTrack: (Int) -> Unit
) {
    val subtitle = "${stringResource(item.regularity.toStringRes())} · ${item.daysCount} " +
            stringResource(getDayWordForm(item.daysCount))

    SectionCard {
        Column {
            Text(
                item.name,
                style = BoldBodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                subtitle,
                style = BoldBodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = { onCheckTrack(item.id) },
            containerColor = MaterialTheme.colorScheme.onBackground,
            shape = RoundedCornerShape(64.dp)
        ) {
            if (item.isChecked) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    text = "${item.progress}%",
                    style = BoldBodyLarge,
                    color = Color.White
                )
            } else {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    imageVector = SiyAiIcons.Check,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

private fun LazyGridScope.EmptyTrackLists(navigateToAddTrackScreen: () -> Unit) {
    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
        SectionCard {
            Column {
                Text(
                    stringResource(R.string.tracklists_subtitle1),
                    style = BoldBodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    stringResource(R.string.tracklists_subtitle2),
                    style = BoldBodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = navigateToAddTrackScreen,
                containerColor = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(64.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    imageVector = SiyAiIcons.Plus,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun SectionCard(content: @Composable RowScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}

@Composable
@Preview("without items")
fun TrackListsBlock_WithoutItems_Preview() {
    SiyaifrontandroidTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            TrackListsBlock(
                trackLists = emptyList(),
                onCheckTrack = {},
                goToArchive = {},
                onAddTrack = {}
            )
        }
    }
}

@Composable
@Preview("with items")
fun TrackListsBlock_WithItems_Preview() {
    SiyaifrontandroidTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            TrackListsBlock(
                trackLists = listOf(
                    TrackList(
                        id = 1,
                        name = "спать",
                        regularity = Regularity.EVERY_DAY,
                        daysCount = 89,
                        isChecked = false
                    ),
                    TrackList(
                        id = 2,
                        name = "бегать",
                        regularity = Regularity.EVERY_DAY,
                        daysCount = 7,
                        isChecked = true,
                        progress = 1
                    )
                ),
                onCheckTrack = {},
                goToArchive = {},
                onAddTrack = {}
            )
        }
    }
}
