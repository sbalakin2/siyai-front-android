package com.example.siyai_front_android.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons

@Composable
fun ProfileScreen(
    onEditClick: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: ProfileViewModel = viewModel(factory = viewModelFactory)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = R.drawable.avatar_image,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 64.dp)
                .size(160.dp)
                .clip(RoundedCornerShape(112.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Marat Faizov",
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleLarge
        )

        FilledTonalButton(
            onClick = {
                onEditClick()
            },
            modifier = Modifier.padding(top = 16.dp),
            shape = RoundedCornerShape(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = SiyAiIcons.Edit,
                contentDescription = null
            )

            Text(
                text = stringResource(R.string.edit),
                modifier = Modifier.padding(start = 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        ProfileItem(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            iconRes = R.drawable.statistics_image,
            titleRes = R.string.my_stats
        )

        Divider()

        ProfileItem(
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.support_image,
            titleRes = R.string.support
        )

        Divider()

        ProfileItem(
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.restoring_image,
            titleRes = R.string.restore_purchases
        )

        Divider()

        ProfileItem(
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.rate_image,
            titleRes = R.string.rate_app
        )

        Divider()

        ProfileItem(
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.about_app_image,
            titleRes = R.string.about_app
        )

        Divider()

        ProfileItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = viewModel::exitFromApp),
            iconRes = R.drawable.log_out_image,
            titleRes = R.string.log_out_of_app
        )
    }
}