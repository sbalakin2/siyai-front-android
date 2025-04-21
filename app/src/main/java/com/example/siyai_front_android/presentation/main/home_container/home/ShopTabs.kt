package com.example.siyai_front_android.presentation.main.home_container.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.model.Categories
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTabs(
    modifier: Modifier = Modifier,
    selectedCategory: Categories,
    onCategoryChange: (Categories) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.shop),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row {
            Categories.entries.forEachIndexed { index, category ->
                CategoryChip(
                    category = category,
                    selected = selectedCategory == category,
                    onClick = { onCategoryChange(category) }
                )
                if (index < Categories.entries.toTypedArray().lastIndex) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryChip(
    category: Categories,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = stringResource(category.toStringRes()),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.inversePrimary,
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        shape = RoundedCornerShape(16.dp),
        border = null
    )
}


@Composable
@Preview
private fun ShowTabs_Preview(){
    SiyaifrontandroidTheme {
        ShopTabs(selectedCategory = Categories.PURCHASED) {
            
        }
    }
}