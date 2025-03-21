package com.example.siyai_front_android.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.components.buttons.SecondaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onLoginClick: () -> Unit,
    onRegClick: () -> Unit
) {
    val onboardingImages = listOf(
        Triple(
            R.drawable.onboarding_image_one,
            stringResource(R.string.welcome),
            stringResource(R.string.get_trained_and_reach_new_heights_with_us)
        ),
        Triple(
            R.drawable.onboarding_image_two,
            stringResource(R.string.shine),
            stringResource(R.string.with_our_course_you_will_return_to_your_real_self_in_five_weeks)
        ),
        Triple(
            R.drawable.onboarding_image_three,
            stringResource(R.string.shine),
            stringResource(R.string.create_account_faster_and_take_training_wherever_it_suits_you)
        )
    )

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { onboardingImages.size })

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(state = pagerState) { page ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 134.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = onboardingImages[page].first,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp, end = 24.dp, top = 8.dp)
                                .clip(RoundedCornerShape(32.dp)),
                            contentScale = ContentScale.FillWidth
                        )

                        Text(
                            text = onboardingImages[page].second,
                            modifier = Modifier.padding(top = 24.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Text(
                            text = onboardingImages[page].third,
                            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(color = MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->

                        val color = if (pagerState.currentPage == iteration) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onPrimary
                        }

                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
                        PrimaryButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage + 1)
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.next)
                        )
                    } else {
                        SecondaryButton(
                            onClick = onLoginClick,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 4.dp),
                            text = stringResource(R.string.log_in)
                        )

                        PrimaryButton(
                            onClick = onRegClick,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp),
                            text = stringResource(R.string.registration)
                        )
                    }
                }
            }
        }
    }
}
