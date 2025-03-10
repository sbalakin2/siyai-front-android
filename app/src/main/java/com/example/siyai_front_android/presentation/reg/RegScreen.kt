package com.example.siyai_front_android.presentation.reg

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.icons.SiyAiIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = SiyAiIcons.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    Text(
                        text = stringResource(R.string.there_is_account),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                onClick = onLoginClick
                            )
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
            Text(
                text = stringResource(R.string.registration),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        }
    }
}
