package com.example.siyai_front_android.presentation.auth.password_reset

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.components.text_fields.PasswordTextField
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.utils.validateUserData
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordResetScreen(
    token: String,
    onBackClick: () -> Unit,
    onSuccessPasswordReset: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    Log.d("MyLog","PasswordResetScreen opened with token: $token")
    val viewModel: PasswordResetViewModel = viewModel(factory = viewModelFactory)
    val passwordResetState by viewModel.passwordResetState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }

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
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.password_reset_subtitle),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = stringResource(R.string.password_reset_text),
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.titleMedium,
                )

                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    password = password,
                    onPasswordChange = { value ->
                        password = value
                    },
                    label = stringResource(R.string.new_password)
                )

                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    password = repeatPassword,
                    onPasswordChange = { value ->
                        repeatPassword = value
                    },
                    label = stringResource(R.string.repeat_new_password)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
                    text = stringResource(R.string.confirm),
                    onClick = {
                        validateUserData(null, password, repeatPassword, context)?.let { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            return@PrimaryButton
                        }
                        viewModel.resetPassword(token, password)
                    },
                    enabled = password.isNotEmpty() && repeatPassword.isNotEmpty()
                )
            }
        }
    }

    LaunchedEffect(password, repeatPassword) {

        delay(1000L)

        if (
            password.isNotEmpty() &&
            repeatPassword.isNotEmpty() &&
            password != repeatPassword
        ) {
            Toast.makeText(
                context,
                context.getString(R.string.invalid_repeat_password),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LaunchedEffect(passwordResetState) {
        getPasswordResetState(
            passwordResetState = passwordResetState,
            context = context,
            onSuccessPasswordReset = onSuccessPasswordReset,
            clearPasswordResetState = viewModel::clearPasswordResetState
        )
    }
}

private fun getPasswordResetState(
    passwordResetState: PasswordResetState,
    context: Context,
    onSuccessPasswordReset: () -> Unit,
    clearPasswordResetState: () -> Unit
) {
    when (passwordResetState) {
        is PasswordResetState.Success -> {
            onSuccessPasswordReset()
            clearPasswordResetState()
        }
        is PasswordResetState.Error -> {
            val errorMessage = when (passwordResetState.code) {
                in 500..599 -> context.getString(R.string.server_error)
                else -> passwordResetState.message
            }
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            clearPasswordResetState()
        }
        is PasswordResetState.Exception -> {
            Toast.makeText(context, passwordResetState.message, Toast.LENGTH_SHORT).show()
            clearPasswordResetState()
        }
        PasswordResetState.Idle -> {
        }
    }
}