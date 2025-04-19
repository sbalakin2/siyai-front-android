package com.example.siyai_front_android.presentation.main.home_container.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun WaitingListBanner(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.5f),
                painter = painterResource(R.drawable.waiting_list_image),
                contentDescription = null,
                contentScale = ContentScale.Crop)
            Column(modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 16.dp, start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.waiting_list),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Text(
                    text = stringResource(R.string.waiting_list_subtitle),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }
    }
}

@Composable
@Preview
fun WaitingListBanner_Preview(){
    SiyaifrontandroidTheme {
        WaitingListBanner(onClick = {})
    }
}