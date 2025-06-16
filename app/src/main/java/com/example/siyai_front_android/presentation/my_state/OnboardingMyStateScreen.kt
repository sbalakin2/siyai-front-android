package com.example.siyai_front_android.presentation.my_state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.GradientButton

@Composable
fun OnboardingMyStateScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = modifier.padding(start = 16.dp, top = 48.dp),
            text = stringResource(R.string.state_my_way),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.ic_gender),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.state_onboarding_title),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp),
                text = stringResource(R.string.state_onboarding_description),
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                ),
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(24.dp))
            GradientButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.state_onboarding_start_button),
                onClick = onContinueClick
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun PreviewOnboardingMyStateScreen() {
    OnboardingMyStateScreen { }
}