package com.example.siyai_front_android.presentation.auth.lets_meet

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.CreateProfileData
import com.example.siyai_front_android.ui.components.buttons.SecondaryLoadingButton
import com.example.siyai_front_android.ui.components.text_fields.BaseTextField
import com.example.siyai_front_android.ui.components.text_fields.DatePickerTextField
import com.example.siyai_front_android.ui.components.text_fields.DropDownTextField
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme
import com.example.siyai_front_android.utils.toISODateString
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetsMeetScreen(
    email: String,
    onProfileCreated: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: LetsMeetViewModel = viewModel(factory = viewModelFactory)

    val countiesWithCities by viewModel.countiesWithCities.collectAsStateWithLifecycle()
    var cities by remember { mutableStateOf<List<String>>(emptyList()) }

    var countryIndex by rememberSaveable { mutableIntStateOf(-1) }
    var cityIndex by rememberSaveable { mutableIntStateOf(-1) }

    LaunchedEffect(countryIndex) {
        cities = countiesWithCities.getOrNull(countryIndex)?.cities ?: emptyList()
    }

    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var birthday by rememberSaveable { mutableStateOf<Date?>(null) }

    val isCreateProfileEnabled by remember {
        derivedStateOf {
            name.isNotEmpty() && surname.isNotEmpty() && birthday != null
                    && cityIndex != -1 && countryIndex != -1
        }
    }

    val letsMeetState by viewModel.letsMeetState.collectAsStateWithLifecycle()
    val isProfileCreating by remember { derivedStateOf { letsMeetState is LetsMeetState.Loading } }

    val context = LocalContext.current

    LaunchedEffect(letsMeetState) {
        when (val currentState = letsMeetState) {
            is LetsMeetState.Success -> {
                onProfileCreated()
            }

            is LetsMeetState.Error -> {
                Toast.makeText(context, "Error: " + currentState.message, Toast.LENGTH_SHORT)
                    .show()
            }

            is LetsMeetState.Exception -> {
                Toast.makeText(context, "Exception: " + currentState.message, Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {}
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
                value = name,
                onValueChange = { value -> name = value },
                label = stringResource(R.string.lets_meet_name),
                enabled = !isProfileCreating,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            BaseTextField(
                value = surname,
                onValueChange = { value -> surname = value },
                label = stringResource(R.string.lets_meet_surname),
                enabled = !isProfileCreating,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            DatePickerTextField(
                value = birthday,
                onValueChange = { value -> birthday = value },
                label = stringResource(R.string.lets_meet_birthday),
                enabled = !isProfileCreating,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            DropDownTextField(
                index = countryIndex,
                onIndexChange = { index -> countryIndex = index; cityIndex = -1 },
                label = stringResource(R.string.lets_meet_country),
                itemLabel = { it.name },
                items = countiesWithCities,
                fullScreen = false,
                enabled = !isProfileCreating,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            DropDownTextField(
                index = cityIndex,
                onIndexChange = { index -> cityIndex = index },
                label = stringResource(R.string.lets_meet_city),
                itemLabel = { it },
                items = cities,
                fullScreen = false,
                enabled = !isProfileCreating && cities.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            SecondaryLoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, bottom = 32.dp),
                text = stringResource(R.string.enter_to_app),
                isLoading = isProfileCreating,
                onClick = {
                    createUserProfileData(
                        userEmail = email,
                        userName = name,
                        userSurname = surname,
                        userBirthday = birthday,
                        userCountry = countiesWithCities.getOrNull(countryIndex)?.name,
                        userCity = cities.getOrNull(cityIndex)
                    )?.let { data -> viewModel.createUserProfile(data) }
                },
                enabled = isCreateProfileEnabled && !isProfileCreating
            )
        }
    }
}

private fun createUserProfileData(
    userEmail: String,
    userName: String,
    userSurname: String,
    userBirthday: Date?,
    userCountry: String?,
    userCity: String?
): CreateProfileData? {
    if (userBirthday == null || userCountry == null || userCity == null) {
        return null
    }

    val data = CreateProfileData(
        email = userEmail,
        name = userName,
        surName = userSurname,
        birthday = userBirthday.toISODateString(),
        country = userCountry,
        city = userCity
    )

    return data
}

@Composable
@Preview(showSystemUi = true)
private fun LetsMeetScreen_Preview() {
    SiyaifrontandroidTheme {
//        LetsMeetScreen()
    }
}

