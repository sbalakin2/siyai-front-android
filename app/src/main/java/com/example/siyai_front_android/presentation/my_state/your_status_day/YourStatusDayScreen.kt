package com.example.siyai_front_android.presentation.my_state.your_status_day

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.icons.CloseIcon
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun YourStatusDayScreen(
    modifier: Modifier = Modifier,
    onSelectedStatus: (YourStatus, String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val isImeVisible = WindowInsets.isImeVisible
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    ModalBottomSheet(
        modifier = modifier.then(
            if (isImeVisible) Modifier.heightIn(min = screenHeight * 0.9f) else modifier
        ),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        val items = YourStatus.getStatusesList()
        var selectedIndex by remember { mutableIntStateOf(0) }
        var note by remember { mutableStateOf("") }

        val animatedColor by animateColorAsState(
            targetValue = YourStatus.getStatusColor(selectedIndex),
            animationSpec = tween(durationMillis = 300)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            animatedColor.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.secondaryContainer,
                            animatedColor.copy(alpha = 0.1f)
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                val bottomPadding = if (isImeVisible) 16.dp else 48.dp

                BottomSheetHeader(onDismiss = onDismiss)
                WheelPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 16.dp),
                    items = items,
                    onSelectionChanged = { index -> selectedIndex = index }
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp
                )
                BottomSheetTextField(
                    selectedColor = animatedColor,
                    note = note,
                    onValueChange = { note = it }
                )

                if (isImeVisible)
                    Spacer(modifier = Modifier.weight(1f))
                else
                    Spacer(modifier = Modifier.height(64.dp))

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = bottomPadding),
                    text = stringResource(R.string.apply),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                                onSelectedStatus(YourStatus.entries[selectedIndex], note)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomSheetHeader(
    onDismiss: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.your_status_today),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Icon(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(18.dp)
                .clickable { onDismiss() },
            imageVector = CloseIcon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun WheelPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    onSelectionChanged: (Int) -> Unit,
    pickerHeight: Dp = 190.dp
) {
    val itemHeight = pickerHeight / 4
    val middleIndex = (Int.MAX_VALUE / 2) - ((Int.MAX_VALUE / 2) % items.size)

    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = middleIndex - 1
    )

    val scrollInfo by remember {
        derivedStateOf {
            getScrollInfo(lazyListState, items.size)
        }
    }
    val (currentSelectedIndex, scrollProgress) = scrollInfo

    LaunchedEffect(currentSelectedIndex) {
        onSelectionChanged(currentSelectedIndex)
    }

    Box(
        modifier = modifier.height(pickerHeight),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(Unit) {
            snapshotFlow { lazyListState.isScrollInProgress }
                .distinctUntilChanged()
                .filter { !it }
                .collect { snapToClosestItem(lazyListState) }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithContent { this.cutCentralRectOverlay(itemHeight) },
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally,
            flingBehavior = rememberSnapFlingBehavior(lazyListState)
        ) {
            items(
                count = Int.MAX_VALUE,
                key = { it }
            ) { globalIndex ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.graphicsLayer {
                            this.rotationX = getRotationX(lazyListState, globalIndex)
                        },
                        text = items[globalIndex % items.size],
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp
                    )
                }
            }
        }
        CentralOverlay(
            itemHeight = itemHeight,
            scrollProgress = scrollProgress,
            selectedStatus = items[currentSelectedIndex],
            prevStatus = items[(currentSelectedIndex - 1 + items.size) % items.size],
            nextStatus = items[(currentSelectedIndex + 1) % items.size]
        )
    }
}

@Composable
private fun BottomSheetTextField(
    selectedColor: Color,
    note: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        selectedColor.copy(alpha = 0.15f),
                        Color.DarkGray.copy(alpha = 0.45f)
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = if (isFocused) 1.dp else 0.dp,
                color = if (isFocused) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .onFocusChanged { isFocused = it.isFocused },
        value = note,
        label = { Text(text = stringResource(R.string.note_label)) },
        onValueChange = { onValueChange(it) },
        maxLines = 4,
        textStyle = MaterialTheme.typography.bodyLarge.copy(lineHeight = 18.sp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colorScheme.outline,
            unfocusedLabelColor = MaterialTheme.colorScheme.outline,
            focusedTextColor = MaterialTheme.colorScheme.primary
        ),
    )
}

@Composable
private fun CentralOverlay(
    itemHeight: Dp,
    scrollProgress: Float,
    selectedStatus: String,
    prevStatus: String,
    nextStatus: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier.width(36.dp), thickness = 1.dp)
        Box(
            modifier = Modifier
                .weight(1f)
                .height(itemHeight)
                .clipToBounds(),
            contentAlignment = Alignment.Center
        ) {
            CentralOverlayText(text = selectedStatus, offsetY = (scrollProgress * itemHeight.value).dp)
            CentralOverlayText(text = prevStatus, offsetY = ((scrollProgress - 1) * itemHeight.value).dp)
            CentralOverlayText(text = nextStatus, offsetY = ((scrollProgress + 1) * itemHeight.value).dp)
        }
        Divider(modifier = Modifier.width(36.dp), thickness = 1.dp)
    }
}

@Composable
private fun CentralOverlayText(
    text: String,
    offsetY: Dp
) {
    Text(
        modifier = Modifier.offset(y = offsetY),
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 26.sp
    )
}

private fun getRotationX(lazyListState: LazyListState, globalIndex: Int): Float {
    return lazyListState.layoutInfo.visibleItemsInfo.find {
        it.index == globalIndex
    }?.let {
        val viewportCenterY = lazyListState.layoutInfo.viewportSize.height / 2f
        val itemCenterY = it.offset + it.size / 2f
        -40f * (itemCenterY - viewportCenterY) / viewportCenterY
    } ?: 0f
}

private fun ContentDrawScope.cutCentralRectOverlay(itemHeight: Dp) {
    drawContent()

    val maskHeight = itemHeight.toPx()
    val maskTop = (size.height / 2f) - (maskHeight / 2f)

    drawRect(
        color = Color.Transparent,
        topLeft = Offset(0f, maskTop),
        size = Size(size.width, maskHeight),
        blendMode = BlendMode.Clear
    )
}

private fun getScrollInfo(lazyListState: LazyListState, itemsSize: Int): Pair<Int, Float> {
    val focalPointY = lazyListState.layoutInfo.viewportSize.height / 2f
    val closestItem = lazyListState.layoutInfo.visibleItemsInfo
        .getClosestItem(focalPointY) ?: return Pair(0, 0f)
    val currentIndex = closestItem.index % itemsSize
    val progress = ((closestItem.offset + closestItem.size / 2)
            - focalPointY) / closestItem.size.toFloat()

    return Pair(currentIndex, progress)
}

private suspend fun snapToClosestItem(lazyListState: LazyListState) {
    val focalPointY = lazyListState.layoutInfo.viewportSize.height / 2f
    val closestItem = lazyListState.layoutInfo.visibleItemsInfo.getClosestItem(focalPointY)

    closestItem?.let {
        val scrollDelta = (closestItem.offset + closestItem.size / 2) - focalPointY
        lazyListState.animateScrollBy(scrollDelta)
    }
}

private fun List<LazyListItemInfo>.getClosestItem(focalPointY: Float): LazyListItemInfo? = this
    .minByOrNull {
        abs((it.offset + it.size / 2) - focalPointY)
    }

@Preview
@Composable
private fun PreviewYourStatusDayScreen() {
    YourStatusDayScreen(
        onSelectedStatus = { _, _ -> },
        onDismiss = {}
    )
}