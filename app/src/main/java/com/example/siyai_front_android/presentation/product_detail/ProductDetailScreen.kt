package com.example.siyai_front_android.presentation.product_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.model.Product
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

private val tempItems = listOf(
    Product(imageId = R.drawable.product_1, price = 39990, name = "SIYAI New Year"),
    Product(imageId = R.drawable.product_2, price = 990, name = "Вебинары"),
    Product(imageId = R.drawable.product_3, price = 9990, name = "Дом Сияй онлайн"),
    Product(imageId = R.drawable.product_4, price = 18990, name = "Практики SIYAI"),
    Product(imageId = R.drawable.product_5, price = 99900, name = "SIYAI Premium"),
    Product(imageId = R.drawable.product_6, price = 44999, name = "SIYAI 11.Любовь"),
    Product(imageId = R.drawable.product_7, price = 99999, name = "SIYAI New Level")
)

@Composable
fun ProductDetailScreen(
    product: Product,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    ProductDetailContent(
        product = product,
        modifier = modifier,
        onAddToCartClicked = {},
        onBackClicked = onBackClicked
    )
}

@Composable
private fun ProductDetailContent(
    product: Product,
    modifier: Modifier = Modifier,
    onAddToCartClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val imageHeight = screenHeightDp * 0.45f

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 116.dp)
        ) {
            item {
                ProductImageSection(
                    product = product,
                    height = imageHeight,
                    onBackClicked = onBackClicked
                )
            }
            items(tempItems) {
                ProductItem(it = it)
            }
        }

        FixedBottomButton(
            price = product.price,
            modifier = Modifier.align(Alignment.BottomCenter),
            onAddToCartClicked = onAddToCartClicked
        )
    }
}

@Composable
@Preview
fun ProductScreenPreview() {
    SiyaifrontandroidTheme {
        ProductDetailScreen(Product(0, "SIYAI Premium", 99000)) { }
    }
}