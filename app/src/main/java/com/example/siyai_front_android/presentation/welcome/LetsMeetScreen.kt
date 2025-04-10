package com.example.siyai_front_android.presentation.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.components.text_fields.BaseTextField
import com.example.siyai_front_android.ui.components.text_fields.DatePickerTextField
import com.example.siyai_front_android.ui.components.text_fields.DropDownTextField
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetsMeetScreen(
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: LetsMeetViewModel = viewModel(factory = viewModelFactory)

    var userName by rememberSaveable { mutableStateOf("") }
    var userSurname by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var userBirthday by rememberSaveable { mutableStateOf<Date?>(null) }
    var country by rememberSaveable { mutableStateOf("") }

    val isEnterToAppEnabled by remember {
        derivedStateOf {
            sequenceOf(userName, userSurname, city, country)
                .all { it.isNotEmpty() } && userBirthday != null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {})
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.lets_meet),
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(R.string.lets_meet_subtitle),
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 3,
                minLines = 3
            )

            Spacer(modifier = Modifier.height(20.dp))

            BaseTextField(
                value = userName,
                onValueChange = { value -> userName = value },
                label = stringResource(R.string.lets_meet_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            BaseTextField(
                value = userSurname,
                onValueChange = { value -> userSurname = value },
                label = stringResource(R.string.lets_meet_surname),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            DatePickerTextField(
                value = userBirthday,
                onValueChange = { value -> userBirthday = value },
                label = stringResource(R.string.lets_meet_birthday),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            DropDownTextField(
                value = country,
                onValueChange = { value -> country = value },
                label = stringResource(R.string.lets_meet_country),
                items = List(50) { "Country $it" },
                fullScreen = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            DropDownTextField(
                value = city,
                onValueChange = { value -> city = value },
                label = stringResource(R.string.lets_meet_city),
                items = List(50) { "City $it" },
                fullScreen = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 32.dp),
                text = stringResource(R.string.enter_to_app),
                onClick = {

                },
                enabled = isEnterToAppEnabled
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun LetsMeetScreen_Preview() {
    SiyaifrontandroidTheme {
//        LetsMeetScreen()
    }
}

