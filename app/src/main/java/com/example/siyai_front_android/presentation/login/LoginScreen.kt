package com.example.siyai_front_android.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siyai_front_android.R
import com.example.siyai_front_android.ui.components.buttons.PrimaryButton
import com.example.siyai_front_android.ui.components.text_fields.ClearedTextField
import com.example.siyai_front_android.ui.components.text_fields.PasswordTextField
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.utils.validateUserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onRegClick: () -> Unit,
    onPasswordRecoveryClick: () -> Unit,
    onLogin: () -> Unit,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: LoginViewModel = viewModel(factory = viewModelFactory)
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

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
                        text = stringResource(R.string.there_is_no_account),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable(
                                onClick = onRegClick
                            ),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge
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
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.login),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = stringResource(R.string.log_in_to_existing_account),
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 3,
                    minLines = 3
                )

                ClearedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 26.dp, bottom = 6.dp),
                    value = email,
                    onValueChange = { value -> email = value },
                    label = stringResource(R.string.email),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    password = password,
                    onPasswordChange = { value -> password = value },
                    label = stringResource(R.string.password)
                )

                Text(
                    text = stringResource(R.string.forgot_password),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 16.dp, bottom = 120.dp)
                        .clickable(
                            onClick = onPasswordRecoveryClick
                        ),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
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
                    text = stringResource(R.string.log_in),
                    onClick = {
                        validateUserData(email, password, null, context)?.let { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            return@PrimaryButton
                        }
                        viewModel.loginUser(email, password)
                    }
                )
            }

            LaunchedEffect(loginState) {
                when (loginState) {
                    is LoginState.Success -> {
                        onLogin()
                        Toast.makeText(context, "sign in success", Toast.LENGTH_SHORT).show()
                        viewModel.resetState()
                    }
                    is LoginState.Error -> {
                        val errorMessage = if ((loginState as LoginState.Error).code in 500..599) {
                            context.getString(R.string.server_error)
                        } else {
                            (loginState as LoginState.Error).message
                        }
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        viewModel.resetState()
                    }
                    is LoginState.Exception -> {
                        Toast.makeText(
                            context,
                            (loginState as LoginState.Exception).message,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.resetState()
                    }
                    is LoginState.Loading -> {}
                    is LoginState.Empty -> {}
                }
            }
        }
    }
}
