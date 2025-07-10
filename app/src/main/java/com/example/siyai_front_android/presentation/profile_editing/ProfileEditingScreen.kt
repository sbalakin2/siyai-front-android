package com.example.siyai_front_android.presentation.profile_editing

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.siyai_front_android.R
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.components.buttons.SecondaryButton
import com.example.siyai_front_android.ui.components.text_fields.BaseTextField
import com.example.siyai_front_android.ui.components.text_fields.DatePickerTextField
import com.example.siyai_front_android.ui.components.text_fields.DropDownTextField
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.utils.toISODateString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditingScreen(
    onBackClick: () -> Unit,
    onOnboardingClick: () -> Unit,
    onDeleteProfile: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    // User data states
    val viewModel: ProfileEditingViewModel = viewModel(factory = viewModelFactory)
    val initialUserProfileData by viewModel.initialUserProfile.collectAsStateWithLifecycle()
    val profileEditingState by viewModel.profileEditingState.collectAsStateWithLifecycle()

    val countiesWithCities by viewModel.countiesWithCities.collectAsStateWithLifecycle()
    val countiesAndCitiesState = rememberCountryAndCitiesState(countiesWithCities)

    val profileState = rememberProfileState(initialUserProfileData, countiesWithCities)
    val deleteProfileState by viewModel.deleteProfileState.collectAsStateWithLifecycle()

    LaunchedEffect(profileState.countryIndex) {
        countiesAndCitiesState.setCitiesFromCountry(profileState.countryIndex)
    }

    // UI states
    val context = LocalContext.current

    val photoSelectionSheetState = rememberModalBottomSheetState()
    var showPhotoSelectionBottomSheet by remember { mutableStateOf(false) }

    val removingAccountSheetState = rememberModalBottomSheetState()
    var showRemovingAccountBottomSheet by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    var checkedPush by rememberSaveable { mutableStateOf(false) }

    val photoUri = remember { viewModel.provideTempPhotoUri(context) }
    val photoState = rememberSaveable {
        mutableStateOf(Pair(false, ""))
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) profileState.photo = photoUri.toString()
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            context.takePersistablePermissionIfSupported(it)
            profileState.photo = it.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = SiyAiIcons.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        AsyncImage(
            model = profileState.photo,
            contentDescription = null,
            error = painterResource(R.drawable.avatar_image),
            placeholder = painterResource(R.drawable.avatar_image),
            modifier = Modifier
                .padding(top = 16.dp)
                .size(160.dp)
                .clip(RoundedCornerShape(112.dp)),
            contentScale = ContentScale.Crop
        )

        FilledTonalButton(
            onClick = {
                showPhotoSelectionBottomSheet = true
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
                text = stringResource(R.string.choose_another_photo),
                modifier = Modifier.padding(start = 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Text(
            text = stringResource(R.string.remove_photo),
            modifier = Modifier
                .padding(top = 12.dp)
                .clickable(
                    onClick = {
                        photoState.value = true to profileState.photo
                        profileState.photo = ""
                    }
                ),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.push_notifications),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
            Switch(
                checked = checkedPush,
                onCheckedChange = { checkedPush = it },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    checkedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        BaseTextField(
            value = profileState.email,
            onValueChange = {},
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            label = stringResource(R.string.email),
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        BaseTextField(
            value = profileState.firstName,
            onValueChange = { profileState.firstName = it },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            label = stringResource(R.string.lets_meet_name),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        BaseTextField(
            value = profileState.lastName,
            onValueChange = { profileState.lastName = it },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            label = stringResource(R.string.lets_meet_surname),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        DatePickerTextField(
            value = profileState.birthday,
            onValueChange = { profileState.birthday = it },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            label = stringResource(R.string.lets_meet_birthday)
        )

        DropDownTextField(
            index = profileState.countryIndex,
            onIndexChange = profileState::updateCountryIndex,
            label = stringResource(R.string.lets_meet_country),
            itemLabel = { it.name },
            items = countiesAndCitiesState.countries,
            fullScreen = true,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )

        DropDownTextField(
            index = profileState.cityIndex,
            onIndexChange = profileState::updateCityIndex,
            label = stringResource(R.string.lets_meet_city),
            itemLabel = { it },
            items = countiesAndCitiesState.cities,
            enabled = countiesAndCitiesState.cities.isNotEmpty(),
            fullScreen = true,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable(
                    onClick = {
                        showRemovingAccountBottomSheet = true
                    }
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = SiyAiIcons.Trash,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.remove_account),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        PrimaryButton(
            text = stringResource(R.string.apply),
            onClick = {
                val profile = getCurrentProfile(profileState, countiesAndCitiesState)
                val (isDeletedPhoto, photoPath) = photoState.value
                viewModel.editProfile(profile)
                if (isDeletedPhoto) viewModel.deletePhotoByUri(context, photoPath)
            },
            modifier = Modifier
                .padding(top = 16.dp, bottom = 32.dp)
                .fillMaxWidth(),
            enabled = checkIsFormCompleted(profileState)
        )
    }

    LaunchedEffect(profileEditingState) {
        getProfileEditingState(
            profileEditingState = profileEditingState,
            context = context,
            onBackClick = onBackClick,
            clearProfileEditingState = viewModel::clearProfileEditingState
        )
    }

    LaunchedEffect(deleteProfileState) {
        getDeleteProfileState(
            deleteProfileState = deleteProfileState,
            context = context,
            onDeleteProfile = onDeleteProfile,
            clearProfileEditingState = viewModel::clearProfileEditingState
        )
    }

    if (showPhotoSelectionBottomSheet) {
        PhotoSelectionBottomSheet(
            openGalery = {
                galleryLauncher.launch(context.getString(R.string.mime_image))
            },
            openCamera = {
                cameraLauncher.launch(photoUri)
            },
            onDismissRequest = {
                showPhotoSelectionBottomSheet = false
            },
            sheetState = photoSelectionSheetState,
            coroutineScope = coroutineScope
        )
    }

    if (showRemovingAccountBottomSheet) {
        RemovingAccountBottomSheet(
            onOnboardingClick = onOnboardingClick,
            removeAccount = {
                viewModel.deleteProfile()
            },
            onDismissRequest = {
                showRemovingAccountBottomSheet = false
            },
            sheetState = removingAccountSheetState,
            coroutineScope = coroutineScope
        )
    }
}

private fun getCurrentProfile(
    profileEditState: ProfileEditState,
    countriesAndCitiesState: CountriesAndCitiesState
): Profile {
    return Profile(
        email = profileEditState.email,
        firstName = profileEditState.firstName,
        lastName = profileEditState.lastName,
        birthday = runCatching { profileEditState.birthday?.toISODateString().orEmpty() }
            .getOrDefault(""),
        country = countriesAndCitiesState.countries
            .getOrNull(profileEditState.countryIndex)?.name.orEmpty(),
        city = countriesAndCitiesState.cities
            .getOrNull(profileEditState.cityIndex).orEmpty(),
        photo = profileEditState.photo
    )
}

private fun getDeleteProfileState(
    deleteProfileState: DeleteProfileState,
    context: Context,
    onDeleteProfile: () -> Unit,
    clearProfileEditingState: () -> Unit
) {
    if (deleteProfileState is DeleteProfileState.Success) {
        Toast.makeText(context, deleteProfileState.message, Toast.LENGTH_SHORT).show()
        onDeleteProfile()
    }

    if (deleteProfileState is DeleteProfileState.Error) {
        Toast.makeText(
            context,
            "${context.getString(R.string.server_error)}: ${deleteProfileState.message}",
            Toast.LENGTH_SHORT
        ).show()
        clearProfileEditingState()
    }
}

private fun getProfileEditingState(
    profileEditingState: ProfileEditingState,
    context: Context,
    onBackClick: () -> Unit,
    clearProfileEditingState: () -> Unit
) {
    when (profileEditingState) {
        is ProfileEditingState.Success -> {
            onBackClick()
            clearProfileEditingState()
        }

        is ProfileEditingState.Error -> {
            val errorMessage = when (profileEditingState.code) {
                in 500..599 -> context.getString(R.string.server_error)
                else -> profileEditingState.message
            }
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            clearProfileEditingState()
        }

        is ProfileEditingState.Exception -> {
            Toast.makeText(context, profileEditingState.message, Toast.LENGTH_SHORT).show()
            clearProfileEditingState()
        }

        ProfileEditingState.Idle -> {
        }
    }
}

private fun checkIsFormCompleted(profileEditState: ProfileEditState): Boolean {
    return sequenceOf(profileEditState.firstName, profileEditState.lastName)
        .all { it.isNotEmpty() }
            && profileEditState.birthday != null
            && profileEditState.cityIndex != -1
            && profileEditState.countryIndex != -1
}

private fun Context.takePersistablePermissionIfSupported(uri: Uri) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        contentResolver.takePersistableUriPermission(
            uri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoSelectionBottomSheet(
    openGalery: () -> Unit,
    openCamera: () -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    coroutineScope: CoroutineScope
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9f),
        tonalElevation = 0.dp,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.selection_photo_bottom_sheet_title),
                style = MaterialTheme.typography.titleMedium
            )

            SecondaryButton(
                onClick = {
                    openGalery()
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismissRequest()
                    }
                },
                text = stringResource(R.string.selection_photo_bottom_sheet_confirm_one_text),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )

            SecondaryButton(
                onClick = {
                    openCamera()
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismissRequest()
                    }
                },
                text = stringResource(R.string.selection_photo_bottom_sheet_confirm_two_text),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RemovingAccountBottomSheet(
    onOnboardingClick: () -> Unit,
    removeAccount: () -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    coroutineScope: CoroutineScope
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9f),
        tonalElevation = 0.dp,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = SiyAiIcons.Trash,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(R.string.removing_account_bottom_sheet_title),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = stringResource(R.string.removing_account_bottom_sheet_text),
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            SecondaryButton(
                onClick = {
                    removeAccount()
                },
                text = stringResource(R.string.remove),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
            )

            SecondaryButton(
                onClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismissRequest()
                    }
                },
                text = stringResource(R.string.cancel),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}