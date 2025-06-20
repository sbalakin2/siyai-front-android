package com.example.siyai_front_android.presentation.product_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.GradientButton

@Composable
fun FixedBottomButton(
    price: Long,
    modifier: Modifier = Modifier,
    onAddToCartClicked: () -> Unit
) {
    GradientButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 48.dp),
        text = stringResource(R.string.product_price, price),
        onClick = onAddToCartClicked
    )
}