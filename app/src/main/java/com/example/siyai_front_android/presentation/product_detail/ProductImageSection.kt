package com.example.siyai_front_android.presentation.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siyai_front_android.presentation.model.Product

@Composable
fun ProductImageSection(
    product: Product,
    height: Dp,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Image(
            painter = painterResource(id = product.imageId),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        ProductImageGradientOverlay()
        BackButton(onClick = onBackClicked)
        ProductImageOverlayText(product = product)
    }
}

@Composable
private fun BoxScope.ProductImageOverlayText(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(16.dp)
    ) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            lineHeight = 20.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
private fun ProductImageGradientOverlay(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Transparent,
                        MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                        MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                        MaterialTheme.colorScheme.background.copy(alpha = 1f)
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    )
}

@Composable
private fun BoxScope.BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .align(Alignment.TopStart)
            .padding(16.dp)
            .background(
                Color.Black.copy(alpha = 0.3f),
                CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            modifier = Modifier.size(28.dp),
            contentDescription = null,
            tint = Color.White
        )
    }
}