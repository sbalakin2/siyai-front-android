package com.example.siyai_front_android.presentation.profile_editing

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siyai_front_android.domain.dto.CountryWithCities
import com.example.siyai_front_android.domain.dto.Profile
import com.example.siyai_front_android.domain.usecases.DeleteProfileUseCase
import com.example.siyai_front_android.domain.usecases.EditProfileUseCase
import com.example.siyai_front_android.domain.usecases.GetCountiesWithCitiesUseCase
import com.example.siyai_front_android.domain.usecases.GetProfileUseCase
import com.example.siyai_front_android.presentation.profile.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class ProfileEditingViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    private val getCountiesWithCitiesUseCase: GetCountiesWithCitiesUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteProfileUseCase: DeleteProfileUseCase
) : ViewModel() {

    private val _profileEditingState = MutableStateFlow<ProfileEditingState>(ProfileEditingState.Idle)
    val profileEditingState: StateFlow<ProfileEditingState> = _profileEditingState.asStateFlow()

    private val _countriesWithCities = MutableStateFlow<List<CountryWithCities>>(emptyList())
    val countiesWithCities = _countriesWithCities.asStateFlow()

    private val _initialUserProfile = MutableStateFlow<Profile?>(null)
    val initialUserProfile: StateFlow<Profile?> = _initialUserProfile.asStateFlow()

    private val _deleteProfileState = MutableStateFlow<DeleteProfileState>(DeleteProfileState.Idle)
    val deleteProfileState: StateFlow<DeleteProfileState> = _deleteProfileState.asStateFlow()

    init {
        loadInitialProfileData()
        loadCountriesWithCities()
    }

    private fun loadCountriesWithCities() {
        viewModelScope.launch(Dispatchers.IO) {
            _countriesWithCities.value = getCountiesWithCitiesUseCase()
        }
    }

    private fun loadInitialProfileData() {
        viewModelScope.launch {
            val saveProfileState = getProfileUseCase()
                .firstOrNull { it is ProfileState.Success } as? ProfileState.Success

            _initialUserProfile.value = saveProfileState?.profile
        }
    }

    fun editProfile(profile: Profile) {
        viewModelScope.launch {
            _profileEditingState.value = editProfileUseCase(
                email = profile.email,
                firstName = profile.firstName,
                lastName = profile.lastName,
                birthday = profile.birthday,
                country = profile.country,
                city = profile.city,
                photo = profile.photo
            )
        }
    }

    fun clearProfileEditingState() {
        _profileEditingState.value = ProfileEditingState.Idle
        _deleteProfileState.value = DeleteProfileState.Idle
    }

    fun deleteProfile() {
        viewModelScope.launch {
            initialUserProfile.value?.let {
                _deleteProfileState.value = deleteProfileUseCase(it.email)
            }
        }
    }

    fun provideTempPhotoUri(context: Context): Uri {
        val file = File.createTempFile(PREFIX, SUFFIX, context.externalCacheDir)
            .apply { createNewFile() }
        return FileProvider.getUriForFile(
            context, "${context.packageName}$FILEPROVIDER", file
        )
    }

    fun deletePhotoByUri(context: Context, uriString: String) {
        try {
            val fileName = Uri.parse(uriString).pathSegments.last()
            val file = File(context.externalCacheDir, fileName)
            if (file.exists()) file.delete()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val PREFIX = "photo_"
        private const val SUFFIX = ".jpg"
        private const val FILEPROVIDER = ".fileprovider"
    }
}