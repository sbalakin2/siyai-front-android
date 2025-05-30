package com.example.siyai_front_android.presentation.main.home_container.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.model.Product
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun ProductGridItem(
    modifier: Modifier = Modifier,
    item: Product,
    onClick: (Product) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clickable(interactionSource = null, indication = null) { onClick(item) },
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .aspectRatio(1f),
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            stringResource(R.string.price, item.price),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            item.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview
private fun ProductGridItem_Preview() {
    SiyaifrontandroidTheme {
        ProductGridItem(item = Product(0, "SIYAI Premium", 99000)) {}
    }
}