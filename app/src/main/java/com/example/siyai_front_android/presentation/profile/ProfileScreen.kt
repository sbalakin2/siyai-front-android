package com.example.siyai_front_android.presentation.profile

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.ui.icons.SiyAiIcons

@Composable
fun ProfileScreen(
    onEditClick: (
        email: String,
        firstName: String,
        lastName: String,
        birthday: String,
        country: String,
        city: String
    ) -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: ProfileViewModel = viewModel(factory = viewModelFactory)
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    viewModel.getProfile("test1@mail.com")

    val context = LocalContext.current

    when (profileState) {
        is ProfileState.Success -> {
            ProfileStateSuccess(
                profile = (profileState as ProfileState.Success).profile,
                onEditClick = onEditClick
            )
        }
        is ProfileState.Error -> {
            ProfileStateError(
                context = context,
                code = (profileState as ProfileState.Error).code,
                message = (profileState as ProfileState.Error).message
            )
        }
        is ProfileState.Exception -> {
            ProfileStateException(
                message = (profileState as ProfileState.Exception).message
            )
        }
        ProfileState.Loading -> {
            ProfileStateLoading()
        }
    }
}

@Composable
private fun ProfileStateSuccess(
    profile: Profile,
    onEditClick: (
        email: String,
        firstName: String,
        lastName: String,
        birthday: String,
        country: String,
        city: String
    ) -> Unit
) {
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
            text = "${profile.firstName} ${profile.lastName}",
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleLarge
        )

        FilledTonalButton(
            onClick = {
                onEditClick(
                    profile.email,
                    profile.firstName,
                    profile.lastName,
                    profile.birthday,
                    profile.country,
                    profile.city
                )
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
            modifier = Modifier.fillMaxWidth(),
            iconRes = R.drawable.log_out_image,
            titleRes = R.string.log_out_of_app
        )
    }
}

@Composable
private fun ProfileStateError(
    context: Context,
    code: Int,
    message: String
) {
    val errorMessage = when (code) {
        in 500..599 -> context.getString(R.string.server_error)
        else -> message
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ProfileStateException(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ProfileStateLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}