package com.example.siyai_front_android.presentation.reg

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.email_confirmation.VerificationState
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.components.text_fields.ClearedTextField
import com.example.siyai_front_android.ui.components.text_fields.PasswordTextField
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme
import com.example.siyai_front_android.utils.validateUserData
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegScreen(
    onBackClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRegClick: (email: String, password: String, expDate: Int, otp: Int) -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: RegViewModel = viewModel(factory = viewModelFactory)
    val verificationState by viewModel.verificationState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

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
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
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
                            ),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.registration),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = stringResource(R.string.registration_subtitle),
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.titleLarge,
                maxLines = 3,
                minLines = 3
            )
            Spacer(modifier = Modifier.height(20.dp))
            ClearedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                value = email,
                onValueChange = { value ->
                    email = value
                },
                label = stringResource(R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                password = password,
                onPasswordChange = { value ->
                    password = value
                },
                label = stringResource(R.string.password)
            )
            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                password = repeatPassword,
                onPasswordChange = { value ->
                    repeatPassword = value
                },
                label = stringResource(R.string.repeat_password)
            )
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp),
                    text = stringResource(R.string.register),
                    onClick = {
                        validateUserData(email, password, repeatPassword, context)?.let { errorMessage ->
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            return@PrimaryButton
                        }
                        viewModel.verify(email)
                    },
                    enabled = email.isNotEmpty() &&
                            password.isNotEmpty() &&
                            repeatPassword.isNotEmpty()
                )
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

            LaunchedEffect(verificationState) {
                getVerificationState(
                    verificationState = verificationState,
                    context = context,
                    email = email,
                    password = password,
                    onRegClick = onRegClick,
                    clearVerificationState = viewModel::clearVerificationState
                )
            }
        }
    }
}

private fun getVerificationState(
    verificationState: VerificationState,
    context: Context,
    email: String,
    password: String,
    onRegClick: (email: String, password: String, expDate: Int, otp: Int) -> Unit,
    clearVerificationState: () -> Unit
) {
    when (verificationState) {
        is VerificationState.Success -> {
            onRegClick(email, password, verificationState.expDate, verificationState.otp)
            clearVerificationState()
        }
        is VerificationState.Error -> {
            val errorMessage = when (verificationState.code) {
                in 500..599 -> context.getString(R.string.server_error)
                else -> verificationState.message
            }
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            clearVerificationState()
        }
        is VerificationState.Exception -> {
            Toast.makeText(context, verificationState.message, Toast.LENGTH_SHORT).show()
            clearVerificationState()
        }
        VerificationState.Idle -> {
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun RegScreen_Preview() {
    SiyaifrontandroidTheme {
//        RegScreen(
//            onBackClick = { },
//            onLoginClick = { },
//            onEmailConfirmationClick = { },
//        )
    }
}

